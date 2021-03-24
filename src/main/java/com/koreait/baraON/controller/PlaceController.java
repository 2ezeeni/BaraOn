package com.koreait.baraON.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.koreait.baraON.command.place.PlaceDeleteCommand;
import com.koreait.baraON.command.place.PlaceFullDeleteCommand;
import com.koreait.baraON.command.place.PlaceInsertCommand;
import com.koreait.baraON.command.place.PlaceListCommand;
import com.koreait.baraON.command.place.PlaceSearchCommand;
import com.koreait.baraON.command.place.PlaceUpdateCommand;
import com.koreait.baraON.command.place.PlaceUpdatePageCommand;
import com.koreait.baraON.command.place.PlaceViewCommand;
import com.koreait.baraON.dao.PlaceDao;

@Controller
public class PlaceController {

	@Autowired
	private SqlSession sqlSession;
	
	private PlaceListCommand placeListCommand;
	private PlaceViewCommand placeViewCommand;
	private PlaceInsertCommand placeInsertCommand;
	private PlaceUpdateCommand placeUpdateCommand;
	private PlaceDeleteCommand placeDeleteCommand;
	private PlaceUpdatePageCommand placeUpdatePageCommand;
	private PlaceFullDeleteCommand placeFullDeleteCommand;
	private PlaceSearchCommand placeSearchCommand;
	
	@Autowired
	public void setCommand(PlaceListCommand placeListCommand,
							PlaceViewCommand placeViewCommand,
							PlaceInsertCommand placeInsertCommand,
							PlaceUpdateCommand placeUpdateCommand,
							PlaceDeleteCommand placeDeleteCommand,
							PlaceUpdatePageCommand placeUpdatePageCommand,
							PlaceFullDeleteCommand placeFullDeleteCommand,
							PlaceSearchCommand placeSearchCommand) {
		this.placeListCommand = placeListCommand;
		this.placeViewCommand = placeViewCommand;
		this.placeInsertCommand = placeInsertCommand;
		this.placeUpdateCommand = placeUpdateCommand;
		this.placeDeleteCommand = placeDeleteCommand;
		this.placeUpdatePageCommand = placeUpdatePageCommand;
		this.placeFullDeleteCommand = placeFullDeleteCommand;
		this.placeSearchCommand = placeSearchCommand;
	}
	
	@RequestMapping(value="placeListPage.place", method=RequestMethod.GET)
	public String placeListPage(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		placeListCommand.execute(sqlSession, model);
		return "place/placeListPage";
	}
	
	@RequestMapping(value="placeViewPage.place", method=RequestMethod.GET)
	public String placeViewPage(HttpServletRequest request, RedirectAttributes rttr, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		placeViewCommand.execute(sqlSession, model);
		return "place/placeViewPage";
	}
	
	@RequestMapping(value="placeInsertPage.place", method=RequestMethod.GET)
	public String placeInsertPage(Model model) {
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		model.addAttribute("categoryList", placeDao.placeCategoryList());
		
		return "place/placeInsertPage";
	}
	
	@RequestMapping(value="placeInsertResult.place")
	public String placeInsertResultPage() {
		return "place/placeInsertResult";
	}
	@RequestMapping(value="placeInsert.place", method=RequestMethod.POST)
	public String placeInsert(MultipartHttpServletRequest multipartRequest, RedirectAttributes rttr, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("rttr", rttr);
		
		placeInsertCommand.execute(sqlSession, model);
		
		return "redirect:placeInsertResult.place";	}
	
	@RequestMapping(value="placeUpdatePage.place", method=RequestMethod.POST)
	public String placeUpdatePage(int p_no, Model model) {
		model.addAttribute("p_no", p_no);
		
		placeUpdatePageCommand.execute(sqlSession, model);
		return "place/placeUpdatePage";
	}

	
	@RequestMapping(value="placeUpdate.place", method=RequestMethod.POST)
	public String placeUpdate(MultipartHttpServletRequest multipartRequest, RedirectAttributes rttr, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("rttr", rttr);
		
		placeUpdateCommand.execute(sqlSession, model);
		return "redirect:placeUpdateResult.place";
	}
	@RequestMapping(value="placeUpdateResult.place", method=RequestMethod.GET)
	public String placeUpdateResult() {
		return "place/placeUpdateResult";
	}
	
	@RequestMapping(value="placeDelete.place", method=RequestMethod.POST)
	public String placeDelete(MultipartHttpServletRequest multipartRequest, RedirectAttributes rttr, Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("rttr", rttr);
		
		placeDeleteCommand.execute(sqlSession, model);
		return "redirect:placeListPage.place";
	}
	
	@RequestMapping(value="placeFullDelete.place/{p_no}", method=RequestMethod.DELETE, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> placeFullDelete(@PathVariable int p_no, Model model){
		model.addAttribute("p_no", p_no);
		
		return placeFullDeleteCommand.execute(sqlSession, model);
	}
	
	
	@RequestMapping(value="searchPlace.place", method=RequestMethod.GET)
	public String searchPlace(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		placeSearchCommand.execute(sqlSession, model);
		
		return "place/placeListPage";
	}
	
}
