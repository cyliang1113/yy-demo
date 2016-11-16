package cn.leo.springmvcdemo.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.leo.springmvcdemo.po.UserUser;

public class BaseController {
	public HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	public UserUser getSessionUser(HttpServletRequest request) {
		HttpSession session = getSession(request);
		UserUser user = (UserUser) session.getAttribute(Constant.USER_KEY_IN_SESSION);
		return user;
	}
}
