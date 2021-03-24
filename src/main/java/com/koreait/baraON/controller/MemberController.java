package com.koreait.baraON.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.baraON.command.member.ChangePwCommand;
import com.koreait.baraON.command.member.ChkIdCommand;
import com.koreait.baraON.command.member.EmailAuthCommand2;
import com.koreait.baraON.command.member.EmailAuthCommmand;
import com.koreait.baraON.command.member.FindIdCommand;
import com.koreait.baraON.command.member.FindPwCommand;
import com.koreait.baraON.command.member.KakaoAPI;
import com.koreait.baraON.command.member.KakaoLoginCommand;
import com.koreait.baraON.command.member.LoginCommand;
import com.koreait.baraON.command.member.LogoutCommand;
import com.koreait.baraON.command.member.MemberEmailSearchCommand;
import com.koreait.baraON.command.member.MemberInsertCommand;
import com.koreait.baraON.command.member.MemberNickSearchCommand;
import com.koreait.baraON.command.member.MemberNickUpdateCommand;
import com.koreait.baraON.command.member.MemberPhoneSearchCommand;
import com.koreait.baraON.command.member.MemberPwSearchCommand;
import com.koreait.baraON.command.member.MemberPwUpdateCommand;
import com.koreait.baraON.command.member.MemberSearchCommand;
import com.koreait.baraON.command.member.MemberUpdateCommand;
import com.koreait.baraON.dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired
	private SqlSession sqlSession;
	
	//private BaraONCommand baraONCommand;
	private LoginCommand loginCommand;
	private KakaoLoginCommand kakaoLoginCommand;
	private LogoutCommand logoutCommand;
	private ChkIdCommand chkIdCommand;
	private FindIdCommand findIdCommand;
	private FindPwCommand findPwCommand;
	private EmailAuthCommmand emailAuthCommmand;
	private ChangePwCommand changePwCommand;
	private KakaoAPI kakaoAPI;
	
	private JavaMailSender javaMailSender;
	private MemberSearchCommand memberSearchCommand;
	private MemberNickSearchCommand memberNickSearchCommand;
	private EmailAuthCommand2 emailAuthCommand2;
	private MemberPwSearchCommand memberPwSearchCommand;
	private MemberInsertCommand memberInsertCommand;
	private MemberPwUpdateCommand memberPwUpdateCommand;
	private MemberNickUpdateCommand memberNickUpdateCommand;
	private MemberUpdateCommand memberUpdateCommand;
	private MemberPhoneSearchCommand memberPhoneSearchCommand;
	private MemberEmailSearchCommand memberEmailSearchCommand;
	
	@Autowired
	public void setCommand(LoginCommand loginCommand,
							 KakaoLoginCommand kakaoLoginCommand,
							 LogoutCommand logoutCommand,
							 ChkIdCommand chkIdCommand,
							 FindIdCommand findIdCommand,
							 FindPwCommand findPwCommand,
							 EmailAuthCommmand emailAuthCommmand,
							 ChangePwCommand changePwCommand,
							 KakaoAPI kakaoAPI,
							 
							MemberSearchCommand memberSearchCommand,
							MemberNickSearchCommand memberNickSearchCommand,
							EmailAuthCommand2 emailAuthCommand2,
							JavaMailSender javaMailSender,
							MemberPwSearchCommand memberPwSearchCommand,
							MemberInsertCommand memberInsertCommand,
							MemberPwUpdateCommand memberPwUpdateCommand,
							MemberNickUpdateCommand memberNickUpdateCommand,
							MemberUpdateCommand memberUpdateCommand,
							MemberPhoneSearchCommand memberPhoneSearchCommand,
							MemberEmailSearchCommand memberEmailSearchCommand) {
		
		this.loginCommand = loginCommand;
		this.kakaoLoginCommand = kakaoLoginCommand;
		this.logoutCommand = logoutCommand;
		this.chkIdCommand = chkIdCommand;
		this.findIdCommand = findIdCommand;
		this.findPwCommand = findPwCommand;
		this.emailAuthCommmand = emailAuthCommmand;
		this.changePwCommand = changePwCommand;
		this.kakaoAPI = kakaoAPI;
		
		
		this.memberSearchCommand=memberSearchCommand;
		this.memberNickSearchCommand=memberNickSearchCommand;
		this.emailAuthCommand2=emailAuthCommand2;
		this.javaMailSender = javaMailSender;
		this.memberPwSearchCommand =memberPwSearchCommand;
		this.memberInsertCommand=memberInsertCommand;
		this.memberPwUpdateCommand=memberPwUpdateCommand;
		this.memberNickUpdateCommand=memberNickUpdateCommand;
		this.memberUpdateCommand=memberUpdateCommand;
		this.memberPhoneSearchCommand=memberPhoneSearchCommand;
		this.memberEmailSearchCommand=memberEmailSearchCommand;
	}
	
	@RequestMapping(value="loginPage.member", method=RequestMethod.GET)
	public String loginPage() {
		return "member/loginPage";
	}
	
	@RequestMapping(value="login.member", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, HttpServletResponse response) {
		model.addAttribute("request", request);

		// 아이디 기억하기
		chkIdCommand.execute(response, model);
		
		// 로그인
		loginCommand.execute(sqlSession, model);
		HttpSession session = request.getSession();
		if(session.getAttribute("grade") != null) {
			if(session.getAttribute("grade").equals("member")) { // member일때
				
				return "member/loginResult";
			}
		} 
		return "member/loginResult2";  // seller일때
	}
	
	@RequestMapping(value="loginKakao.member")
	public String kakaoLogin(@RequestParam(value="code") String code, HttpSession session, HttpServletRequest request, Model model) {
		String access_Token = kakaoAPI.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);
       
		System.out.println("controller access_token : " + access_Token);
		System.out.println("login Controller: " + userInfo);
		System.out.println("access_Token : " + access_Token);
		System.out.println("userInfo : " + userInfo.get("email"));
		
		if(userInfo.get("email") != null && userInfo.get("id") != null ) {
			model.addAttribute("request", request);
			model.addAttribute("id", userInfo.get("id"));
			model.addAttribute("email", userInfo.get("email"));
			
			if(kakaoLoginCommand.execute(sqlSession, model)) { // 로그인
				session.setAttribute("userId", userInfo.get("id"));
				session.setAttribute("access_Token", access_Token);
				return "member/loginResult";
			} else { // 데이터 없음. 회원가입으로 이동
				return "member/memberJoin";
			}
			
		}
		// 토큰 받아오는데 실패
		return "member/loginPage";
		
	}
	
//	@RequestMapping(value="loginKakao.member")
//	public String kakaoLogin(@RequestParam(value="code") String code, HttpSession session) {
//		String access_Token = kakaoAPI.getAccessToken(code);
//		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);
//		
//		System.out.println("controller access_token : " + access_Token);
//		System.out.println("login Controller: " + userInfo);
//		System.out.println("access_Token : " + access_Token);
//		System.out.println("userInfo : " + userInfo.get("email"));
//		
//		if(userInfo.get("email") != null && userInfo.get("id") != null ) {
//			
//			
//			session.setAttribute("userId", userInfo.get("email"));
//			session.setAttribute("access_Token", access_Token);
//		}
//		
//		return "member/loginPage";
//	}
	
	@RequestMapping(value="logout.member", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model, HttpSession session) {
		model.addAttribute("request", request);
		kakaoAPI.kakaoLogout((String)session.getAttribute("access_Token"));
		logoutCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@RequestMapping(value="findPage.member", method=RequestMethod.GET)
	public String findPage(HttpServletRequest request) {
		return "member/findPage";
	}
	
	@RequestMapping(value="findId.member", method=RequestMethod.POST)
	public String findId(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		findIdCommand.execute(sqlSession, model);
		return "member/findPage";
	}
	
	@RequestMapping(value="findIdPage.member", method=RequestMethod.GET)
	public String findIdPage(HttpServletRequest request) {
		return "member/findIdPage";
	}
	
	@RequestMapping(value="findPw.member", method=RequestMethod.POST)
	public String findPw(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		findPwCommand.execute(sqlSession, model);
		return "member/findPage";
	}

	//@Autowired
	//private JavaMailSender mailSender;
	//post?
	@RequestMapping(value="findPwPage.member", method=RequestMethod.GET)
	public String findPwPage(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("javaMailSender", javaMailSender);
		emailAuthCommmand.execute(sqlSession, model);
		return "member/findPwPage";
	}
	
	@RequestMapping(value="findPwPage2.member", method=RequestMethod.GET)
	public String findPwPage2(HttpServletRequest request) {
		return "member/findPwPage2";
	}
	
	@RequestMapping(value="changePw.member", method=RequestMethod.POST)
	public String changePw(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		changePwCommand.execute(sqlSession, model);
		return "member/findPwPage3";
	}
	
	
	
	
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}
	@RequestMapping(value="memberJoin3.member" ,method=RequestMethod.GET)
	public String memberJoin3() {
		return "member/memberJoin3";
	}
	
	@RequestMapping(value="memberJoin.member" ,method=RequestMethod.GET)
	public String memberJoin() {
		return "member/memberJoin";
	}
	
	@RequestMapping(value="memberJoin2.member" ,method=RequestMethod.POST)
	public String memberJoin2(HttpServletRequest request, Model model) {
		model.addAttribute("kakaoId", request.getParameter("kakaoId"));
		model.addAttribute("kakaoEmail", request.getParameter("kakaoEmail"));
		model.addAttribute("chk_email", request.getParameter("chk_email"));
		return "member/memberJoin2";
	}
	
	@RequestMapping(value="memberSearch.member", 
					 method=RequestMethod.POST,
					 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberSearch(@RequestParam("m_id") String m_id,
											  Model model) {
		model.addAttribute("m_id",m_id);
		return memberSearchCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="memberNickSearch.member", 
					method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberNickSearch(@RequestParam("m_nick") String m_nick,
												Model model) {
		model.addAttribute("m_nick",m_nick);
		return memberNickSearchCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="memberPhoneSearch.member", 
			method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberPhoneSearch(@RequestParam("m_phone") String m_phone,
			Model model) {
		model.addAttribute("m_phone",m_phone);
		return memberPhoneSearchCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="memberEmailSearch.member", 
			method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberEmailSearch(@RequestParam("m_email") String m_email,
			Model model) {
		model.addAttribute("m_email",m_email);
		return memberEmailSearchCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="memberPwSearch.member", 
			method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberPwSearch(@RequestBody MemberDto memberDto,
												Model model) {
		model.addAttribute("m_id",memberDto.getM_id());
		model.addAttribute("m_pw",memberDto.getM_pw());
		return memberPwSearchCommand.execute(sqlSession, model);
	}
	
	
	@RequestMapping(value="emailAuth.member", 
					method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> emailAuth(@RequestBody MemberDto memberDto,
											Model model) {
		model.addAttribute("m_email", memberDto.getM_email());
		model.addAttribute("javaMailSender", javaMailSender);
		return emailAuthCommand2.execute(sqlSession, model);
	}
	
	@RequestMapping(value="memberInsert.member",
					method=RequestMethod.POST)
	public String memberInsert(MemberDto memberDto,
											Model model){
		model.addAttribute("memberDto", memberDto);
		memberInsertCommand.execute(sqlSession, model);
		return "member/loginPage";
	}
	
	@RequestMapping(value="memberView.member",
					method=RequestMethod.GET)
	public String memberView(){
		return "myPage/memberView";
	}
	
	@RequestMapping(value="memberPwUpdate.member",
					method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberPwUpdate(@RequestBody MemberDto memberDto,
											Model model){
		if (memberDto != null) {
			model.addAttribute("memberDto", memberDto);
		}
		return memberPwUpdateCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="memberNickUpdate.member",
			method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberNickUpdate(@RequestBody MemberDto memberDto,
													HttpServletRequest request,
													Model model){
		if (memberDto != null) {
			model.addAttribute("memberDto", memberDto);
		}
		model.addAttribute("request",request);
		return memberNickUpdateCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="memberUpdate.member",
					method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberUpdate(@RequestBody MemberDto memberDto,
												HttpServletRequest request,
												Model model){
		if (memberDto != null) {
			model.addAttribute("memberDto", memberDto);
		}
		model.addAttribute("request",request);
		return memberUpdateCommand.execute(sqlSession, model);
	}
	
	
	
	
}