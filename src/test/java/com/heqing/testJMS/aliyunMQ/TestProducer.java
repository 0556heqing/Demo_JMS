package com.heqing.testJMS.aliyunMQ;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;

public class TestProducer {

    public static void main(String[] args) {
    	try{
	       Properties properties = new Properties();
	       properties.put(PropertyKeyConst.AccessKey,  "LTAIxvzm9qkDCt7j");
	       properties.put(PropertyKeyConst.SecretKey,  "b59IXvm4rpritxjmvkeGHi5Gv7S2Mk");
	       properties.put(PropertyKeyConst.ProducerId, "PID_Demo_JMS");
	       //公有云生产环境：http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
	       //公有云公测环境：http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet
	       //杭州金融云环境：http://jbponsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal
	       //杭州深圳云环境：http://mq4finance-sz.addr.aliyun.com:8080/rocketmq/nsaddr4client-internal
	//       properties.put(PropertyKeyConst.ONSAddr,
	//          "http://onsaddr-internal.aliyun.com:8080/rocketmq/nsaddr4client-internal");//此处以公有云生产环境为例
	       Producer producer = ONSFactory.createProducer(properties);
	           
	       //在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
	       producer.start();
	       Message msg = new Message(
	            //Message Topic
	            "testMQ_JMS", 
	            //Message Tag,
	            //可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤        
	            "TagA",
	            //Message Body
	            //任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
	            "Hello ONS".getBytes()
	        );
	        
	        // 设置代表消息的业务关键属性，请尽可能全局唯一。
	        // 以方便您在无法正常收到消息情况下，可通过ONS Console查询消息并补发。
	        // 注意：不设置也不会影响消息正常收发
	        msg.setKey("ORDERID_100");
	        
	        /**
	         * 定时消息投递，设置投递的具体时间戳，单位毫秒例如2017-01-01 0:0:00投递
	         * 定时/延时消息 msg.setStartDeliverTime 的参数可设置40天内的任何时刻（单位毫秒），超过40天消息发送将失败。
	        */
//	        long timeStamp =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-01-01 0:0:00").getTime();
//	        msg.setStartDeliverTime(timeStamp);
	        
	        // deliver time 单位 ms，指定一个时刻，在这个时刻之后才能被消费，这个例子表示 3s 后才能被消费
//	        long delayTime = 3000;
//	        msg.setStartDeliverTime(System.currentTimeMillis() + delayTime);

	        //发送消息，只要不抛异常就是成功
	        SendResult sendResult = producer.send(msg);
	        
	        System.out.println(sendResult);
	
	        // 在应用退出前，销毁Producer对象
	        // 注意：如果不销毁也没有问题
	        producer.shutdown();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
    }
}
