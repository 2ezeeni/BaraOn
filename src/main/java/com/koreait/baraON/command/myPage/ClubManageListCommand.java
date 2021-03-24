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
import com.koreait.baraON.dto.MemberDto;

public class ClubManageListCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		
		int state = 0;
		if(request.getParameter("state") != null) {
			state = Integer.parseInt(request.getParameter("state"));
		}
		
		MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
		int mNo = 0;
		if(memberDto != null) {
			mNo = memberDto.getM_no();
		}
		
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		List<ClubDto> list = null;
		if(state == 0) {
			list = myPageDao.clubManageList(mNo);
		} else {
			list = myPageDao.lastClubManageList(mNo);
		}
		
		model.addAttribute("list", list);
		
	}

}
