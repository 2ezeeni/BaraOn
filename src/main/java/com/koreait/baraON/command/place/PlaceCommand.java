package com.koreait.baraON.command.place;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface PlaceCommand {
	public void execute(SqlSession sqlSession, Model model);
}
