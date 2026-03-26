package com.getourguide.interview;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GetYourGuideApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(get("/activities"))
			.andExpect(status().isOk());
	}
}
