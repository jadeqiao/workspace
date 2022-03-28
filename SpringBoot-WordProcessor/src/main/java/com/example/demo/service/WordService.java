package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Rule;

/**
 * provide word processor service
 * 
 * @author qiao
 *
 */
@Component
public interface WordService {
	final static Logger logger = LoggerFactory.getLogger(WordService.class);

	/**
	 * @param inputList: String list before filter
	 * @param rule:      filter rule, including start string, end string, max
	 *                   length, min length, each condition can be null
	 * @return String list after filter, if all conditions are null, return original
	 *         string list
	 */
	public List<String> countWords(List<String> inputList, Rule rule);

}
