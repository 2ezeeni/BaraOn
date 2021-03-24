package com.koreait.baraON.command.club;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ClubDao;

public class InstanceLikeClubCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);

		int	c_no = Integer.parseInt(request.getParameter("c_no"));
		int	m_no = Integer.parseInt(request.getParameter("m_no"));

		int likeResult = clubDao.instanceLikeClub(c_no, m_no);
	
		model.addAttribute("likeResult", likeResult);
		
	}

}
