package com.heqing.jms.activeMQ.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heqing.jms.activeMQ.queue.AQueueProducer;
import com.heqing.jms.activeMQ.topic.ATopicProducer;

/**
 * @description controller测试
 */
@Controller
@RequestMapping("/activeMQ")
public class ActiveMQController {
	
	@Resource 
	AQueueProducer aQueueProducer;
	@Resource 
	ATopicProducer aTopicProducer;
	
	/**
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message")String message){
		String opt="";
		try {
			aQueueProducer.send("active.queue", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
	/**
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
	 * 这个是主题目的地是一对多的
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try {
			aTopicProducer.send("active.topic", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
}
