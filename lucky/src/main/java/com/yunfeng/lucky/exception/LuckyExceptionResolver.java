package com.yunfeng.lucky.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.yunfeng.util.Log;

public class LuckyExceptionResolver implements HandlerExceptionResolver {

	/**
	 * 处理异常跳转
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String url = request.getRequestURI();
		String param = request.getQueryString();
		if (param != null && param.length() > 0) {
			url += ("?" + param);
		}
		Log.error("异常跳转: " + url, ex);// 把漏网的异常信息记入日志
		return new ModelAndView("error");
	}
}