package com.koreait.baraON.command.member;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class ChkIdCommand {
	public void execute(HttpServletResponse response, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String id = request.getParameter("id");
		String chkId = request.getParameter("chkId");
		
		Cookie cookie = null;
		
		if(chkId != null) {
			cookie = new Cookie("id", id);
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		} else {
			Cookie[] cookieList = request.getCookies();
			if(cookieList != null && cookieList.length != 0) {
				for(Cookie ck : cookieList) {
					if (ck.getName().equals("id")) {
						cookie = new Cookie("id", "");
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
				
		
	}
}
