package com.koreait.baraON.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MemberDao;
import com.koreait.baraON.dto.MemberDto;

public class KakaoLoginCommand {

	public boolean execute(SqlSession sqlSession, Model model) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String id = map.get("id").toString();
		
		MemberDto loginMemberDto = memberDao.memberKakaoLogin(id);
		
		if(loginMemberDto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginDto", loginMemberDto); //  로그인 정보 session에 저장
			session.setAttribute("grade", "member"); 
			return true;
		} else {
			return false;
		}

	}
}
