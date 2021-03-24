package com.koreait.baraON.command.club;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface myClubListCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model);
	
}
