package com.koreait.baraON.command.place;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dto.SellerDto;

public class PlaceSearchCommand implements PlaceCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		HttpServletRequest request = (HttpServletRequest)model.asMap().get("request");
		boolean isSeller = false;
		
		try {
			SellerDto sellerDto = (SellerDto)request.getSession().getAttribute("loginDto");
			if(sellerDto != null) {
				isSeller=true;
			}
		} catch (ClassCastException e) {
			isSeller=false;
		}
		
		String query = request.getParameter("query");
		model.addAttribute("list", placeDao.querySearch(query));
		model.addAttribute("isSeller", isSeller);
		model.addAttribute("categoryList", placeDao.placeCategoryList());
		
	}

}
