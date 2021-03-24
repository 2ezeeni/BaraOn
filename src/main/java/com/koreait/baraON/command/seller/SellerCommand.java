package com.koreait.baraON.command.seller;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface SellerCommand {
	
	public Map<String, Object> execute(SqlSession sqlSession, Model model);

}
