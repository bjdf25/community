package com.nowcoder.community;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class CommunityApplicationTests {

	void contextLoads() {
		HashMap<String, Object> map = new HashMap<>();
		System.out.println(map.get("abc"));
	}

}
