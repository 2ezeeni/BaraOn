package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminConfirmDao;
import com.koreait.baraON.dto.AdminConfirmDto;

public class AdminConfirmViewCommand implements AdminMemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		int no = (int)map.get("no");
		
		AdminConfirmDao adminConfirmDao = sqlSession.getMapper(AdminConfirmDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdminConfirmDto adminConfirmDto = adminConfirmDao.adminConfirmView(no);
		if (adminConfirmDto != null) {
			resultMap.put("adminConfirmDto", adminConfirmDto);
			resultMap.put("result", true);
		} else {
			resultMap.put("result", false);
		}
		
		return resultMap;
	}

}
