package com.heqing.jms.rabbitMQ.queue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 功能概要：消费接收
 */
@Component("rQueueConsumer1")
public class RQueueConsumer1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		System.out.println("RabbitMQ Queue 1接收到消息:"+ message);
	}

}
