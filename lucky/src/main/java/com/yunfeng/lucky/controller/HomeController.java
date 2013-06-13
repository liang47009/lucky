package com.yunfeng.lucky.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunfeng.lucky.entity.Props;
import com.yunfeng.lucky.manager.DtoManager;
import com.yunfeng.lucky.service.PropsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Resource
	private DtoManager dtoManager;
	@Resource
	private PropsService propsService;

	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/")
	public String index(Model model, HttpSession session) throws Exception {
		List<Props> list = propsService.findAllHot();
		model.addAttribute("hot", dtoManager.toHotProps(list));
		return "hot";
	}

	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/hot")
	public String hot(Model model, HttpSession session) throws Exception {
		// List<Props> list = propsService.findAllHot();
		// model.addAttribute("list", dtoManager.toHotProps(list));
		return index(model, session);
	}

	/**
	 * 即将开始的
	 */
	@RequestMapping(value = "/start")
	public String start(Model model, HttpSession session) {
		List<Props> list = propsService.findAllStart();
		model.addAttribute("start", dtoManager.toStartProps(list));
		return "start";
	}

	/**
	 * 即将开始的
	 */
	@RequestMapping(value = "/rule")
	public String rule(Model model, HttpSession session) {
		return "rule";
	}

	/**
	 * 即将开始的
	 */
	@RequestMapping(value = "/info")
	public String info(Model model, HttpSession session) {
		return "info";
	}

}