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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.command.myPage.AddCardCommand;
import com.koreait.baraON.command.myPage.ClubCountCommand;
import com.koreait.baraON.command.myPage.ClubListDeleteCommand;
import com.koreait.baraON.command.myPage.ClubManageDetailCommand;
import com.koreait.baraON.command.myPage.ClubManageListCommand;
import com.koreait.baraON.command.myPage.ClubReservationListCommand;
import com.koreait.baraON.command.myPage.DeletePwCheck;
import com.koreait.baraON.command.myPage.InfoDeleteCommand;
import com.koreait.baraON.command.myPage.InstantClubCommand;
import com.koreait.baraON.command.myPage.RegularClubCommand;
import com.koreait.baraON.command.myPage.WishDeleteCommand;
import com.koreait.baraON.command.myPage.WishListCommand;

@Controller
public class MyPageController {
	@Autowired
	private SqlSession sqlSession;
	
	private WishListCommand wishListCommand;
	private WishDeleteCommand wishDeleteCommand;
	private RegularClubCommand regularClubCommand;
	private InstantClubCommand instantClubCommand;
	private ClubListDeleteCommand clubListDeleteCommand;
	private DeletePwCheck deletePwCheck;
	private InfoDeleteCommand infoDeleteCommand;
	private ClubManageListCommand clubManageListCommand;
	private ClubCountCommand clubCountCommand;
	private ClubManageDetailCommand clubManageDetailCommand;
	private AddCardCommand addCardCommand;
	private ClubReservationListCommand clubReservationListCommand;
	
	@Autowired
	public void setCommand(WishListCommand wishListCommand,
							 WishDeleteCommand wishDeleteCommand,
							 RegularClubCommand regularClubCommand,
							 InstantClubCommand instantClubCommand,
							 ClubListDeleteCommand clubListDeleteCommand,
							 DeletePwCheck deletePwCheck,
							 InfoDeleteCommand InfoDeleteCommand,
							 ClubManageListCommand clubManageListCommand,
							 ClubCountCommand clubCountCommand,
							 ClubManageDetailCommand clubManageDetailCommand,
							 AddCardCommand addCardCommand,
							 ClubReservationListCommand clubReservationListCommand) {
		this.wishListCommand = wishListCommand;
		this.wishDeleteCommand = wishDeleteCommand;
		this.regularClubCommand = regularClubCommand;
		this.instantClubCommand = instantClubCommand;
		this.clubListDeleteCommand = clubListDeleteCommand;
		this.deletePwCheck = deletePwCheck;
		this.infoDeleteCommand = InfoDeleteCommand;
		this.clubManageListCommand = clubManageListCommand;
		this.clubManageListCommand = clubManageListCommand;
		this.clubCountCommand = clubCountCommand;
		this.clubManageDetailCommand = clubManageDetailCommand;
		this.addCardCommand = addCardCommand;
		this.clubReservationListCommand = clubReservationListCommand;
	}
	
	@RequestMapping(value="profile.myPage", method=RequestMethod.GET)
	public String myPage(HttpServletRequest request) {
		return "myPage/profile";
	}
	
	@RequestMapping(value="pwCheck.myPage" ,method=RequestMethod.GET)
	public String memberJoin2(HttpServletRequest request, Model model) {
		model.addAttribute("m_phone", request.getParameter("m_phone"));
		model.addAttribute("m_email", request.getParameter("m_email"));
		return "myPage/pwCheck";
	}
	
	@RequestMapping(value="wishList.myPage", method=RequestMethod.GET)
	public String wishList(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		wishListCommand.execute(sqlSession, model);
		return "myPage/wishList";
	}
	
	@RequestMapping(value="wishDelete.myPage", method=RequestMethod.POST)
	public String wishDelete(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		wishDeleteCommand.execute(sqlSession, model);
		return "redirect:wishList.myPage";
	}
	
	@RequestMapping(value="regularClub.myPage", method=RequestMethod.GET)
	public String regularClub(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		regularClubCommand.execute(sqlSession, model);
		return "myPage/regularClub";
	}
	@RequestMapping(value="instantClub.myPage", method=RequestMethod.GET)
	public String instantClub(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		instantClubCommand.execute(sqlSession, model);
		return "myPage/instantClub";
	}
	
	@RequestMapping(value="clubListDelete.myPage", method=RequestMethod.POST)
	public String clubListDelete(HttpServletRequest request, Model model, RedirectAttributes rttr) {
		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		clubListDeleteCommand.execute(sqlSession, model);
		if(Integer.parseInt(request.getParameter("clubNo")) == 0) {  //정기 모임
			return "redirect:regularClub.myPage";
		} else { //번개 모임
			return "redirect:instantClub.myPage";
		}
	}
	
	@RequestMapping(value="infoDeletePage.myPage", method=RequestMethod.GET)
	public String infoDeletePage(@RequestParam(value="grade", required=false) String grade) {
		if(grade.equals("member")) {
			return "myPage/memberDeletePage";
		} else {
			return "myPage/sellerDeletePage";
		}
	}
	
	@RequestMapping(value="deletePwCheck.myPage/{pw}", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deletePwCheck(@PathVariable("pw") String pw, HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("pw", pw);
		return deletePwCheck.execute(sqlSession, model);
	}
	
	@RequestMapping(value="infoDelete.myPage", method=RequestMethod.GET)
	public String infoDelete(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		infoDeleteCommand.execute(sqlSession, model);
		return "myPage/infoDeleteResult";
	}
	
	@RequestMapping(value="clubManagePage.myPage", method=RequestMethod.GET)
	public String clubManagePage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		clubManageListCommand.execute(sqlSession, model);
		return "myPage/clubManagePage";
	}
	
	@RequestMapping(value="clubCount/{cNo}", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> clubCount(@PathVariable("cNo") int cNo, Model model) {
		model.addAttribute("cNo", cNo);
		return clubCountCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="clubManageDetailPage.myPage", method=RequestMethod.POST)
	public String clubManageDetailPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		clubManageDetailCommand.execute(sqlSession, model);
		return "myPage/clubManageDetailPage";
	}
	
	@RequestMapping(value="infoPopUp.myPage", method=RequestMethod.GET)
	public String infoPopUp(@RequestParam("nick") String nick, Model model) {
		model.addAttribute("nick", nick);
		return "myPage/infoPopUp";
	}
	
	@RequestMapping(value="cardPlus/{cl_no}", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> cardPlus(@PathVariable("cl_no") int cl_no, Model model) {
		model.addAttribute("cl_no", cl_no);
		return addCardCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="clubReservationList.myPage", method=RequestMethod.GET)
	public String clubReservationList(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		clubReservationListCommand.execute(sqlSession, model);
		return "myPage/clubReservationPage";
	}
	
}
