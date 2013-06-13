package com.yunfeng.lucky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ResultController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/success")
	public String success() {
		return "success";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/failed")
	public String failed() {
		return "failed";
	}

}
