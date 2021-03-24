package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminMemberDao;
import com.koreait.baraON.dto.AdminMemberDto2;

public class AdminMemberViewCommand implements AdminMemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		int no = (int)map.get("no");
		
		AdminMemberDao adminMemberDao = sqlSession.getMapper(AdminMemberDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdminMemberDto2 adminMemberDto2 = adminMemberDao.adminMemberView(no);
		if (adminMemberDto2 != null) {
			resultMap.put("adminMemberDto2", adminMemberDto2);
			resultMap.put("result", true);
		} else {
			resultMap.put("result", false);
		}
		
		return resultMap;
	}

}
