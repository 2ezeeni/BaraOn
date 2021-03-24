package com.koreait.baraON.command.place;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dao.PlaceOptionDao;
import com.koreait.baraON.dto.PlaceCategoryDto;
import com.koreait.baraON.dto.PlaceDto;
import com.koreait.baraON.dto.PlaceOptionDto;

public class PlaceUpdatePageCommand implements PlaceCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		int p_no = (int)model.asMap().get("p_no");
		List<PlaceCategoryDto> categoryList = placeDao.placeCategoryList();
		
		List<PlaceOptionDto> placeOptionList = placeDao.getPlaceOptionList(p_no);
		
		PlaceDto placeDto = placeDao.getPlaceDto(p_no);
		PlaceCategoryDto placeCategoryDto = placeDao.getPlaceCategoryByPCNo(placeDto.getPc_no());

		model.addAttribute("placeDto", placeDto);
		model.addAttribute("placeCategoryDto", placeCategoryDto);
		model.addAttribute("placeOptionList", placeOptionList);
		model.addAttribute("categoryList", categoryList);
	}

}
