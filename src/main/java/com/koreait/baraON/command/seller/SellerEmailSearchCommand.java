package com.koreait.baraON.command.seller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.SellerDao;

public class SellerEmailSearchCommand implements SellerCommand{

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		String s_email = (String)map.get("s_email");

		SellerDao sellerDao = sqlSession.getMapper(SellerDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", sellerDao.sellerEmailSearch(s_email));
		
		return resultMap;
	}
}