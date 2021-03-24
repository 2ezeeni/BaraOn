package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.PlaceCategoryDto;
import com.koreait.baraON.dto.PlaceDto;
import com.koreait.baraON.dto.PlaceListDto;
import com.koreait.baraON.dto.PlaceOptionDto;
import com.koreait.baraON.dto.ReviewCheckDto;
import com.koreait.baraON.dto.ReviewListDto;
import com.koreait.baraON.dto.SellerDto;

public interface PlaceDao {

	public List<PlaceListDto> placeList();
	public List<PlaceCategoryDto> placeCategoryList();
	public List<PlaceListDto> getPlaceListDtoByCategory(int pc_no);
	public PlaceDto getPlaceDto(int p_no);
	public SellerDto getSellerDto(int s_no);
	public List<PlaceOptionDto> getPlaceOptionList(int p_no);
	public PlaceCategoryDto getPlaceCategoryByPCNo(int pc_no);
	public List<ReviewListDto> getReviewListOfViewPage(int p_no, int beginRecord, int endRecord);
	public int getTotalReviewCount(int p_no);
	public int placeInsert(PlaceDto placeDto);
	public int placeDelete(int p_no);
	public int placeUpdate(PlaceDto placeDto);
	public int placeFullDelete(int p_no);
	public List<PlaceListDto> allPlaceSearch(PlaceDto placeDto);
	public List<PlaceListDto> placeCaSearch(PlaceDto placeDto);
	public List<PlaceListDto> allPlaceSearch2(PlaceDto placeDto);
	public List<PlaceListDto> placeCaSearch2(PlaceDto placeDto);
	public List<ReviewCheckDto> placeAdminList(int s_no, int beginRecord, int endRecord);
	public int placeCount();
	public List<PlaceListDto> querySearch(String query);
	
}
