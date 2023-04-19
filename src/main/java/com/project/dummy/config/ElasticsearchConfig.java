package com.project.dummy.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Configuration
@PropertySource("classpath:elasticsearch.properties")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration{

	@Value("${elasticsearch.host}")
	private String host;
	@Value("${elasticsearch.port}")
	private String port;
	
	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		System.out.println(">> Elastic node : "+host+":"+port);
		final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
				.connectedTo(host +":"+ port)
				.build();
		
		return RestClients.create(clientConfiguration).rest();
	}
}
