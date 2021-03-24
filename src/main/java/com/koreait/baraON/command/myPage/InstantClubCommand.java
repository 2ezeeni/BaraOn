package com.koreait.baraON.command.myPage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MyPageDao;
import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.ClubListDto;

public class InstantClubCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		int state = 0;
		int mNo = 0;
		int cPart = 1; // 정기 모임

		if(request.getParameter("state") != null) {
			state = Integer.parseInt(request.getParameter("state"));
		}
		
		MemberDto loginDto = (MemberDto)session.getAttribute("loginDto");
		if(loginDto != null) {
			if(loginDto.getM_no() != 0) {
				mNo = loginDto.getM_no();
			}
		}
		
		List<ClubListDto> list = null;
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		if(state == 0) {
			list = myPageDao.clubList(mNo, cPart);
		} else {
			list = myPageDao.lastClubList(mNo, cPart);
		}
		
		model.addAttribute("list", list);

	}

}
