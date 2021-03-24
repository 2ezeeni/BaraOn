package com.koreait.baraON.dao;

import java.util.List;

import com.koreait.baraON.dto.ReservationDto;

public interface ReservationDao {
	public int reserveInsert(ReservationDto reservationDto);
	public int reserveUpdate(ReservationDto reservationDto);
	
	// seller에서 가져다 쓰는 중
	public List<ReservationDto> reservationList(int s_no, int beginRecord, int endRecord);
	
	public int reservationCount();
	
	public int stateChange(ReservationDto reservationDto);
	
	public int stateCancle(ReservationDto reservationDto);
	
}
