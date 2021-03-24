package com.koreait.baraON.command.myPage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MyPageDao;

public class ClubListDeleteCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");

		int clNo = 0;
		if(request.getParameter("clNo") != null) {
			clNo = Integer.parseInt(request.getParameter("clNo"));
		}
		
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		int deleteResult = myPageDao.clubListDelete(clNo);

		rttr.addFlashAttribute("deleteResult", deleteResult);
		rttr.addFlashAttribute("delete", true);
	}

}
