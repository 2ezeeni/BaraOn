package com.koreait.baraON.command.myPage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MyPageDao;
import com.koreait.baraON.dto.ClubDto;
import com.koreait.baraON.dto.ClubListDto;
import com.koreait.baraON.dto.ClubReservationListDto;
import com.koreait.baraON.dto.MemberDto;

public class ClubManageDetailCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int cNo = 0;
		int state = 0;    // 진행중, 지난
		int c_part = 0;   // 정기, 번개
		if(request.getParameter("c_no") != null) {
			cNo = Integer.parseInt(request.getParameter("c_no"));
		}
		if(request.getParameter("state") != null) {
			state = Integer.parseInt(request.getParameter("state"));
		}
		if(request.getParameter("c_part") != null) {
			c_part = Integer.parseInt(request.getParameter("c_part"));
		}
		
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		/** 모임 내용 **/
		ClubDto clubDto = myPageDao.clubManageDetail(cNo);
		/** 예약 내용 **/
		HttpSession session = (HttpSession)request.getSession();
		MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
		int mNo = memberDto.getM_no();
		List<ClubReservationListDto> reservationList = myPageDao.clubReservationList(mNo);
		
		/** 참가 멤버 **/
		int memberCount = myPageDao.clubCount(cNo);
		List<ClubListDto> clubList = null;
		clubList = myPageDao.memberInfo(cNo);
		
		model.addAttribute("clubDto", clubDto);
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("clubList", clubList);
		model.addAttribute("state", state);
	}

}
