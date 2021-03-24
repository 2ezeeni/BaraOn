package com.koreait.baraON.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminConfirmDto {
	private int s_no;
	private String p_name;
	private String p_info;
	private String p_addr;
	private String p_addrdetail;
	private String p_confirm;
}
