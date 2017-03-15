package com.heqing.jms.rabbitMQ.queue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 功能概要：消费接收
 */
@Component("rQueueConsumer2")
public class RQueueConsumer2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("RabbitMQ Queue 2接收到消息:"+ message);
	}

}
