package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Rule;
import com.example.demo.service.WordService;

/**
 * provide word processor service
 * 
 * @author qiao
 *
 */
@Component
public class WordServiceImpl implements WordService{
//	final static Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);

	/**
	 * @param inputList: String list before filter
	 * @param rule:      filter rule, including start string, end string, max
	 *                   length, min length, each condition can be null
	 * @return String list after filter, if all conditions are null, return original
	 *         string list
	 */
	@Override
	public List<String> countWords(List<String> inputList, Rule rule) {
		logger.info("Count String list...{}, filter rules: {} ", inputList, rule.toString());
		List<String> tmpList = inputList;
		if (inputList != null && inputList.size() > 0) {
			Integer minLen = rule.getMinLength();
			Integer maxLen = rule.getMaxLength();
			String startStr = rule.getStartStr();
			String endStr = rule.getEndStr();
			boolean filtered = false;
			if (minLen != null) {
				filtered = true;
				if (minLen < 0) {
					logger.error("minLen should not less than 0!");
				} else {
					tmpList = tmpList.stream().filter(s -> (s.length() > minLen)).collect(Collectors.toList());
					System.out.println("minLen" + tmpList);
				}
			}
			if (maxLen != null) {
				filtered = true;
				if (maxLen < 0) {
					logger.error("maxLen should not less than 0!");
				} else {
					tmpList = tmpList.stream().filter(s -> (s.length() < maxLen)).collect(Collectors.toList());
					System.out.println("minLen" + tmpList);
				}
			}
			if (startStr != null) {
				filtered = true;
				tmpList = tmpList.stream().filter(s -> (s.startsWith(startStr) || s.startsWith(startStr.toUpperCase())
						|| s.startsWith(startStr.toLowerCase()))).collect(Collectors.toList());
			}
			if (endStr != null) {
				filtered = true;
				tmpList = tmpList.stream().filter(s -> (s.endsWith(endStr) || s.endsWith(endStr.toUpperCase())
						|| s.endsWith(endStr.toLowerCase()))).collect(Collectors.toList());
			}
			if (!filtered) {
				logger.info("No filter entered!");
			}
		} else {
			logger.error("String list input error!");
			throw new RuntimeException("Count word failed.");
		}
		return tmpList;
	}

}
