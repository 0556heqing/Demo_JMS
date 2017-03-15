package com.heqing.testJMS.rabbitMQ;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.QueueingConsumer;  

public class TestConsumer {

	private final static String QUEUE_NAME = "Demo_JMS";  
	  
    public static void main(String[] argv) throws Exception {  
  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        channel.basicConsume(QUEUE_NAME, true, consumer);  
  
        while (true) {  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            System.out.println(" Received '" + message + "'");  
        }  
    }  
}
