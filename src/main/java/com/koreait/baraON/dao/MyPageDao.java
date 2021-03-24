package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.ClubDto;
import com.koreait.baraON.dto.ClubListDto;
import com.koreait.baraON.dto.ClubReservationListDto;
import com.koreait.baraON.dto.WishListDto;

public interface MyPageDao {
	public List<WishListDto> wishList(int no);
	public int wishDelete(int wNo);
	public List<ClubListDto> clubList(int mNo, int cPart);
	public List<ClubListDto> lastClubList(int mNo, int cPart);
	public int clubListDelete(int clNo);
	public int canMemberDelete1(int no);
	public int canMemberDelete2(int no);
	public int infoMemberDelete1(int no);
	public int infoMemberDelete2(int no);
	public int canSellerDelete(int no);
	public int infoSellerDelete1(int no);
	public int infoSellerDelete2(int no);
	public int clubCount(int cNo);
	public List<ClubDto> clubManageList(int mNo);
	public List<ClubDto> lastClubManageList(int mNo);
	public ClubDto clubManageDetail(int cNo);
	public List<ClubReservationListDto> clubReservationList(int mNo);
	public List<ClubListDto> memberInfo(int cNo);
	public int addCard(int clNo);
	public int cardCount(int clNo);
	public int clubMemberDelete(int clNo);
	
}
