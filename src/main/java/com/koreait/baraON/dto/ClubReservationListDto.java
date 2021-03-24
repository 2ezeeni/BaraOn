package com.koreait.baraON.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClubReservationListDto {
	private int res_no;
	private Date res_date;
	private int res_people;
	private String res_state;
	private int res_price;
	private String po_name;
	private String po_img;
	private String p_no;
	private String p_title;
	private String p_name;
	private String p_addr;
	private String p_addrDetail;
	private String p_img;
}
