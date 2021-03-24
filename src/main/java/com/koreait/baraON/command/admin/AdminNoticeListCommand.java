package com.koreait.baraON.command.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.baraON.command.board.NoticeCommand;
import com.koreait.baraON.command.board.Paging;
import com.koreait.baraON.dao.NoticeDao;
import com.koreait.baraON.dto.NoticeDto;

public class AdminNoticeListCommand implements NoticeCommand {
	
	@Override
	public void execute(SqlSession sqlSession, Model model){
		
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int totalRecord = noticeDao.totalRecord();
		
		int recordPerPage = 10; 
		
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = endRecord < totalRecord ? endRecord : totalRecord;
		
		List<NoticeDto> list = noticeDao.noticeList(beginRecord , endRecord);
		
		String paging = Paging.getPaging("adminNoticeListPage.admin" , totalRecord, recordPerPage, page);
		
		model.addAttribute("list", list);
		model.addAttribute("paging" , paging);
		model.addAttribute("totalRecord" , totalRecord);
		model.addAttribute("page" , page);
		model.addAttribute("recordPerPage" , recordPerPage);
		
	}
}
