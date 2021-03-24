package com.koreait.baraON.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto {
	
	private int w_no;
	private int c_no;
	private int m_no;
	private String c_title;
	private String c_content;
	private String c_mainimg;
}
