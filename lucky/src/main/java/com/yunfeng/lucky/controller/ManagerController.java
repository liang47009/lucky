package com.yunfeng.lucky.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.yunfeng.lucky.dto.EmailForm;
import com.yunfeng.lucky.dto.ManageDto;
import com.yunfeng.lucky.dto.PropsForm;
import com.yunfeng.lucky.entity.Email;
import com.yunfeng.lucky.entity.Manager;
import com.yunfeng.lucky.service.EmailService;
import com.yunfeng.lucky.service.ManagerService;
import com.yunfeng.lucky.service.PropsService;
import com.yunfeng.util.FileUtil;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

	@Resource
	private PropsService propsService;
	@Resource
	private EmailService emailService;
	@Resource
	private ManagerService managerService;

	@RequestMapping(value = "/")
	public String manager() {
		return "manager/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, Model model)
			throws Exception {
		Manager m = managerService.findByNamePass(username, password);
		if (null == m) {
			return "manager/login";
		} else {
			return "manager/index";
		}
	}

	@RequestMapping(value = "/publish")
	public String publish(Model model) {
		model.addAttribute("pf", new PropsForm());
		return "manager/publish";
	}

	@RequestMapping(value = "/manage")
	public String manage(Model model) {
		List<ManageDto> list = managerService.getAllProps();
		model.addAttribute("m", list);
		return "manager/manage";
	}

	@RequestMapping(value = "/email")
	public String email(Model model) throws Exception {
		Email email = emailService.find(Email.class, 1);
		if (null == email) {
			email = new Email();
		}
		model.addAttribute("e", email);
		return "manager/email";
	}

	@RequestMapping(value = "/addProps")
	public String addProps(PropsForm propsForm, MultipartFile logo, Model model)
			throws Exception {
		if (logo.getSize() > 0) {
			String fileName = FileUtil.generateFileName(logo);
			FileUtil.writeFile(logo, fileName);
			propsForm.setImgUrl(fileName);
		}
		propsService.addOrEditProps(propsForm);
		return "success";
	}

	@RequestMapping(value = "/editProps")
	public String editProps(int pid, Model model) throws Exception {
		PropsForm pf = managerService.editProps(pid);
		if (null == pf) {
			return "error";
		}
		model.addAttribute("pf", pf);
		return "manager/editProps";
	}

	@RequestMapping(value = "/delProps")
	public String delProps(int pid, Model model) throws Exception {
		managerService.delProps(pid);
		return "success";
	}

	@RequestMapping(value = "/addEmail")
	public String addEmail(EmailForm ef, Model model) throws Exception {
		emailService.editEmail(ef);
		return "success";
	}

}