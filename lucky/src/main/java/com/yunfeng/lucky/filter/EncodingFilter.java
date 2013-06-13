package com.yunfeng.lucky.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yunfeng.lucky.entity.User;

/**
 * 以 UTF-8 输入输出
 * 
 * @author CurlyMaple
 * 
 */
public class EncodingFilter implements Filter {

	private String config;

	public void destroy() {
		this.config = null;
	}

	/**
	 * name="FilterChar" <br>
	 * init-param name="config" value="utf-8"<br>
	 * mapping url-pattern="/*"<br>
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding(config);
		request.setCharacterEncoding(config);
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		if (uri.indexOf("favicon.ico") != -1) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/resources/favicon.ico");
			dispatcher.forward(request, response);
			return;
		}
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("user");
		if (null == obj) {
			if (uri.indexOf("/manager") == -1) {
				User user = new User();
				user.setId(1);
				session.setAttribute("user", user);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/hot");
				dispatcher.forward(request, response);
				return;
			}
			String[] s = uri.split("/");
			if (!s[s.length - 1].equals("login")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/manager/");
				dispatcher.forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 获得web.xml中初始化的参数
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config.getInitParameter("chars");
	}

}