package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.AdminReplyDto;

public interface AdminReplyDao {
	public List<AdminReplyDto> adminReplyList(int beginRecord, int endRecord);
	public int replyCount();
	public int adminReplyDelete(int no);
}
