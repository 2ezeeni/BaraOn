package com.koreait.baraON.command.seller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.SellerDao;
import com.koreait.baraON.dto.SellerDto;

public class SellerUpdateCommand implements SellerCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		
		SellerDto sellerDto  = (SellerDto)map.get("sellerDto");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SellerDao sellerDao = sqlSession.getMapper(SellerDao.class);
		
		resultMap.put("result", sellerDao.sellerUpdate(sellerDto));
		
		return resultMap;
	}
}
