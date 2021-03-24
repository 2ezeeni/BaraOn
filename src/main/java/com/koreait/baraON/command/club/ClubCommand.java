package com.koreait.baraON.command.club;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface ClubCommand {

	public void execute(SqlSession sqlSession, Model model);
	
}
