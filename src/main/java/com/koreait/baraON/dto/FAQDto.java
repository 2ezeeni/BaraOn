package com.koreait.baraON.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FAQDto {
	private int f_no;
	private String f_title;
	private String f_content;
	private Date f_regDate;
	private int f_category;
}
