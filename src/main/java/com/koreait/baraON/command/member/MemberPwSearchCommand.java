package com.koreait.baraON.command.member;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MemberDao;

public class MemberPwSearchCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		
		String m_id = (String)map.get("m_id");
		String m_pw = Sha256.sha256((String)map.get("m_pw"));
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("result", memberDao.memberPwSearch(m_id).equals(m_pw));

		return resultMap;
		
	}

}
