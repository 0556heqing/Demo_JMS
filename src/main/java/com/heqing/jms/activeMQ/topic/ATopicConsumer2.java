package com.heqing.jms.activeMQ.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * @description  Topic消息监听器
 */
@Component("aTopicConsumer2")
public class ATopicConsumer2 implements MessageListener{


	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("ActiveMQ Topic 2接收到消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
