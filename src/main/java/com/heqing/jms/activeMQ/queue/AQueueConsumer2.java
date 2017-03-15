
package com.heqing.jms.activeMQ.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * @description  队列消息监听器
 */
@Component("aQueueConsumer2")
public class AQueueConsumer2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("ActiveMQ Queue 2接收到消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
