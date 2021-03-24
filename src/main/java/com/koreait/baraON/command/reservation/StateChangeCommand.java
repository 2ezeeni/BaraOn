package com.koreait.baraON.command.reservation;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.ReservationDao;
import com.koreait.baraON.dto.ReservationDto;

public class StateChangeCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		ReservationDto reservationDto = (ReservationDto) model.asMap().get("reservationDto");
		
		ReservationDao reservationDao = sqlSession.getMapper(ReservationDao.class);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", reservationDao.stateChange(reservationDto));
		
		return resultMap;
	}

}