package com.koreait.baraON.command.member;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MemberDao;

public class MemberPhoneSearchCommand implements MemberCommand{

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		String m_phone = (String)map.get("m_phone");

		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", memberDao.memberPhoneSearch(m_phone));
		
		return resultMap;
	}
}