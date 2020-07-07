package com.bagayugu.dummy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bagayugu.dummy.controller.ProductController;
import com.bagayugu.dummy.mongo.model.Product;
import com.bagayugu.dummy.mongo.repository.ProductRepository;
import com.bagayugu.dummy.request.ProductRequest;

@SpringBootTest
class DummyApplicationTests {
	
	@Autowired
	private ProductController controller;
	@MockBean
	private ProductRepository repository;

	private Date now = new Date();
	private String id = "123";
	private Product product = Product.builder()
			.id("123")
			.name("MLD")
			.quantity(5)
			.price(15000)
			.expiredDate(now).build();
	
	@Test
	public void findByIdTest() {
		when(repository.findById(id)).thenReturn(Optional.of(product));
		assertEquals("MLD", controller.findById(id).getBody().getProduct().getName());
	}
	
	@Test
	public void saveTest() {
		Product _product = this.product;
		_product.setId(null);
		
		ProductRequest request = ProductRequest.builder()
				.name("MLD")
				.quantity(5)
				.price(15000)
				.expiredDate(now).build();
		
		when(repository.save(_product)).thenReturn(this.product);
		assertEquals("MLD", controller.save(request).getBody().getProduct().getName());
	}
	
	@Test
	public void deleteTest() {
		repository.delete(product);
		verify(repository, times(1)).delete(product);
	}
}
