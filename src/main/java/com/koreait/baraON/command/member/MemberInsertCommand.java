package com.koreait.baraON.command.member;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MemberDao;
import com.koreait.baraON.dto.MemberDto;

public class MemberInsertCommand{

	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MemberDto memberDto = (MemberDto)map.get("memberDto");
		memberDto.setM_pw(Sha256.sha256(memberDto.getM_pw()));
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		memberDao.memberInsert(memberDto);
		
	}

}
