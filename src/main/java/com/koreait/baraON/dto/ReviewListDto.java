package com.koreait.baraON.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewListDto {
	private int rn;
	private int rv_no;
	private int m_no;
	private int p_no;
	private int rv_delete;
	private int rv_star;
	private String rv_img;
	private Timestamp rv_postDate;
	private Timestamp rv_modifyDate;
	// 작성일 필요할거같다.
	
	// view page에서 쓰기 위해 추가한 필드
	private String rv_content;
	private String m_nick;
	private String m_id;
}
