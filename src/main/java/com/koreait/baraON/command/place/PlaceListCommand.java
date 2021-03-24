package com.koreait.baraON.command.place;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dto.PlaceListDto;
import com.koreait.baraON.dto.SellerDto;

public class PlaceListCommand implements PlaceCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		
		HttpServletRequest request = (HttpServletRequest)model.asMap().get("request");
		
		String strPc_no = request.getParameter("categoryNo");
		int pc_no = 0;
		List<PlaceListDto> list = null;
		if (strPc_no !=null && !strPc_no.isEmpty() ) {
			pc_no = Integer.parseInt(strPc_no);
			list = placeDao.getPlaceListDtoByCategory(pc_no);
		} else {
			list = placeDao.placeList();
		}
		
		// 현재 로그인 된 회원이 seller인지 확인
		boolean isSeller = false;
		
		try {
			SellerDto sellerDto = (SellerDto)request.getSession().getAttribute("loginDto");
			if(sellerDto != null) {
				isSeller=true;
			}
		} catch (ClassCastException e) {
			isSeller=false;
		}
		StringBuffer sb = new StringBuffer();
		sb.trimToSize();
		sb.append("[");
		for(int i = 0, size=list.size();i<size;i++) {
			if(i!=(size-1)) {
				sb.append(list.get(i).getP_img()+",");
			} else {
				sb.append(list.get(i).getP_img()+"]");
				
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("isSeller", isSeller);
		model.addAttribute("imgList", sb.toString());
		model.addAttribute("categoryList", placeDao.placeCategoryList());
	}

}
