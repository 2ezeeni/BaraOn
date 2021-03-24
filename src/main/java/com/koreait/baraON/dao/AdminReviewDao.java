package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.AdminReviewDto;

public interface AdminReviewDao {
	
	public List<AdminReviewDto> adminReviewList(int beginRecord, int endRecord);
	public int reviewCount();
	public int adminReviewDelete(int no);
}
