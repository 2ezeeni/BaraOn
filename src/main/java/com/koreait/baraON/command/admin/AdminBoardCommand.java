package com.koreait.baraON.command.admin;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface AdminBoardCommand {
	
	public void execute(SqlSession sqlSession, Model model);
}
