package com.koreait.baraON.command.review;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.ReviewDao;
import com.koreait.baraON.dto.ReviewDto;

public class ReviewDeleteCommand {
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		int rv_no = (int)model.asMap().get("rv_no");
		HttpServletRequest request= (HttpServletRequest)model.asMap().get("request");
		ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ReviewDto reviewDto = reviewDao.getReviewDto(rv_no);
		String filenames= null;
		try {
			filenames = reviewDto.getRv_img().replace("[", "").replace("]", "");
			filenames.replaceAll("\"", "");
			String[] filenameList = filenames.split(",");
			String realPath = request.getServletContext().getRealPath("resources/images/ReviewImages");
			for(int i = 0;i<filenameList.length;i++) {
				File file = new File(realPath, filenameList[i]);
				if (file.exists()) {
					file.delete();
				}
			}
			
		} catch (NullPointerException e) {
			
		}
		resultMap.put("result", reviewDao.reviewDelete(rv_no));
		
		return resultMap;
	}
}
