package com.koreait.baraON.command.myPage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MyPageDao;
import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.SellerDto;
import com.koreait.baraON.dto.WishListDto;

public class WishListCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		MemberDto loginDto = (MemberDto)session.getAttribute("loginDto");
		
		int no = 0;
		if(loginDto.getM_no() != 0) {
			no = loginDto.getM_no();
		}
		List<WishListDto> list = myPageDao.wishList(no);
		
		session.setAttribute("list", list);
		
	}

}
