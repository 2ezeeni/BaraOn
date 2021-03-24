package com.koreait.baraON.command.myPage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.command.BaraONCommand;
import com.koreait.baraON.dao.MyPageDao;

public class WishDeleteCommand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
		
		int wNo = 0;
		if(request.getParameter("wNo") != null) {
			wNo = Integer.parseInt(request.getParameter("wNo"));
		}
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		// 삭제
		int result = myPageDao.wishDelete(wNo);
		
		boolean isDelete = false;
		if(result > 0) { 
			isDelete = true;
		}
		
		// 삭제 후 리스트 다시 가져오기
		rttr.addFlashAttribute("isDelete", isDelete);
		rttr.addFlashAttribute("deleteResult", result);
	}

}
