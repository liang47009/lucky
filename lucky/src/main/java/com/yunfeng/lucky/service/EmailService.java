package com.yunfeng.lucky.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.yunfeng.lucky.dao.EmailDao;
import com.yunfeng.lucky.dto.EmailForm;
import com.yunfeng.lucky.entity.Email;
import com.yunfeng.lucky.entity.Props;

@Service
public class EmailService extends BaseService<Email> {

	@Resource
	private EmailDao emailDao;
	@Resource
	private JavaMailSenderImpl javaMailSender;

	/**
	 * 修改Email信息
	 * 
	 * @param emailForm
	 */
	public void editEmail(EmailForm emailForm) {
		Email email = this.find(Email.class, emailForm.getId());
		if (null == email) {
			email = new Email();
		}
		changeProperties(emailForm, email);
		this.saveOrUpdate(email);
	}

	/**
	 * 发送邮件
	 * 
	 * @param p
	 * @param email
	 * @param mailto
	 */
	public void sendMail(Props p, Email email, String mailto) {
		validEmail(email.getUsername(), email.getPassword());
		this.sendSimpleMailMessage(
				email.getUsername(),
				mailto,
				email.getSubject(),
				email.getContext()
						.replace(email.getActivation(), p.getActivation())
						.replace(email.getUrl(), p.getLink()));
	}

	/**
	 * 转移数据
	 * 
	 * @param emailForm
	 * @param email
	 */
	private void changeProperties(EmailForm emailForm, Email email) {
		email.setActivation(emailForm.getActivation());
		email.setContext(emailForm.getContext());
		email.setPassword(emailForm.getPassword());
		email.setUsername(emailForm.getUsername());
		email.setSubject(emailForm.getSubject());
		email.setUrl(emailForm.getUrl());
	}

	/**
	 * 发送简单邮件 SimpleMailMessage
	 */
	private void sendSimpleMailMessage(String from, String to, String subject,
			String context) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);// 可选的
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(context);
		this.javaMailSender.send(mailMessage);
	}

	/**
	 * 重新设置用户名密码
	 * 
	 * @param username
	 * @param password
	 */
	private void validEmail(String username, String password) {
		String tempUserName = this.javaMailSender.getUsername();
		String tempPassWord = this.javaMailSender.getPassword();

		if (!username.equals(tempUserName) || !password.equals(tempPassWord)) {
			this.javaMailSender.setUsername(username);
			this.javaMailSender.setPassword(password);
		}
	}

	@Override
	@PostConstruct
	public void init() {
		this.baseDao = emailDao;
	}

}
