package com.koreait.baraON.command.club;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ClubDao;

public class UnLikeClubCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");

		int w_no = 0;
		if(request.getParameter("w_no") != null) {
			w_no = Integer.parseInt(request.getParameter("w_no"));
		}
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);

		int unlikeResult = clubDao.unlikeClub(w_no);
		
		model.addAttribute("unlikeResult", unlikeResult);
		
	}

}
