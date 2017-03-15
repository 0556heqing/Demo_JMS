
package com.heqing.jms.activeMQ.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * @description  队列消息监听器
 */
@Component("aQueueConsumer1")
public class AQueueConsumer1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("ActiveMQ Queue 1接收到消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
