package com.koreait.baraON.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
	private int m_no;
	private String m_id;
	private String m_pw;
	private String m_phone;
	private String m_name;
	private String m_birth;
	private String m_nick;
	private String m_email;
	private Timestamp m_regDate;
	private int m_agreement;
}
