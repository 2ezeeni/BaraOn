package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.ReviewDto;
import com.koreait.baraON.dto.SellerDto;

public interface SellerDao {

	public String sellerPwSearch(int s_no);
	public int sellerPwUpdate(SellerDto sellerDto);
	public int sellerUpdate(SellerDto sellerDto);
	public int sellerSearch(String s_id);
	public List<ReviewDto> reviewSearch(ReviewDto reveiwDto);
	public int sellerPhoneSearch(String s_phone);
	public int sellerEmailSearch(String s_email);
	public int sellerInsert(SellerDto sellerDto);

}
