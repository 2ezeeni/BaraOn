package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminSellerDao;
import com.koreait.baraON.dto.AdminSellerDto2;

public class AdminSellerViewCommand implements AdminMemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		int no = (int)map.get("no");
		
		AdminSellerDao adminSellerDao = sqlSession.getMapper(AdminSellerDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdminSellerDto2 adminSellerDto2 = adminSellerDao.adminSellerView(no);
		if (adminSellerDto2 != null) {
			resultMap.put("adminSellerDto2", adminSellerDto2);
			resultMap.put("result", true);
		} else {
			resultMap.put("result", false);
		}
		
		return resultMap;
	}

}
