package com.koreait.baraON.command.myPage;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.MyPageDao;

public class ClubCountCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		int cNo = (int)map.get("cNo");
		
		MyPageDao myPageDao = sqlSession.getMapper(MyPageDao.class);
		int clubCount = myPageDao.clubCount(cNo);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("clubCount", clubCount);
		
		return resultMap; 
		
	}

}
