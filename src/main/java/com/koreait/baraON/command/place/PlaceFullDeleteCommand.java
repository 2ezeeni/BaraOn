package com.koreait.baraON.command.place;

import java.io.File;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dao.ReviewDao;
import com.koreait.baraON.dto.PlaceOptionDto;
import com.koreait.baraON.dto.ReviewDto;

public class PlaceFullDeleteCommand {
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model){
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
		
		int p_no = (int)model.asMap().get("p_no");
		List<ReviewDto> reviewList = reviewDao.reviewByPNo(p_no);
		List<PlaceOptionDto> optionList = placeDao.getPlaceOptionList(p_no);
		
		for(int i=0, size=optionList.size();i<size;i++) {
			File img = new File("resources/images/PlaceOptionImages", optionList.get(i).getPo_img());
			if(img.exists()) {
				img.delete();
			}
		}
		
		for(int i=0, size=reviewList.size();i<size;i++) {
			String reviewImg = reviewList.get(i).getRv_img().replace("\"", "").replace("[", "").replace("]", "");
			StringTokenizer st = new StringTokenizer(reviewImg);
			while(st.hasMoreTokens()) {
				File img = new File("resources/images/ReviewImages", st.nextToken());
				if(img.exists()) {
					img.delete();
				}
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", placeDao.placeFullDelete(p_no));
		
		return resultMap;
	}

}
