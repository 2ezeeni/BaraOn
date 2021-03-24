package com.koreait.baraON.command.admin;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface AdminMemberCommand {
	public Map<String, Object> execute(SqlSession sqlSession, Model model);
}
