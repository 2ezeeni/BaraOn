package com.koreait.baraON.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.baraON.command.IndexCommand;

@Controller
public class controller {

	private SqlSession sqlSession;
	private IndexCommand indexCommand;
	
	@Autowired
	public void setCommands(SqlSession sqlSession,
							IndexCommand indexCommand) {
		this.sqlSession = sqlSession;
		this.indexCommand = indexCommand;
	}
	
	@RequestMapping(value={"/","index"}, method=RequestMethod.GET)
	public String index(Model model) {
		indexCommand.execute(sqlSession, model);
		return "index";
	}

	
}