package com.koreait.baraON.command.reservation;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MemberDao;
import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dao.PlaceOptionDao;
import com.koreait.baraON.dao.ReservationDao;
import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.PlaceDto;
import com.koreait.baraON.dto.PlaceOptionDto;
import com.koreait.baraON.dto.ReservationDto;

public class ReservationPageCommand implements ReservationCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		HttpServletRequest request = (HttpServletRequest)model.asMap().get("request");
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		PlaceOptionDao placeOptionDao = sqlSession.getMapper(PlaceOptionDao.class);
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		ReservationDao reservationDao = sqlSession.getMapper(ReservationDao.class);
		
		
		int po_no = Integer.parseInt(request.getParameter("po_no"));
		Date res_date = Date.valueOf(request.getParameter("res_date"));
		
		int res_people = Integer.parseInt(request.getParameter("res_people"));
		
		MemberDto memberDto = (MemberDto)request.getSession().getAttribute("loginDto");
		int m_no = memberDto.getM_no();
		PlaceOptionDto placeOptionDto = placeOptionDao.getPlaceOptionDto(po_no);
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(res_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		boolean isWeekend = false;
		if(day==1 || day==7) {
			isWeekend = true;
		}
		
		PlaceDto placeDto = placeDao.getPlaceDto(placeOptionDto.getP_no());
				
		
		// 먼저 받아준 데이터를 바탕으로 reservation table에 삽입 후 그 no를 가져온다.
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setM_no(m_no);
		reservationDto.setPo_no(po_no);
		reservationDto.setRes_date(res_date);
		reservationDto.setRes_people(res_people);
		
		reservationDao.reserveInsert(reservationDto);
		
		model.addAttribute("isWeekend", isWeekend);
		model.addAttribute("sellerDto", memberDao.getSellerDto(placeDto.getS_no()));
		model.addAttribute("memberDto", memberDto);
		model.addAttribute("reservationDto", reservationDto);
		model.addAttribute("placeOptionDto", placeOptionDto);
		model.addAttribute("placeDto", placeDto);
		model.addAttribute("category",placeDao.getPlaceCategoryByPCNo(placeDto.getPc_no()).getPc_name());
		
		
	}

}
