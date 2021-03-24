package com.koreait.baraON.command.myPage;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MyPageDao;

public class AddCardCommand{

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		int cl_no = (int)map.get("cl_no");
		
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		// 옐로카드 추가
		myPageDao.addCard(cl_no);
		// 옐로카드 개수
		int cardCount = myPageDao.cardCount(cl_no);
		// 옐로카드 3개 이상이면 강퇴
		if (cardCount >= 3) { 
			myPageDao.clubMemberDelete(cl_no);
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("cardCount", cardCount);
		
		return resultMap;
	}

}
