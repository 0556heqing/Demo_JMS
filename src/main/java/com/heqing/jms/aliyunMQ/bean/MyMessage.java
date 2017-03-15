package com.heqing.jms.aliyunMQ.bean;

import com.heqing.jms.utils.PropertyUtil;

public class MyMessage {

	private String accessKey = PropertyUtil.getProperty("config.properties", "aliyun.AccessKey");	
	private String secretKey = PropertyUtil.getProperty("config.properties", "aliyun.SecretKey");	
	private String url       = PropertyUtil.getProperty("config.properties", "aliyun.Url");	
	private String producerId;	//生产者ID，需提前配置
	private String consumerId;	//消费者者ID，需提前配置
	private String topic;		//主题
	private String key;			//消息的业务关键属性，请尽可能全局唯一
	private String tag;			//标签
	private String baby;		//消息体
	private String startDeliverTime;	//定时时间

	public MyMessage(String id, String type, String topic) {
		if(type.equals("producer")) this.producerId = id;
		else {this.consumerId = id; }
		this.topic = topic;
	}
	
	/**
	 * @return the accessKey
	 */
	public String getAccessKey() {
		return accessKey;
	}
	/**
	 * @param accessKey the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	/**
	 * @return the producerId
	 */
	public String getProducerId() {
		return producerId;
	}
	/**
	 * @param producerId the producerId to set
	 */
	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}
	/**
	 * @return the consumerId
	 */
	public String getConsumerId() {
		return consumerId;
	}
	/**
	 * @param consumerId the consumerId to set
	 */
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * @return the baby
	 */
	public String getBaby() {
		return baby;
	}
	/**
	 * @param baby the baby to set
	 */
	public void setBaby(String baby) {
		this.baby = baby;
	}
	/**
	 * @return the startDeliverTime
	 */
	public String getStartDeliverTime() {
		return startDeliverTime;
	}
	/**
	 * @param startDeliverTime the startDeliverTime to set
	 */
	public void setStartDeliverTime(String startDeliverTime) {
		this.startDeliverTime = startDeliverTime;
	}

}
