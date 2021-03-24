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
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.command.admin.AdminConfirmListCommand;
import com.koreait.baraON.command.admin.AdminConfirmUpdateCommand;
import com.koreait.baraON.command.admin.AdminConfirmViewCommand;
import com.koreait.baraON.command.admin.AdminEventListCommand;
import com.koreait.baraON.command.admin.AdminEventViewCommand;
import com.koreait.baraON.command.admin.AdminFAQListCommand;
import com.koreait.baraON.command.admin.AdminFAQViewCommand;
import com.koreait.baraON.command.admin.AdminMemberCommand;
import com.koreait.baraON.command.admin.AdminMemberDeleteCommand;
import com.koreait.baraON.command.admin.AdminMemberListCommand;
import com.koreait.baraON.command.admin.AdminMemberUpdateCommand;
import com.koreait.baraON.command.admin.AdminMemberViewCommand;
import com.koreait.baraON.command.admin.AdminNoticeListCommand;
import com.koreait.baraON.command.admin.AdminNoticeViewCommand;
import com.koreait.baraON.command.admin.AdminReplyDeleteCommand;
import com.koreait.baraON.command.admin.AdminReplyListCommand;
import com.koreait.baraON.command.admin.AdminReviewDeleteCommand;
import com.koreait.baraON.command.admin.AdminReviewListCommand;
import com.koreait.baraON.command.admin.AdminSellerDeleteCommand;
import com.koreait.baraON.command.admin.AdminSellerListCommand;
import com.koreait.baraON.command.admin.AdminSellerUpdateCommand;
import com.koreait.baraON.command.admin.AdminSellerViewCommand;
import com.koreait.baraON.command.board.EventDeleteCommand;
import com.koreait.baraON.command.board.EventInsertCommand;
import com.koreait.baraON.command.board.EventUpdateCommand;
import com.koreait.baraON.command.board.FAQDeleteCommand;
import com.koreait.baraON.command.board.FAQInsertCommand;
import com.koreait.baraON.command.board.FAQListCommand;
import com.koreait.baraON.command.board.FAQUpdateCommand;
import com.koreait.baraON.command.board.FAQViewCommand;
import com.koreait.baraON.command.board.NoticeCommand;
import com.koreait.baraON.command.board.NoticeDeleteCommand;
import com.koreait.baraON.command.board.NoticeInsertCommand;
import com.koreait.baraON.command.board.NoticeUpdateCommand;
import com.koreait.baraON.dto.AdminConfirmDto2;
import com.koreait.baraON.dto.AdminMemberDto2;
import com.koreait.baraON.dto.AdminSellerDto2;
import com.koreait.baraON.dto.EventDto;
import com.koreait.baraON.dto.FAQDto;
import com.koreait.baraON.dto.NoticeDto;
import com.koreait.baraON.dto.PageVo;

@Controller
public class AdminController {
	
	@Autowired
	private SqlSession sqlSession;
	private AdminNoticeListCommand adminNoticeListCommand;
	private AdminNoticeViewCommand adminNoticeViewCommand;
	private NoticeInsertCommand noticeInsertCommand;
	private NoticeDeleteCommand noticeDeleteCommand;
	private NoticeUpdateCommand noticeUpdateCommand;
	private AdminFAQListCommand adminFaqListCommand;
	private AdminFAQViewCommand adminFaqViewCommand;
	private FAQInsertCommand faqInsertCommand;
	private FAQDeleteCommand faqDeleteCommand;
	private FAQUpdateCommand faqUpdateCommand;
	private AdminEventListCommand adminEventListCommand;
	private AdminEventViewCommand adminEventViewCommand;
	private EventInsertCommand eventInsertCommand;
	private EventDeleteCommand eventDeleteCommand;
	private EventUpdateCommand eventUpdateCommand;
	private AdminMemberListCommand adminMemberListCommand;
	private AdminMemberViewCommand adminMemberViewCommand;
	private AdminMemberDeleteCommand adminMemberDeleteCommand;
	private AdminMemberUpdateCommand adminMemberUpdateCommand;
	private AdminSellerListCommand adminSellerListCommand;
	private AdminSellerViewCommand adminSellerViewCommand;
	private AdminSellerDeleteCommand adminSellerDeleteCommand;
	private AdminSellerUpdateCommand adminSellerUpdateCommand;
	private AdminReplyListCommand adminReplyListCommand;
	private AdminReplyDeleteCommand adminReplyDeleteCommand;
	private AdminReviewListCommand adminReviewListCommand;
	private AdminReviewDeleteCommand adminReviewDeleteCommand;
	private AdminConfirmListCommand adminConfirmListCommand;
	private AdminConfirmViewCommand adminConfirmViewCommand;
	private AdminConfirmUpdateCommand adminConfirmUpdateCommand;

	
	@Autowired
	public void setCommand(AdminNoticeListCommand adminNoticeListCommand,
							AdminNoticeViewCommand adminNoticeViewCommand,
							NoticeInsertCommand noticeInsertCommand,
							NoticeDeleteCommand noticeDeleteCommand,
							NoticeUpdateCommand noticeUpdateCommand,
							AdminFAQListCommand adminFaqListCommand,
							AdminFAQViewCommand adminFaqViewCommand,
							FAQInsertCommand faqInsertCommand,
							FAQDeleteCommand faqDeleteCommand,
							FAQUpdateCommand faqUpdateCommand,
							AdminEventListCommand adminEventListCommand,
							AdminEventViewCommand adminEventViewCommand,
							EventInsertCommand eventInsertCommand,
							EventDeleteCommand eventDeleteCommand,
							EventUpdateCommand eventUpdateCommand,
							AdminMemberListCommand adminMemberListCommand,
							AdminMemberViewCommand adminMemberViewCommand,
							AdminMemberUpdateCommand adminMemberUpdateCommand,
							AdminMemberDeleteCommand adminMemberDeleteCommand,
							AdminSellerListCommand adminSellerListCommand,
							AdminSellerViewCommand adminSellerViewCommand,
							AdminSellerDeleteCommand adminSellerDeleteCommand,
							AdminSellerUpdateCommand adminSellerUpdateCommand,
							AdminReplyListCommand adminReplyListCommand,
							AdminReplyDeleteCommand adminReplyDeleteCommand,
							AdminReviewListCommand adminReviewListCommand,
							AdminReviewDeleteCommand adminReviewDeleteCommand,
							AdminConfirmListCommand adminConfirmListCommand,
							AdminConfirmUpdateCommand adminConfirmUpdateCommand,
							AdminConfirmViewCommand adminConfirmViewCommand) {
		this.adminNoticeListCommand = adminNoticeListCommand;
		this.adminNoticeViewCommand = adminNoticeViewCommand;
		this.noticeInsertCommand = noticeInsertCommand;
		this.noticeDeleteCommand = noticeDeleteCommand;
		this.noticeUpdateCommand = noticeUpdateCommand;
		this.adminFaqListCommand = adminFaqListCommand;
		this.adminFaqViewCommand = adminFaqViewCommand;
		this.faqInsertCommand = faqInsertCommand;
		this.faqDeleteCommand = faqDeleteCommand;
		this.faqUpdateCommand = faqUpdateCommand;
		this.adminEventListCommand = adminEventListCommand;
		this.adminEventViewCommand = adminEventViewCommand;
		this.eventInsertCommand = eventInsertCommand;
		this.eventDeleteCommand = eventDeleteCommand;
		this.eventUpdateCommand = eventUpdateCommand;
		this.adminMemberListCommand = adminMemberListCommand;
		this.adminMemberViewCommand = adminMemberViewCommand;
		this.adminMemberDeleteCommand = adminMemberDeleteCommand;
		this.adminMemberUpdateCommand = adminMemberUpdateCommand;
		this.adminSellerListCommand = adminSellerListCommand;
		this.adminSellerViewCommand = adminSellerViewCommand;
		this.adminSellerDeleteCommand = adminSellerDeleteCommand;
		this.adminSellerUpdateCommand = adminSellerUpdateCommand;
		this.adminReplyListCommand = adminReplyListCommand;
		this.adminReplyDeleteCommand = adminReplyDeleteCommand;
		this.adminReviewListCommand = adminReviewListCommand;
		this.adminReviewDeleteCommand  = adminReviewDeleteCommand;
		this.adminConfirmListCommand =  adminConfirmListCommand;
		this.adminConfirmUpdateCommand = adminConfirmUpdateCommand;
		this.adminConfirmViewCommand = adminConfirmViewCommand;
	}
			
	
	// 단순이동
	@RequestMapping(value="admin", method=RequestMethod.GET)
		public String adminPage() {
			return "admin/adminPage";
	}
	@RequestMapping(value="noticeInsertPage.admin", method=RequestMethod.GET)
		public String noticeInsertPage() {
			return "admin/AdminNoticeInsertPage";
	}
	@RequestMapping(value="faqInsertPage.admin", method=RequestMethod.GET)
		public String faqInsertPage() {
		return "admin/AdminFaqInsertPage";
	}
	@RequestMapping(value="noticeUpdatePage.admin", method=RequestMethod.GET)
		public String noticeUpdatePage(NoticeDto noticeDto,
										Model model) {
		model.addAttribute("noticeDto", noticeDto);
		return "admin/AdminNoticeUpdatePage";
	}
	@RequestMapping(value="faqUpdatePage.admin", method=RequestMethod.GET)
		public String faqUpdatePage(FAQDto faqDto,
									Model model) {
		model.addAttribute("faqDto" , faqDto);
		return "admin/AdminFaqUpdatePage";
	}
	@RequestMapping(value="adminMemberPage.admin" , method=RequestMethod.GET)
		public String adminMemberListPage() {
		return "admin/AdminMemberListPage";
	}
	@RequestMapping(value="adminSellerPage.admin" , method=RequestMethod.GET)
	public String adminSellerList() {
	return "admin/AdminSellerPage";
	}
	@RequestMapping(value="adminReplyPage.admin" , method=RequestMethod.GET)
		public String adminReplyListPagege() {
		return "admin/AdminReplyPage";
	}
	@RequestMapping(value="adminReviewPage.admin" , method=RequestMethod.GET)
		public String adminReviewListPagege() {
		return "admin/AdminReviewPage";
	}
	@RequestMapping(value="eventInsertPage.admin", method=RequestMethod.GET)
	public String eventInsertPage() {
		return "admin/AdminEventInsertPage";
	}
	@RequestMapping(value="eventUpdatePage.admin", method=RequestMethod.GET)
	public String eventUpdatePage(EventDto eventDto,
									Model model) {
	model.addAttribute("eventDto", eventDto);
	return "admin/AdminEventUpdatePage";
	}
	@RequestMapping(value="adminConfirmPage.admin", method=RequestMethod.GET)
	public String confirmListPage() {
		return "admin/AdminConfirmPage";
}
	//BOARD
	@RequestMapping(value="adminNoticeListPage.admin",method=RequestMethod.GET)
		public String noticeListPage(HttpServletRequest request, Model model){
			model.addAttribute("request" , request);
			adminNoticeListCommand.execute(sqlSession, model);
			return "admin/AdminNoticeListPage";
	}
	@RequestMapping(value="noticeViewPage.admin",method=RequestMethod.GET)
		public String noticeViewPage(HttpServletRequest request, Model model) {
			model.addAttribute("request" , request);
			adminNoticeViewCommand.execute(sqlSession, model);
			return "admin/AdminNoticeViewPage";
	}
	@RequestMapping(value="noticeInsert.admin" , method=RequestMethod.GET)
		public String noticeInsert(HttpServletRequest request,
										RedirectAttributes rttr, 
										Model model) {
		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		noticeInsertCommand.execute(sqlSession, model);
		return "redirect:adminNoticeListPage.admin";
	}
	@RequestMapping(value="noticeDelete.admin" , method=RequestMethod.GET)
		public String noticeDelete(HttpServletRequest request,
                					RedirectAttributes rttr,
                					Model model) {
		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		
		noticeDeleteCommand.execute(sqlSession, model);
		
		return "redirect:adminNoticeListPage.admin";
	}
	@RequestMapping(value="noticeUpdate.admin", method=RequestMethod.GET)
		public String noticeUpdate(HttpServletRequest request,
			                   RedirectAttributes rttr,
			                   Model model) {

		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		noticeUpdateCommand.execute(sqlSession, model);
		
		return "redirect:noticeViewPage.admin?no=" + request.getParameter("no");
	}
	@RequestMapping(value="adminFaqListPage.admin" , method=RequestMethod.GET)
		public String FAQListPage(HttpServletRequest request, Model model) {
			model.addAttribute("request" , request);
			adminFaqListCommand.execute(sqlSession, model);
			return "admin/AdminFaqListPage";
	}
	@RequestMapping(value="faqViewPage.admin", method=RequestMethod.GET)
		public String faqViewPage(HttpServletRequest request, Model model) {
			model.addAttribute("request" , request);
			adminFaqViewCommand.execute(sqlSession, model);
			return "admin/AdminFaqViewPage";
	}
	@RequestMapping(value="faqInsert.admin" , method=RequestMethod.GET)
		public String faqInsert(HttpServletRequest request,
									RedirectAttributes rttr, 
									Model model) {
			
			model.addAttribute("request", request);
			model.addAttribute("rttr", rttr);
			faqInsertCommand.execute(sqlSession, model);
			return "redirect:adminFaqListPage.admin";
	}
	@RequestMapping(value="faqDelete.admin" , method=RequestMethod.GET)
		public String faqDelete(HttpServletRequest request,
            					RedirectAttributes rttr,
            					Model model) {
			
			model.addAttribute("request", request);
			model.addAttribute("rttr", rttr);
			faqDeleteCommand.execute(sqlSession, model);
			return "redirect:adminFaqListPage.admin";
	}
	@RequestMapping(value="faqUpdate.admin", method=RequestMethod.GET)
		public String faqUpdate(HttpServletRequest request,
		                   		RedirectAttributes rttr,
		                   		Model model) {

			model.addAttribute("request", request);
			model.addAttribute("rttr", rttr);
			faqUpdateCommand.execute(sqlSession, model);
			return "redirect:faqViewPage.admin?no=" + request.getParameter("no");
	}
	@RequestMapping(value="adminEventListPage.admin",method=RequestMethod.GET)
	public String eventListPage(HttpServletRequest request, Model model){
		model.addAttribute("request" , request);
		adminEventListCommand.execute(sqlSession, model);
		return "admin/AdminEventListPage";
}
	@RequestMapping(value="eventViewPage.admin",method=RequestMethod.GET)
		public String eventViewPage(HttpServletRequest request, Model model) {
			model.addAttribute("request" , request);
			adminEventViewCommand.execute(sqlSession, model);
			return "admin/AdminEventViewPage";
	}
	@RequestMapping(value="eventInsert.admin" , method=RequestMethod.POST)
		public String eventInsert(MultipartRequest multipartRequest,
									RedirectAttributes rttr,
									Model model) {
		model.addAttribute("multipartRequest", multipartRequest);
		model.addAttribute("rttr", rttr);
		eventInsertCommand.execute(sqlSession, model);
		return "redirect:adminEventListPage.admin";
	}
	@RequestMapping(value="eventDelete.admin" , method=RequestMethod.GET)
		public String eventDelete(HttpServletRequest request,
	            					RedirectAttributes rttr,
	            					Model model) {
		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		
		eventDeleteCommand.execute(sqlSession, model);
		
		return "redirect:adminEventListPage.admin";
	}
	@RequestMapping(value="eventUpdate.admin", method=RequestMethod.GET)
		public String eventUpdate(HttpServletRequest request,
			                   RedirectAttributes rttr,
			                   Model model) {
	
		model.addAttribute("request", request);
		model.addAttribute("rttr", rttr);
		eventUpdateCommand.execute(sqlSession, model);
		
		return "redirect:eventViewPage.admin?no=" + request.getParameter("no");
	}
	
	
	//MEMBER
	@RequestMapping(value="member", method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> memberList(@RequestBody PageVo pageVo, Model model) {
		model.addAttribute("page" , pageVo.getPage());
		return adminMemberListCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="member/{no}", method=RequestMethod.GET,
	        		produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> memberView(@PathVariable("no") int no,
		                              			Model model) {
		model.addAttribute("no", no);
		return adminMemberViewCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="member", method=RequestMethod.PUT,
	            	produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> memberUpdate(@RequestBody AdminMemberDto2 adminMemberDto2,
	                                     	Model model) {
	if (adminMemberDto2 != null) {
		model.addAttribute("adminMemberDto2", adminMemberDto2);
	}
		return adminMemberUpdateCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="member/{no}",method=RequestMethod.DELETE,
            		produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> memberDelete(@PathVariable("no") int no,
												Model model) {
		model.addAttribute("no", no);
		return adminMemberDeleteCommand.execute(sqlSession, model);
	}
	// SELLER
	@RequestMapping(value="seller", method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> sellerList(@RequestBody PageVo pageVo, Model model) {
		model.addAttribute("page" , pageVo.getPage());
		return adminSellerListCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="seller/{no}", method=RequestMethod.GET,
	    			produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> sellerView(@PathVariable("no") int no,
	                              			Model model) {
		model.addAttribute("no", no);
		return adminSellerViewCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="seller", method=RequestMethod.PUT,
	        		produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> sellerUpdate(@RequestBody AdminSellerDto2 adminSellerDto2,
	                                 	Model model) {
	if (adminSellerDto2 != null) {
	model.addAttribute("adminSellerDto2", adminSellerDto2);
	}
	return adminSellerUpdateCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="seller/{no}",method=RequestMethod.DELETE,
	    			produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> sellerDelete(@PathVariable("no") int no,
											Model model) {
		model.addAttribute("no", no);
		return adminSellerDeleteCommand.execute(sqlSession, model);
	}
	
	
	//REPLY
	@RequestMapping(value="reply", method=RequestMethod.POST,
			produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> replyList(@RequestBody PageVo pageVo, Model model) {
		model.addAttribute("page" , pageVo.getPage());
		return adminReplyListCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="reply/{no}",method=RequestMethod.DELETE,
    				produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> replyDelete(@PathVariable("no") int no,
												Model model) {
		model.addAttribute("no", no);
		return adminReplyDeleteCommand.execute(sqlSession, model);
	}
	
	
	//REVIEW
	@RequestMapping(value="review", method=RequestMethod.POST,
					produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> reviewList(@RequestBody PageVo pageVo, Model model) {
		model.addAttribute("page" , pageVo.getPage());
		return adminReviewListCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="review/{no}",method=RequestMethod.DELETE,
    				produces="application/json; charset=utf-8")
	@ResponseBody
		public Map<String, Object> reviewDelete(@PathVariable("no") int no,
												Model model) {
		model.addAttribute("no", no);
		return adminReviewDeleteCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="confirm", method=RequestMethod.POST,
				produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> confirmList(@RequestBody PageVo pageVo, Model model) {
	model.addAttribute("page" , pageVo.getPage());
	return adminConfirmListCommand.execute(sqlSession, model);
	}
	@RequestMapping(value="confirm/{no}", method=RequestMethod.GET,
					produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> confirmView(@PathVariable("no") int no,
	                          			Model model) {
	model.addAttribute("no", no);
	return adminConfirmViewCommand.execute(sqlSession, model);
	}
	
	@RequestMapping(value="confirm", method=RequestMethod.PUT,
    		produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> confirmUpdate(@RequestBody AdminConfirmDto2 adminConfirmDto2,
	                             			Model model) {
	if (adminConfirmDto2 != null) {
	model.addAttribute("adminConfirmDto2", adminConfirmDto2);
	}
	return adminConfirmUpdateCommand.execute(sqlSession, model);
	}
}
