package com.koreait.baraON.dao;

import java.util.List;
import java.util.Map;

import com.koreait.baraON.dto.FAQDto;

public interface FAQDao {

	public List<FAQDto> faqList(Map<String, Object> paramMap);
	public int totalRecord(Map<String, Object> paramMap);
	public FAQDto faqView(int no);
	public int faqInsert(String title, String content , int f_category);
	public int faqDelete(int no);
	public int faqUpdate(String title, String content ,int no);
	
	
	public List<FAQDto> indexFaq(int beginRecord, int endRecord);
}
