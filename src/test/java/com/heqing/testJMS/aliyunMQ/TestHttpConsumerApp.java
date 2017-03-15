package com.heqing.testJMS.aliyunMQ;

import java.util.List;

import com.heqing.jms.aliyunMQ.bean.MyMessage;
import com.heqing.jms.aliyunMQ.bean.ResultMessage;
import com.heqing.jms.aliyunMQ.util.HttpMQConsumer;
import com.heqing.jms.utils.PropertyUtil;

public class TestHttpConsumerApp {

	public static void main(String[] args) {
		
		String consumerId = PropertyUtil.getProperty("config.properties", "aliyun.ConsumerId");	
		String topic 	  = PropertyUtil.getProperty("config.properties", "aliyun.Topic");	
		MyMessage msg     = new MyMessage(consumerId, "consumer", topic);
		HttpMQConsumer consumer =new HttpMQConsumer(msg);
		List<ResultMessage> list = consumer.pull();
		if (list != null && list.size() > 0) {
			for (ResultMessage rm : list) {
				System.out.println(rm.toString());

//				// 当消息处理成功后，需要进行delete，如果不及时delete将会导致重复消费此消息
//				if (consumer.delete(rm.getMsgHandle())) {
//					System.out.println("delete success: " + rm.getMsgHandle());
//				} else {
//					System.out.println("delete failed: " + rm.getMsgHandle());
//				}
			}
		}
	}

}
