package com.example.demo.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Rule;
import com.example.demo.service.WordService;

/**
 * @author qiao
 *
 */
@Controller
public class WordController {
	final static Logger logger = LoggerFactory.getLogger(WordController.class);
	@Autowired
	WordService wordService;
	
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleUnknowException(Exception ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", ex.getClass().getSimpleName());
		map.put("message", ex.getMessage());
//		return new ModelAndView("error.html", Map.of("error", ex.getClass().getSimpleName(), "message", ex.getMessage()));
		return new ModelAndView("error.html", map);
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("word.html");
	}

	@PostMapping("/count")
	public ModelAndView wordCount(@RequestParam("inputStr") String inputStr, @RequestParam("startStr") String startStr,
			@RequestParam("minLength") Integer minLength, @RequestParam("endStr") String endStr,
			@RequestParam("maxLength") Integer maxLength) {
		Rule rule = new Rule();
		rule.setStartStr(startStr);
		rule.setMinLength(minLength);
		rule.setEndStr(endStr);
		rule.setMaxLength(maxLength);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inputStr", inputStr);
		map.put("rule", rule);
		try {
			String resultStr = "";
			int resultNum = 0;
			if(!inputStr.isEmpty()) {
				List<String> strList = new ArrayList<String>(Arrays.asList(inputStr.split(",")));
				List<String> resultList = wordService.countWords(strList, rule);
				if(resultList != null && resultList.size() > 0) {
					resultNum = resultList.size();
					if(resultNum == 1) {
						resultStr = resultList.get(0);
					} else {
						resultStr = resultList.stream().reduce((a,b) -> (a+","+b)).get();
					}
				}
				map.put("resultStr", resultStr);
				map.put("resultNum", resultNum);
//				return new ModelAndView("word.html", Map.of("resultStr", resultStr, "resultNum", resultNum, "inputStr", inputStr, "rule", rule));
				return new ModelAndView("word.html", map);
				
			} else {
				logger.error("Input string list is empty!");
				map.put("error", "Input string list is empty");
//				return new ModelAndView("word.html", Map.of("inputStr", inputStr, "rule", rule,  "error", "Input string list is empty"));
				return new ModelAndView("word.html", map);
			}
		} catch (RuntimeException e) {
			map.put("error", "Count failed");
//			return new ModelAndView("word.html", Map.of("inputStr", inputStr, "rule", rule,  "error", "Count failed"));
			return new ModelAndView("word.html", map);
		}
	}
}
