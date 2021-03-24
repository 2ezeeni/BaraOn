package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.AdminSellerDto;
import com.koreait.baraON.dto.AdminSellerDto2;

public interface AdminSellerDao {
	public List<AdminSellerDto> adminSellerList(int beginRecord, int endRecord);
	public int sellerCount();
	public AdminSellerDto2 adminSellerView(int no);
	public int adminSellerDelete(int no);
	public int adminSellerUpdate(AdminSellerDto2 adminSellerDto2);
}
