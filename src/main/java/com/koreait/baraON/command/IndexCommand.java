package com.koreait.baraON.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.ClubDao;
import com.koreait.baraON.dao.EventDao;
import com.koreait.baraON.dao.FAQDao;
import com.koreait.baraON.dao.NoticeDao;
import com.koreait.baraON.dao.PlaceDao;

public class IndexCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		EventDao eventDao = sqlSession.getMapper(EventDao.class);
		FAQDao faqDao = sqlSession.getMapper(FAQDao.class);
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		
		
		model.addAttribute("eventList", eventDao.eventList(1, 6));
		model.addAttribute("faqList",faqDao.indexFaq(1, 3));
		model.addAttribute("noticeList", noticeDao.noticeList(1, 3));
		model.addAttribute("clubList",clubDao.clubList());
		model.addAttribute("placeList",placeDao.placeList());
		
	}

}
