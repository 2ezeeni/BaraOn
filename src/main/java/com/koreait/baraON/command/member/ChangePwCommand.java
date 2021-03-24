package com.koreait.baraON.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MemberDao;

public class ChangePwCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpSession session = request.getSession();
		String newPassword = Sha256.sha256(request.getParameter("newPassword"));
		String id = (String)session.getAttribute("id");
		String grade = (String)session.getAttribute("grade");
		
		MemberDao memberdao = sqlSession.getMapper(MemberDao.class);
		int changePwResult = 0;
		if(grade.equals("member")) {  // 멤버일때
			changePwResult = memberdao.changeMemberPw(newPassword, id);
		} else {  // 호스트일때
			changePwResult = memberdao.changeSellerPw(newPassword, id);
		}
		model.addAttribute("changePwResult", changePwResult);
	}

}
