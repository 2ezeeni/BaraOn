package com.koreait.baraON.dao;

import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.SellerDto;

public interface MemberDao {
	public MemberDto memberLogin(String id, String pw);
	public MemberDto memberKakaoLogin(String id);
	public SellerDto sellerLogin(String id, String pw);
	public MemberDto findMemberId(String name, String email);
	public SellerDto findSellerId(String name, String email);
	public MemberDto findMemberPw(String id, String name, String email);
	public SellerDto findSellerPw(String id, String name, String email);
	public int changeMemberPw(String pw, String id);
	public int changeSellerPw(String pw, String id);
	
	// place
	public SellerDto getSellerDto(int s_no);
	
	public int memberSearch(String m_id);
	public int memberNickSearch(String m_nick);
	public int memberPhoneSearch(String m_phone);
	public int memberEmailSearch(String m_email);
	public int memberInsert(MemberDto memberDto);
	public MemberDto memberView(int m_no);
	public String memberPwSearch(String m_id);
	public int memberPwUpdate(MemberDto memberDto);
	public int memberNickUpdate(MemberDto memberDto);
	public int memberUpdate(MemberDto memberDto);
	public int memberDelete(int m_no);
	
}