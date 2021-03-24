package com.koreait.baraON.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDto {
	private int rv_no;
	private int m_no;
	private int p_no;
	private int rv_delete;
	private int rv_star;
	private String rv_img;
	private Date rv_postDate;
	private Date rv_modifyDate;
	// 작성일 필요할거같다.
	private String rv_content;
}