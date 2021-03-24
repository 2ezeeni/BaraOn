package com.koreait.baraON.command.member;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MemberDao;
import com.koreait.baraON.dto.MemberDto;

public class MemberPwUpdateCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MemberDto memberDto  = (MemberDto)map.get("memberDto");
		memberDto.setM_pw(Sha256.sha256(memberDto.getM_pw()));
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		resultMap.put("result", memberDao.memberPwUpdate(memberDto));
		
		return resultMap;

	}

}
