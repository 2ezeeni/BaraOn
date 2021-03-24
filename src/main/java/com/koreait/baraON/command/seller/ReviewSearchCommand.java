package com.koreait.baraON.command.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.SellerDao;
import com.koreait.baraON.dto.ReviewDto;

public class ReviewSearchCommand implements SellerCommand{

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		
		ReviewDto reviewDto = (ReviewDto) map.get("reviewDto");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		SellerDao sellerDao = sqlSession.getMapper(SellerDao.class);
		
		List<ReviewDto> list = sellerDao.reviewSearch(reviewDto);
	
		resultMap.put("list", list);
		
		if(list.size() > 0) {	
			resultMap.put("result", true);
		}else {
			resultMap.put("result", false);
		}
		
		return resultMap;
		
	}

}
