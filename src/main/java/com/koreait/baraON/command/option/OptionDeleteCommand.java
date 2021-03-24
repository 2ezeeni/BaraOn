package com.koreait.baraON.command.option;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceOptionDao;
import com.koreait.baraON.dto.PlaceOptionDto;

public class OptionDeleteCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model){
		
		int po_no = (int) model.asMap().get("po_no");
		PlaceOptionDao placeOptionDao = sqlSession.getMapper(PlaceOptionDao.class);
		PlaceOptionDto placeOptionDto = placeOptionDao.getPlaceOptionDto(po_no);
		File img = new File("resources/images/PlaceOptionImages", placeOptionDto.getPo_img());
		if(img.exists()) {
			img.delete();
		}
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("result", placeOptionDao.placeOptionDelete(po_no));
		return resultMap;
	}
}
