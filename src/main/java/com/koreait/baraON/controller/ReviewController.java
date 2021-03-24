package com.koreait.baraON.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.command.review.ReviewDeleteCommand;
import com.koreait.baraON.command.review.ReviewInsertCommand;
import com.koreait.baraON.command.review.ReviewListAppendCommand;
import com.koreait.baraON.command.review.ReviewUpdateCommand;
import com.koreait.baraON.dto.ReviewDto;

@Controller
public class ReviewController {

	private SqlSession sqlSession;
	private ReviewUpdateCommand reviewUpdateCommand;
	private ReviewDeleteCommand reviewDeleteCommand;
	private ReviewInsertCommand reviewInsertCommand;
	private ReviewListAppendCommand reviewListAppendCommand;
	
	@Autowired
	public void setSessionAndCommands(SqlSession sqlSession,
										  ReviewUpdateCommand reviewUpdateCommand,
										  ReviewDeleteCommand reviewDeleteCommand,
										  ReviewInsertCommand reviewInsertCommand,
										  ReviewListAppendCommand reviewListAppendCommand) {
		this.sqlSession = sqlSession;
		this.reviewUpdateCommand = reviewUpdateCommand;
		this.reviewDeleteCommand = reviewDeleteCommand;
		this.reviewInsertCommand = reviewInsertCommand;
		this.reviewListAppendCommand = reviewListAppendCommand;
	}
	
	
	
	@RequestMapping(value="reviewUpdate.review", method=RequestMethod.PUT, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> reviewUpdate(@RequestBody ReviewDto reviewDto , Model model) {
		model.addAttribute("reviewDto",reviewDto);
		
		return reviewUpdateCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="reviewDelete.review/{rv_no}", method=RequestMethod.PUT, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> reviewDelete(@PathVariable int rv_no , HttpServletRequest request, Model model) {
		model.addAttribute("rv_no", rv_no);
		model.addAttribute("request",request);
		return reviewDeleteCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="reviewInsert.review", method=RequestMethod.POST)
	public String reviewInsert(MultipartHttpServletRequest multipartRequest, RedirectAttributes rttr, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("rttr", rttr);
		
		reviewInsertCommand.execute(sqlSession, model);
		return "redirect:placeViewPage.place?no="+multipartRequest.getParameter("p_no");
	}
	
	@RequestMapping(value="reviewListAppend.review/{rn}/{p_no}", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> reviewListAppend(@PathVariable int rn, @PathVariable int p_no, Model model){
		model.addAttribute("rn",rn);
		model.addAttribute("p_no",p_no);
		
		return reviewListAppendCommand.execute(sqlSession, model);
	}
}
