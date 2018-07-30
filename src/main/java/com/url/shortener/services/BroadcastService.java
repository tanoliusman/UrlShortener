package com.url.shortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.url.shortener.config.HelloMessage;

@Service
public class BroadcastService {

	@Autowired
	private  SimpMessagingTemplate brokerMessagingTemplate;
	
	
	 public void broadcast(HelloMessage  message) {
	    	this.brokerMessagingTemplate.convertAndSend("/topic/greetings", message);
	    }

	
}
