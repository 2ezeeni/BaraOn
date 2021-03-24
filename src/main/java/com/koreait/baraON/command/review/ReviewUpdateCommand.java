package com.koreait.baraON.command.review;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.ReviewDao;
import com.koreait.baraON.dto.ReviewDto;

public class ReviewUpdateCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model){
		ReviewDto reviewDto = (ReviewDto)model.asMap().get("reviewDto");
		
		ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		resultMap.put("result", reviewDao.reviewUpdate(reviewDto));
		
		return resultMap;
	}
}
