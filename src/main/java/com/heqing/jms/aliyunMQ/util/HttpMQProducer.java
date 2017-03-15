package com.heqing.jms.aliyunMQ.util;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.openservices.ons.api.impl.authority.AuthUtil;
import com.heqing.jms.aliyunMQ.bean.MyMessage;
import com.heqing.jms.utils.MD5Util;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HttpMQProducer {

	private static final Logger log = LoggerFactory.getLogger(HttpMQProducer.class);

	private static final String NEWLINE = "\n";
	
	MyMessage msg;
	
	public HttpMQProducer(MyMessage msg) {
		this.msg = msg;
	}
	
	/**
	 * 发送消息
	 * @param Message
	 * @return
	 */
	public boolean send() {
		if(msg.getTopic()==null || msg.getProducerId()==null) {
			log.error("topic or consumerId is null");
			return false;
		}
		try {
			long time = System.currentTimeMillis();
			HttpRequestWithBody req = Unirest.post(msg.getUrl());
			String signString = msg.getTopic() + NEWLINE + msg.getProducerId() + NEWLINE + 
					MD5Util.getInstance().getMD5String(msg.getBaby())+ NEWLINE + time;
			String sign = AuthUtil.calSignature(signString.getBytes("UTF-8"), msg.getSecretKey());
			req.header("Signature", sign);
			req.header("AccessKey", msg.getAccessKey());
			req.header("ProducerID", msg.getProducerId());
			req.queryString("topic", msg.getTopic());
			req.queryString("time", time);
			if (msg.getStartDeliverTime() != null) {
				long startDeliverTime = 0;
				if(msg.getStartDeliverTime().split("-").length > 0) {
					startDeliverTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(msg.getStartDeliverTime()).getTime();
				} else {
					startDeliverTime = System.currentTimeMillis() + Long.parseLong(msg.getStartDeliverTime());
				}
				req.queryString("startdelivertime", startDeliverTime);
			}
			req.body(msg.getBaby());
		
			HttpResponse<String> res = req.asString();
			if (res.getStatus() == 201) {
				return true;
			} else {
				log.error("post message error: {}", msg, res.getBody());
			}
		} catch (Exception e) {
			log.error("post message error: {}", msg, e);
		}
		return false;
	}
}
