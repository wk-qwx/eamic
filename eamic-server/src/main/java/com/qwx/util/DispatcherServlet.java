package com.qwx.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

	private static final long serialVersionUID = -6524984532025127425L;

	private static final String REQUEST_KEY_BASE = "base";

	@Override
	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		exportContextPath(request, mv);
		super.render(mv, request, response);
	}

	/**
	 * 将contextPath加入环境变量
	 * 
	 * @param request
	 * @param mv
	 */
	protected void exportContextPath(HttpServletRequest request, ModelAndView mv) {
		mv.addObject(REQUEST_KEY_BASE, request.getContextPath());
	}
}
