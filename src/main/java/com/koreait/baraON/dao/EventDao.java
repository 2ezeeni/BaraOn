package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.EventDto;

public interface EventDao {
	public List<EventDto> eventList(int beginRecord, int endRecord);
	
	public int totalRecord();
	
	public EventDto eventView(int no);
	
	public int eventInsert(EventDto eventDto);
	
	public int eventDelete(int no);
	
	public int eventUpdate(String title, String content , int no, String filename);
}
