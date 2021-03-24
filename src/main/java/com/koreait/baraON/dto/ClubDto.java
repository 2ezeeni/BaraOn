package com.koreait.baraON.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClubDto {

	private int c_no;
	private int m_no;
	private String c_title;		
	private String c_desc;
	private String c_content;	
	private Date c_postDate;	
	private Date c_startDate;	
	private Date c_endDate;  	
	private int c_min;			
	private int c_max;			
	private String c_mainImg;
	private int c_delete;
	private int c_part;
	
}
