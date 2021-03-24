package com.koreait.baraON.command.review;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.ReviewDao;

public class ReviewListAppendCommand {
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);

		int rn = (int)model.asMap().get("rn");
		int p_no = (int)model.asMap().get("p_no");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("reviewList", reviewDao.reviewListAppend(p_no, rn+1, rn+3));
		
		return resultMap;
	}
}
