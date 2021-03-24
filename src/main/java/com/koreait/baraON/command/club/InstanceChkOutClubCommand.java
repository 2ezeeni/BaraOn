package com.koreait.baraON.command.club;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ClubDao;

public class InstanceChkOutClubCommand implements ClubCommand {

	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");

		int cl_no = 0;
		if(request.getParameter("cl_no") != null) {
			cl_no = Integer.parseInt(request.getParameter("cl_no"));
		}
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
		
		int chkOutResult2 = clubDao.instanceChkOutClub(cl_no);
		
		boolean afterChkOut2 = false;
		if(chkOutResult2 > 0) { 
			afterChkOut2 = true;
		}
		
		rttr.addFlashAttribute("chkOutResult2", chkOutResult2);
		rttr.addFlashAttribute("afterChkOut2", afterChkOut2);
		
	}

}
