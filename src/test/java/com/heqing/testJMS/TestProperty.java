package com.heqing.testJMS;

import org.junit.Test;

import com.heqing.jms.utils.PropertyUtil;

public class TestProperty {

	@Test
	public void read() {
		String pId = PropertyUtil.getProperty("config.properties", "aliyun.ProducerId");
		System.out.println("--->"+pId);
	}
}
