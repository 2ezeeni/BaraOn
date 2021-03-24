package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.AdminConfirmDto;
import com.koreait.baraON.dto.AdminConfirmDto2;

public interface AdminConfirmDao {
	public List<AdminConfirmDto> adminConfirmList(int beginRecord, int endRecord);
	public int confirmCount();
	public AdminConfirmDto adminConfirmView(int no);
	public int adminConfirmUpdate(AdminConfirmDto2 adminConfirmDto2);
}
