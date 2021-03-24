package com.koreait.baraON.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOptionDto {

	private int po_no;
	private int p_no;
	private String po_name;
	private int po_min;
	private int po_max;
	private int po_dayPrice;
	private int po_holiday;
	// private int po_addPrice;
	private String po_img;
	private String po_fxility;
	// name 필요할듯..
	// 추가가격은 필요없을듯
}
