package com.koreait.baraON.command.member;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public class MemberJoin2Command {

	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		String agreeNo=(String)map.get("agreeNo");
		int m_agreement = Integer.parseInt(agreeNo);
		model.addAttribute("m_agreement", m_agreement);
		
	}
}
