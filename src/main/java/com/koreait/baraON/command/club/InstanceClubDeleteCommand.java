package com.koreait.baraON.command.club;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ClubDao;

public class InstanceClubDeleteCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
		
		int c_no = 0;
		if(request.getParameter("c_no") != null) {
			c_no = Integer.parseInt(request.getParameter("c_no"));
		}
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);

		int deleteResult = clubDao.clubDelete(c_no);
		
		String c_mainImg = request.getParameter("c_mainImg");
		String realPath = request.getServletContext().getRealPath("resources/images/club");
		
		File file = new File(realPath, c_mainImg);
		
		if (file.exists()) {
			file.delete();
		}

		boolean afterDelete = false;
		if(deleteResult > 0) { 
			afterDelete = true;
		}
		
		rttr.addFlashAttribute("deleteResult", deleteResult);
		rttr.addFlashAttribute("afterDelete", afterDelete);
		
	}

}
