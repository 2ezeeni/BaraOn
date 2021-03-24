package com.koreait.baraON.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceListDto {
	
	private int p_no;
	private int rvCount;
	private String pc_name;
	private int minPrice;
	private int maxPeople;
	private String p_title;
	private String p_img;
	private String p_bname;
}
