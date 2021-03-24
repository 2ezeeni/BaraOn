package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.ClubDto;
import com.koreait.baraON.dto.ClubListDto;
import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.WishListDto;

public interface ClubDao {
	
	public List<ClubDto> clubList();
	
	public List<ClubDto> instanceClubList();
	
	public int finishDay(int c_no);
	
	public int instancefinishDay(int c_no);
	
	public int clubInsert(int m_no, String c_title, String c_desc, int c_min, int c_max, String c_startDate, String c_endDate, String c_content, String c_mainImg);
	
	public int instanceclubInsert(int m_no, String c_title, String c_desc, int c_min, int c_max, String c_startDate, String c_endDate, String c_content, String c_mainImg);
	
	public ClubDto clubView(int c_no);
	
	public MemberDto clubView2(int c_no);
	
	public int clubDelete(int c_no);

	public int clubUpdate(String c_mainImg, String c_desc, int c_min, int c_max, String c_content, int c_no);
	
	public ClubListDto findJoin(int c_no, int m_no);
	
	public WishListDto findLike(int c_no, int m_no);
	
	public int joinClub(int c_no, int m_no);
	
	public int chkOutClub(int cl_no);
	
	public int likeClub(int c_no, int m_no);
	
	public int unlikeClub(int w_no);
	
	public int instanceJoinClub(int c_no, int m_no);
	
	public int instanceChkOutClub(int cl_no);
	
	public int instanceLikeClub(int c_no, int m_no);
	
	public int instanceUnlikeClub(int w_no);
	
 }