package com.koreait.baraON.command.member;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MemberDao;
import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.SellerDto;

public class LoginCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String id = request.getParameter("id");
		String pw = Sha256.sha256(request.getParameter("pw"));
		//String pw = request.getParameter("pw");
		String grade = request.getParameter("grade");
		
		if(grade.equals("member")) { // 등급이 member일때
			MemberDto loginMemberDto = memberDao.memberLogin(id, pw);
			
			if(loginMemberDto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginDto", loginMemberDto); //  로그인 정보 session에 저장
				session.setAttribute("grade", grade); 
				if(loginMemberDto.getM_id().equals("admin")) {
					session.setAttribute("admin", true);
				} else {
					session.setAttribute("admin", false);
				}
			}
		} else {  // 등급이 seller일때 
			SellerDto loginSellerDto = memberDao.sellerLogin(id, pw);
			
			if(loginSellerDto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginDto", loginSellerDto);
				session.setAttribute("grade", grade); 
			}
		}
		
		
	}
}
