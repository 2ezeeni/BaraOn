package com.koreait.baraON.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewCheckDto {

	private String m_id;
	private int rv_star;
	private String rv_content;
	private String p_title;
	
}
