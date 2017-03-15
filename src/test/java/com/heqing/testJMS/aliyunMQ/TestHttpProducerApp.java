package com.heqing.testJMS.aliyunMQ;

import com.heqing.jms.aliyunMQ.bean.MyMessage;
import com.heqing.jms.aliyunMQ.util.HttpMQProducer;
import com.heqing.jms.utils.PropertyUtil;


public class TestHttpProducerApp {
	
	public static void main(String[] args) {

		String producerId = PropertyUtil.getProperty("config.properties", "aliyun.ProducerId");	
		String topic = PropertyUtil.getProperty("config.properties", "aliyun.Topic");	
		MyMessage msg = new MyMessage(producerId, "producer", topic);
		msg.setBaby("hello wrold");
		HttpMQProducer producer = new HttpMQProducer(msg);

		if (producer.send()) {
			System.out.println("send message success");
		} else {
			System.out.println("send message failed");
		}
	}
}
