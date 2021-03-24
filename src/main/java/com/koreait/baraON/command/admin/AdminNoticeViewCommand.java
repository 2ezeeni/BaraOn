package com.koreait.baraON.command.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.board.NoticeCommand;
import com.koreait.baraON.dao.NoticeDao;

public class AdminNoticeViewCommand implements NoticeCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map  = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		model.addAttribute("noticeDto", noticeDao.noticeView(no));
	}

}
