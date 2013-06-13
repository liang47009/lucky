package com.yunfeng.lucky.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yunfeng.lucky.dto.DtailDto;
import com.yunfeng.lucky.dto.PropsDto;
import com.yunfeng.lucky.entity.User;
import com.yunfeng.lucky.service.PropsService;
import com.yunfeng.util.ConstantUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")
public class PropsController {

	@Resource
	private PropsService propsService;

	/**
	 * 购买道具
	 */
	@RequestMapping(value = "/buyProps")
	public String buyProps(@ModelAttribute("user") User user, int pid,
			Model model) {
		boolean b = false;

		if (ConstantUtil.isValid(pid)) {
			b = propsService.buyProps(user, pid);
		}
		if (b) {
			return "forward:/hot";
		}
		return "failed";
	}

	/**
	 * 所有成功领取
	 */
	@RequestMapping(value = "/sus")
	public String sus(Model model, HttpSession session) {
		List<PropsDto> list = propsService.findAllSuccess();
		model.addAttribute("sus", list);
		return "sus";
	}

	/**
	 * 详细信息
	 */
	@RequestMapping(value = "/detail")
	public String detail(int pid, Model model, HttpSession session) {
		DtailDto dd = propsService.findDetail(pid);
		model.addAttribute("dd", dd);
		return "detail";
	}
}
