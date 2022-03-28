package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testRequest() {
		String requestResult = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/", String.class);
		System.out.println(requestResult);
		Assertions.assertThat(requestResult).contains("count");
	}

	@Test
	public void testPost() {
		try {
			String url = "http://127.0.0.1:" + port + "/count";
			String inputStr = "aaa,bbb,ccc";
			String startStr = "a";
			String endStr ="c";
			String minLength = "1";
			String maxLength = "6";
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("inputStr", inputStr);
			map.add("startStr", startStr);
			map.add("endStr", endStr);
			map.add("minLength", minLength);
			map.add("maxLength", maxLength);
			String result = restTemplate.postForObject(url, map, String.class);
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
