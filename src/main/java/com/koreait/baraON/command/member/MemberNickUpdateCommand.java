package com.koreait.baraON.command.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MemberDao;
import com.koreait.baraON.dto.MemberDto;

public class MemberNickUpdateCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		MemberDto memberDto  = (MemberDto)map.get("memberDto");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		HttpSession session = request.getSession();
		
		resultMap.put("result", memberDao.memberNickUpdate(memberDto));
		
		session.setAttribute("loginDto", memberDao.memberView(memberDto.getM_no()));
		
		return resultMap;
	}

}
