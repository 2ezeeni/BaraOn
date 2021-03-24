package com.koreait.baraON.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDto {
	private int res_no;				// 인덱스
	private int m_no;					// 예약 회원 번호
	private int po_no;					// 옵션번호
	private Date res_date;				// 예약날짜
	private int res_people;			// 예약인원
	private String res_state;			// 예약상태 = {결제대기, 예약대기, 예약완료, 사용완료, 예약취소}
	private String res_email;			// 예약자메일
	private int res_price;				// 결제금액
	private int res_applynum;			// 카드승인번호
	private String res_purpose;		// 사용목적
	private String res_requirement;	// 요구사항
}
