package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminConfirmDao;
import com.koreait.baraON.dto.AdminConfirmDto2;

public class AdminConfirmUpdateCommand implements AdminMemberCommand {

	@Override
		public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		AdminConfirmDto2 adminConfirmDto2 = (AdminConfirmDto2)map.get("adminConfirmDto2");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdminConfirmDao adminConfirmDao = sqlSession.getMapper(AdminConfirmDao.class);
		resultMap.put("result", adminConfirmDao.adminConfirmUpdate(adminConfirmDto2));
		
		return resultMap;
	}

}
