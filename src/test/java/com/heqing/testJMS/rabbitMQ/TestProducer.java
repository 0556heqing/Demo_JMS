package com.heqing.testJMS.rabbitMQ;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  

public class TestProducer {
	
	private final static String QUEUE_NAME = "Demo_JMS";  
	  
    public static void main(String[] args) throws Exception {  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = "Hello heqing!";  
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());   
  
        channel.close();  
        connection.close();  
    }  
}
