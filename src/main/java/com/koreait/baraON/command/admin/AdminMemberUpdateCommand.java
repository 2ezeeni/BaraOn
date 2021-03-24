package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminMemberDao;
import com.koreait.baraON.dto.AdminMemberDto2;

public class AdminMemberUpdateCommand implements AdminMemberCommand {

	@Override
		public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		AdminMemberDto2 adminMemberDto2 = (AdminMemberDto2)map.get("adminMemberDto2");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdminMemberDao adminMemberDao = sqlSession.getMapper(AdminMemberDao.class);
		resultMap.put("result", adminMemberDao.adminMemberUpdate(adminMemberDto2));
		
		return resultMap;
	}

}
