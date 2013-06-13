package com.yunfeng.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.URLDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件发送工具
 * 
 * @author CurlyMaple
 * 
 */
public class MailUtil {
	private static final String EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL,
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String email) {
		Matcher matcher = EMAIL_REGEX.matcher(email);
		return matcher.find();
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(validate("xiayunfeng2012@gmail.com"));
		sendSimpleMailMessage();
		// sendMimeMessage();
		// sendMimeMessageAttachment();
		// sendMimeMessageInline();
	}

	/**
	 * 创建邮件发送者
	 */
	private static JavaMailSender createJavaMailSender() {
		/*
		 * //从网上找的参考，说要添加这么多，实际测试了下，不需要这么多个 Properties properties = new
		 * Properties(); properties.setProperty("mail.debug", "true");
		 * properties.setProperty("mail.smtp.socketFactory.class",
		 * "javax.net.ssl.SSLSocketFactory");
		 * properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		 * properties.setProperty("mail.smtp.socketFactory.port", "465");
		 * properties.setProperty("mail.smtp.port", "465");
		 * properties.setProperty("mail.smtp.auth", "true");
		 */
		Properties properties = new Properties();
		properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.auth", "true");

		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setJavaMailProperties(properties);
		javaMailSender.setUsername("xiayunfeng2012@gmail.com");
		javaMailSender.setPassword("XLL#470093");
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(465);
		javaMailSender.setDefaultEncoding("UTF-8");
		return javaMailSender;
	}

	/**
	 * 发送简单邮件 SimpleMailMessage
	 */
	public static void sendSimpleMailMessage() {
		JavaMailSender javaMailSender = createJavaMailSender();

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("xiayunfeng2012@gmail.com");// 可选的
		mailMessage.setTo("105144919@qq.com");
		mailMessage.setSubject("Hello，你好啊");
		mailMessage.setText("那就随便说说吧" + new Date());

		javaMailSender.send(mailMessage);
	}

	/**
	 * 发送MIME类型邮件 MimeMessage
	 */
	public static void sendMimeMessage() throws MessagingException {
		JavaMailSender javaMailSender = createJavaMailSender();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		// 可选的，可以用来修改显示给接收者的名字
		// helper.setFrom("zywang@gmail.com", "ZYWANG");
		helper.setTo("zywang@iflysse.com");
		helper.setSubject("这就是个实验");
		helper.setText("<h1>你好</h1>", true);

		javaMailSender.send(mimeMessage);
	}

	/**
	 * 发送含附件的邮件
	 */
	public static void sendMimeMessageAttachment() throws MessagingException,
			UnsupportedEncodingException, MalformedURLException {
		JavaMailSender javaMailSender = createJavaMailSender();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		// 第二个参数设置为true，表示允许添加附件
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo("zywang@iflysse.com");
		helper.setSubject("发送含图片附件的邮件");
		// 第二个参数为true表示需要内容为HTML格式
		helper.setText("<h1>你好</h1>", true);
		// 需要对文件名进行转码
		helper.addAttachment(MimeUtility.encodeText("王.png"), new File(
				"d:\\王.png"));
		helper.addAttachment(MimeUtility.encodeText("Apache网站图标.gif"),
				new URLDataSource(new URL(
						"http://www.apache.org/images/asf_logo_wide.gif")));

		javaMailSender.send(mimeMessage);
	}

	/**
	 * 发送HTML格式含图片的邮件
	 */
	public static void sendMimeMessageInline() throws MessagingException,
			MalformedURLException {
		JavaMailSender javaMailSender = createJavaMailSender();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		// 第二个参数设置为true，表示允许添加附件
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo("zywang@iflysse.com");
		helper.setSubject("发送含图片附件的邮件");
		// 第二个参数为true表示需要内容为HTML格式
		helper.setText(
				"<h1>你好</h1><br><img src='cid:fileId'><img src='cid:abc'>",
				true);
		helper.addInline("fileId", new File("d:\\王.png"));
		helper.addInline("abc", new UrlResource(
				"http://www.apache.org/images/asf_logo_wide.gif"));

		javaMailSender.send(mimeMessage);
	}

}
