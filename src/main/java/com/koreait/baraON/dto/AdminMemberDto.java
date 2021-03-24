package com.koreait.baraON.dto;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminMemberDto {
	private int m_no;
	private String m_id;
	private String m_pw;
	private String m_phone;
	private String m_name;
	private String m_birth;
	private String m_nick;
	private String m_email;
	private Date m_regDate;
}
