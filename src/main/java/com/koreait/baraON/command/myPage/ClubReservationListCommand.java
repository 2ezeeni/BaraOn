package com.koreait.baraON.command.myPage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MyPageDao;
import com.koreait.baraON.dto.ClubReservationListDto;
import com.koreait.baraON.dto.MemberDto;

public class ClubReservationListCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
		List<ClubReservationListDto> list = myPageDao.clubReservationList(memberDto.getM_no());
		
		model.addAttribute("list", list);
		
	}

}
