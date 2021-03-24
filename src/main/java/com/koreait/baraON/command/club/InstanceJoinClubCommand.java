package com.koreait.baraON.command.club;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ClubDao;

public class InstanceJoinClubCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);

		int	c_no = Integer.parseInt(request.getParameter("c_no"));
		int	m_no = Integer.parseInt(request.getParameter("m_no"));

		int joinResult2 = clubDao.instanceJoinClub(c_no, m_no);
	
		boolean afterJoin2 = false;
		if(joinResult2 > 0) { 
			afterJoin2 = true;
		}
		
		rttr.addFlashAttribute("joinResult2", joinResult2);
		rttr.addFlashAttribute("afterJoin2", afterJoin2);
		
	}

}
