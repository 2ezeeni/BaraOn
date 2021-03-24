package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.AdminMemberDto;
import com.koreait.baraON.dto.AdminMemberDto2;

public interface AdminMemberDao {
	public List<AdminMemberDto> adminMemberList(int beginRecord, int endRecord);
	public int memberCount();
	public AdminMemberDto2 adminMemberView(int no);
	public int adminMemberDelete(int no);
	public int adminMemberUpdate(AdminMemberDto2 adminMemberDto2);
}
