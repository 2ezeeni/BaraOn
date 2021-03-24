package com.koreait.baraON.command.club;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.ClubDao;
import com.koreait.baraON.dto.MemberDto;

public class InstanceClubViewCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
		
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		int m_no = 0;
		
		HttpSession session = request.getSession(); 
		try {
			MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
			m_no = memberDto.getM_no();
		} catch (Exception e) {
			
		}
		
		model.addAttribute("clubListDto", clubDao.findJoin(c_no, m_no));
		model.addAttribute("wishListDto", clubDao.findLike(c_no, m_no));
		model.addAttribute("memberDto", clubDao.clubView2(c_no));
		model.addAttribute("clubDto", clubDao.clubView(c_no));

	}

}
