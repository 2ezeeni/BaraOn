package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.NoticeDto;

public interface NoticeDao {
	public List<NoticeDto> noticeList(int beginRecord, int endRecord);
	
	public int totalRecord();
	
	public NoticeDto noticeView(int no);
	
	public int noticeInsert(String title, String content);
	
	public int noticeDelete(int no);
	
	public int noticeUpdate(String title, String content , int no);
}
