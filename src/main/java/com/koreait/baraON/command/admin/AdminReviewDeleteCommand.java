package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminReviewDao;

public class AdminReviewDeleteCommand implements AdminMemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		int no = (int)map.get("no");
		
		AdminReviewDao adminReviewDao = sqlSession.getMapper(AdminReviewDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", adminReviewDao.adminReviewDelete(no));
		
		return resultMap;
	}

}
