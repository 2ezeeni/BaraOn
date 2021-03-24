package com.koreait.baraON.controller;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.command.option.OptionDeleteCommand;
import com.koreait.baraON.command.option.OptionInsertCommand;
import com.koreait.baraON.command.option.OptionUpdateCommand;

@Controller
public class PlaceOptionController {
	
	private SqlSession sqlSession;
	private OptionDeleteCommand optionDeleteCommand;
	private OptionUpdateCommand optionUpdateCommand;
	private OptionInsertCommand optionInsertCommand;
	
	@Autowired
	public void setCommands(SqlSession sqlSession,
							OptionDeleteCommand optionDeleteCommand,
							OptionUpdateCommand optionUpdateCommand,
							OptionInsertCommand optionInsertCommand) {
		this.sqlSession = sqlSession;
		this.optionDeleteCommand = optionDeleteCommand;
		this.optionUpdateCommand = optionUpdateCommand;
		this.optionInsertCommand = optionInsertCommand;
	}
	
	@RequestMapping(value="placeOptionDelete.place/{po_no}", method=RequestMethod.DELETE, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> optionDelete(@PathVariable int po_no, Model model){
		model.addAttribute("po_no", po_no);
		
		return optionDeleteCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="placeOptionUpdate.place", method=RequestMethod.POST, produces="application/json; charset=utf-8", headers=("content-type=multipart/*"))
	@ResponseBody
	public Map<String, Object> optionUpdate(MultipartHttpServletRequest multipartRequest, Model model){
		model.addAttribute("multipartRequest", multipartRequest);
		
		return optionUpdateCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="placeOptionInsert.place", method=RequestMethod.POST, produces="application/json; charset=utf-8", headers=("content-type=multipart/*"))
	@ResponseBody
	public Map<String, Object> optionInsert(MultipartHttpServletRequest multipartRequest, Model model){
		model.addAttribute("multipartRequest", multipartRequest);
		
		return optionInsertCommand.execute(sqlSession, model);
	}
}
