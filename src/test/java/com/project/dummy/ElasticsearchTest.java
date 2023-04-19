package com.project.dummy;

import com.project.dummy.controller.ElasticProductController;
import com.project.dummy.elasticsearch.model.EsProduct;
import com.project.dummy.elasticsearch.repository.EsProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ElasticsearchTest {

	@Autowired
	private ElasticProductController elasticController;
	
	@MockBean
	private EsProductRepository elasticRepository;
	
	@Test
	public void findAllProductTest() {
		when(elasticRepository.findAll()).thenReturn(
				new PageImpl<>(Stream.of(
						new EsProduct("123", "MLD", 15000), new EsProduct("456", "Sampoerna", 20000)
					).collect(Collectors.toList()))
			);
		assertEquals(2, elasticController.findAllViaElastic().getBody().getProducts().size());
	}
}
