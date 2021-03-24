package com.koreait.baraON.command.board;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.FAQDao;

public class FAQUpdateCommand implements NoticeCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		FAQDao faqDao = sqlSession.getMapper(FAQDao.class);
		
		int updateResult = faqDao.faqUpdate(title, content, no);
		
		rttr.addFlashAttribute("updateResult", updateResult);
		rttr.addFlashAttribute("afterUpdate", true);
	}

}
