package com.heqing.testJMS.aliyunMQ;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

public class TestConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.AccessKey,  "LTAIxvzm9qkDCt7j");
        properties.put(PropertyKeyConst.SecretKey,  "b59IXvm4rpritxjmvkeGHi5Gv7S2Mk");
        properties.put(PropertyKeyConst.ConsumerId, "CID_Demo_JMS");
        Consumer consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe("testMQ_JMS", "*", new MessageListener() {
			@Override
			public Action consume(Message message, ConsumeContext context) {
				// TODO Auto-generated method stub
				try {
				String jsonbody=new String(message.getBody(), "UTF-8"); 
				System.out.println("Receive: " + jsonbody);
				} catch(Exception e) {
					e.printStackTrace();
				}
				return Action.CommitMessage;
			}
        });
        consumer.start();
        System.out.println("Consumer Started");
    } 
}
