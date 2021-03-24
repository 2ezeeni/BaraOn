package com.koreait.baraON.command.myPage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.member.Sha256;
import com.koreait.baraON.dto.MemberDto;
import com.koreait.baraON.dto.SellerDto;

public class DeletePwCheck {
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = (HttpSession)request.getSession();
		
		String loginPw = null;
		String pw = (String)map.get("pw");
		String grade = (String)session.getAttribute("grade");

		pw = Sha256.sha256(pw);
		
		boolean result = false;
		if(grade.equals("member")) { // 멤버일때
			MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
			if(memberDto != null) {
				loginPw = memberDto.getM_pw();
				result = pw.equals(loginPw);
			}
		} else { // 호스트일때
			SellerDto sellerDto = (SellerDto)session.getAttribute("loginDto");
			if(sellerDto != null) {
				loginPw = sellerDto.getS_pw();
				result = pw.equals(loginPw);
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		
		return resultMap;
	}

}
