package com.koreait.baraON.command.seller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceCategoryDao;

public class SpaceViewPageCommand {

	public void execute(SqlSession sqlSession, Model model) {
		
		PlaceCategoryDao placeCategoryDao = sqlSession.getMapper(PlaceCategoryDao.class);
		model.addAttribute("categoryList",placeCategoryDao.pcList()); 
		
	}

}
