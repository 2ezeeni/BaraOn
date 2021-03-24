package com.koreait.baraON.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.baraON.command.reservation.ReservationPageCommand;
import com.koreait.baraON.command.reservation.ReservationUpdateCommand;
import com.koreait.baraON.dto.ReservationDto;

@Controller
public class ReservationController {

	private SqlSession sqlSession;
	private ReservationUpdateCommand reservationUpdateCommand;
	private ReservationPageCommand reservationPageCommand;
	
	@Autowired
	public void setCommand(SqlSession sqlSession,
							ReservationUpdateCommand reservationUpdateCommand,
							ReservationPageCommand reservationPageCommand) {
		this.sqlSession = sqlSession;
		this.reservationPageCommand = reservationPageCommand;
		this.reservationUpdateCommand = reservationUpdateCommand;
	}
	
	
	@RequestMapping(value="reservationPage.reservation", method=RequestMethod.POST)
	public String reservationPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		
		reservationPageCommand.execute(sqlSession, model);
		return "reservation/reservationPage";
	}
	
	@RequestMapping(value="reservationUpdate.reservation", method=RequestMethod.PUT, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> reservationUpdate(@RequestBody ReservationDto reservationDto, Model model) {
		model.addAttribute("reservationDto", reservationDto);
		
		return reservationUpdateCommand.execute(sqlSession, model);
	}
	
	
}
