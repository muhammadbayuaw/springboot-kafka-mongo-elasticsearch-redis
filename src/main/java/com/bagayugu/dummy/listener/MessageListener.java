package com.bagayugu.dummy.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bagayugu.dummy.message.MailDto;
import com.bagayugu.dummy.util.MailUtil;

@Service
@PropertySources({
	@PropertySource("classpath:kafka.properties"),
	@PropertySource("classpath:mail.properties")
})
public class MessageListener {

	@Value(value = "${email.from.username}")
	private String fromUsername;
	@Value(value = "${email.from.password}")
	private String fromPassword;
	@Value(value = "${email.to.username}")
	private String toUsername;
	@Value(value = "${email.subject}")
	private String subject;
	
	@KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenDeleteAndPushNotif(String message) {
        System.out.println("Recieved message: " + message);
        
        MailDto mailRequest = MailDto.builder()
        		.fromUsername(fromUsername)
        		.fromPassword(fromPassword)
        		.toUsername(toUsername)
        		.subject(subject)
        		.message(message).build();
        
        MailUtil.sendEmailNotif(mailRequest);
    }
}
