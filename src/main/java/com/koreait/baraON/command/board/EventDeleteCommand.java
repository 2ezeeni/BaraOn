package com.koreait.baraON.command.board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.EventDao;
import com.koreait.baraON.dao.NoticeDao;

public class EventDeleteCommand implements NoticeCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		EventDao eventDao = sqlSession.getMapper(EventDao.class);
		
		int deleteResult = eventDao.eventDelete(no);
		
		rttr.addFlashAttribute("deleteResult", deleteResult);
		rttr.addFlashAttribute("afterDelete", true);
	}

}
