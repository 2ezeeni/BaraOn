package com.koreait.baraON.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BaraONCommand {

	public void execute(SqlSession sqlSession, Model model);
}