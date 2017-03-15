package com.heqing.jms.rabbitMQ.queue;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 功能概要：消息产生,提交到队列中去
 */
@Service
public class RQueueProducer {

	@Resource
	@Qualifier("rmqTemplate")
	private AmqpTemplate rmqTemplate;

	public void sendMessage(Object message){
		rmqTemplate.convertAndSend("rabbit_queue",message);
	}
}
