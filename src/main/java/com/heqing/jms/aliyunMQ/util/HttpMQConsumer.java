package com.heqing.jms.aliyunMQ.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.impl.authority.AuthUtil;
import com.heqing.jms.aliyunMQ.bean.MyMessage;
import com.heqing.jms.aliyunMQ.bean.ResultMessage;
import com.heqing.jms.utils.MD5Util;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class HttpMQConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(HttpMQConsumer.class);

	private static final String NEWLINE = "\n";
	
	MyMessage msg;
	
	public HttpMQConsumer(MyMessage msg) {
		this.msg = msg;
	}

	public List<ResultMessage> pull() {
		if(msg.getTopic()==null || msg.getConsumerId()==null) {
			log.error("topic or consumerId is null");
			return null;
		}
		List<ResultMessage> result = null;
		try {
			long time = System.currentTimeMillis();
			GetRequest req = Unirest.get(msg.getUrl());
			String signString = msg.getTopic() + NEWLINE + msg.getConsumerId() + NEWLINE + time;
			String sign = AuthUtil.calSignature(signString.getBytes("UTF-8"), msg.getSecretKey());
			req.header("Signature", sign);
			req.header("AccessKey", msg.getAccessKey());
			req.header("ConsumerID", msg.getConsumerId());
			req.queryString("topic", msg.getTopic());
			req.queryString("time", time);
			req.queryString("num", 32);

			HttpResponse<String> res = req.asString();
			if (res.getStatus() == 200) {
				if (res.getBody() != null && !res.getBody().isEmpty()) {
					try {
						result = JSON.parseArray(res.getBody(), ResultMessage.class);
					} catch (Exception e) {
						log.error("get message error", e);
					}
				}
			}
		} catch (Exception e) {
			log.error("get message error", e);
		}
		return result;
	}

	public boolean delete(String msgHandle) {
		if(msg.getTopic()==null || msg.getConsumerId()==null) {
			log.error("topic or consumerId is null");
			return false;
		}
		
		try {
			long time = System.currentTimeMillis();
			HttpRequestWithBody req = Unirest.delete(msg.getUrl());
			String signString = msg.getTopic() + NEWLINE + msg.getConsumerId()+ NEWLINE + MD5Util.getInstance().getMD5String(msgHandle) + NEWLINE + time;
			String sign = AuthUtil.calSignature(signString.getBytes("UTF-8"), msg.getSecretKey());
			req.header("Signature", sign);
			req.header("AccessKey", msg.getAccessKey());
			req.header("ConsumerID", msg.getConsumerId());
			req.queryString("topic", msg.getTopic());
			req.queryString("time", time);
			req.queryString("msgHandle", msgHandle);
		
			HttpResponse<String> res = req.asString();
			if (res.getStatus() == 204) {
				return true;
			} else {
				log.error("delete message error: {}", msgHandle, res.getBody());
			}
		} catch (Exception e) {
			log.error("delete message error: {}", msgHandle, e);
		}
		return false;
	}

}
