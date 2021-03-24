package com.koreait.baraON.command.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.AdminSellerDao;
import com.koreait.baraON.dto.AdminSellerDto2;

public class AdminSellerUpdateCommand implements AdminMemberCommand {

	@Override
		public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		AdminSellerDto2 adminSellerDto2 = (AdminSellerDto2)map.get("adminSellerDto2");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		AdminSellerDao adminSellerDao = sqlSession.getMapper(AdminSellerDao.class);
		resultMap.put("result", adminSellerDao.adminSellerUpdate(adminSellerDto2));
		
		return resultMap;
	}

}
