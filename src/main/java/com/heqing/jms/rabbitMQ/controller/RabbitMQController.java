package com.heqing.jms.rabbitMQ.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heqing.jms.rabbitMQ.queue.RQueueProducer;
/**
 * @description controller测试
 */
@Controller
@RequestMapping("/rabbitMQ")
public class RabbitMQController {
	
	@Resource 
	RQueueProducer rQueueProducer;
	
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
			rQueueProducer.sendMessage(message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
}
