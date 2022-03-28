package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.entity.Rule;
import com.example.demo.service.WordService;
import com.example.demo.web.WordController;

@SpringBootTest
@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
class WordControllerMockTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WordController wordController;

	@MockBean
	private WordService wordService;

    @BeforeEach
    void setUp() {
    	List<String> resultList = new ArrayList<String>();
    	Mockito.when(wordService.countWords(Mockito.any(),Mockito.any())).thenReturn(resultList);
    }

	@Test
	public void testPost() throws Exception {
		String inputStr = "aaa,bbb,ccc";
		String startStr = "a";
		String endStr ="";
		String minLength = "1";
		String maxLength = "6";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/count")// .sessionAttr("adminid", "1111")
				.param("inputStr", inputStr).param("startStr", startStr ).param("endStr", endStr).param("minLength", minLength).param("maxLength", maxLength))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		
		assertEquals("word.html", mvcResult.getModelAndView().getViewName());
		MockHttpServletResponse response = mvcResult.getResponse();
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
	}
	@Test 
	public void testService() {
		String[] strs = {"aaa","bbb","csd","aadgaa"};
		List<String> inputList = Stream.of(strs).collect(Collectors.toList());
		Rule rule = new Rule();
//		rule.setStartStr("aa");
		List<String> resultList = wordService.countWords(inputList, rule);
		System.out.println(resultList);
	}
}
