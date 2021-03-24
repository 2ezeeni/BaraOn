package com.koreait.baraON.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MemberDao;
import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.SellerDto;

public class FindIdCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String name = request.getParameter("name");
		String email = request.getParameter("email") + "@" + request.getParameter("domains");
		String grade = request.getParameter("grade");
		
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		String id = null;
		if(grade.equals("member")) { // 멤버일때
			MemberDto memberDto = memberDao.findMemberId(name, email);
			if(memberDto != null) {
				id = memberDto.getM_id();
			}
		} else {  // 호스트일때
			SellerDto sellerDto = memberDao.findSellerId(name, email);
			if(sellerDto != null) {
				id = sellerDto.getS_id();
			}
		}
		
		model.addAttribute("id", id);
		model.addAttribute("findIdResult", true);
	
	}

}
