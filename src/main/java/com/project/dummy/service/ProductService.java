package com.project.dummy.service;

import com.project.dummy.exception.ProductNotFoundException;
import com.project.dummy.message.ProductDto;
import com.project.dummy.mongo.model.Product;
import com.project.dummy.mongo.repository.ProductRepository;
import com.project.dummy.redis.repository.RedisProductRepository;
import com.project.dummy.request.ProductRequest;
import com.project.dummy.response.BaseResponse;
import com.project.dummy.response.DetailProductResponse;
import com.project.dummy.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Optional;

/**
 * @author Muhammad Bayu Agusto
 *
 */

@Service
@PropertySource("classpath:kafka.properties")
public class ProductService {
	
	@Value(value = "${kafka.topic.name}")
	private String topicName;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RedisProductRepository redisProductRepository;
	
	public DetailProductResponse create(ProductRequest request) {
		Product product = Product.builder()
				.name(request.getName())
				.price(request.getPrice()) 
				.expiredDate(request.getExpiredDate())
				.quantity(request.getQuantity()).build();
		  
		Product _product = productRepository.save(product);
		  
		ProductDto dto = ProductDto.builder()
				.id(_product.getId())
				.name(_product.getName())
				.price(_product.getPrice())
				.expiredDate(_product.getExpiredDate())
				.quantity(_product.getQuantity()).build(); 
		
		//Evict redis cache
		redisProductRepository.save(dto);
		
		//Publish kafka and email notif
        String message = "Data product "+ _product.getName() + " has been <b>added.</b>";
		publishEmailNotif(message);

		return new DetailProductResponse(dto, ResponseUtil.Message.SUCCESS,
				ResponseUtil.Code.SUCCESS);
	}
	
	public DetailProductResponse update(String id, ProductRequest request) throws ProductNotFoundException {
		Optional<Product> productOpt = productRepository.findById(id);
		
		if(!productOpt.isPresent())
			throw new ProductNotFoundException("Product id-" + id +" not found");
		
		Product product = productOpt.get();
		product.setName(request.getName());
		product.setExpiredDate(request.getExpiredDate());
		product.setPrice(request.getPrice());
		product.setQuantity(request.getQuantity());
		Product _product = productRepository.save(product);
		
		ProductDto dto = ProductDto.builder()
				.id(_product.getId())
				.name(_product.getName())
				.expiredDate(_product.getExpiredDate())
				.price(_product.getPrice())
				.quantity(_product.getQuantity()).build();
		
		//Set new data cache to Redis
		redisProductRepository.save(dto);
		
		return new DetailProductResponse(dto, ResponseUtil.Message.SUCCESS, 
				ResponseUtil.Code.SUCCESS);
	}
	
	public BaseResponse delete(String id) throws ProductNotFoundException{
		Optional<Product> productOpt = productRepository.findById(id);
		
		if(!productOpt.isPresent())
			throw new ProductNotFoundException("Product id-" + id +" not found");
		
        productRepository.delete(productOpt.get());

        Optional<ProductDto> dtoCache = redisProductRepository.findById(id);
		dtoCache.ifPresent(productDto -> redisProductRepository.delete(productDto));
		
		//Publish kafka and email notif
        String message = "Data product "+ productOpt.get().getName() + " has been <b>deleted.</b>";
		publishEmailNotif(message);
		
		return new BaseResponse(ResponseUtil.Message.SUCCESS, ResponseUtil.Code.SUCCESS);
	}
	
	public DetailProductResponse findById(String id) throws ProductNotFoundException{
		ProductDto response;

		//Check Redis cache
		Optional<ProductDto> dtoCache = redisProductRepository.findById(id);
		if(!dtoCache.isPresent()) {
			Optional<Product> productOpt = productRepository.findById(id);
			
			if(!productOpt.isPresent())
				throw new ProductNotFoundException("Product id-" + id +" not found");
			
			Product product = productOpt.get();
			response = ProductDto.builder()
					.id(product.getId())
					.name(product.getName())
					.price(product.getPrice())
					.quantity(product.getQuantity())
					.expiredDate(product.getExpiredDate()).build();
			
			//Cache to Redis
			redisProductRepository.save(response);
		}else {
			response = dtoCache.get();
		}
		
		return new DetailProductResponse(response, ResponseUtil.Message.SUCCESS, 
				ResponseUtil.Code.SUCCESS);
	}
	
	private void publishEmailNotif(String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
        	@Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + 
                	result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " +
                	ex.getMessage());
            }
        });
	}
}
