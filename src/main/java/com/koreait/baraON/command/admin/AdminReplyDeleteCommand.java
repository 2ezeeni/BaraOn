package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminReplyDao;

public class AdminReplyDeleteCommand implements AdminMemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		int no = (int)map.get("no");
		
		AdminReplyDao adminReplyDao = sqlSession.getMapper(AdminReplyDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", adminReplyDao.adminReplyDelete(no));
		
		return resultMap;
	}

}
