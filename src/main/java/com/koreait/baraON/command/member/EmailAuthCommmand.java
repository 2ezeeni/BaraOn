package com.koreait.baraON.command.member;

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

import com.koreait.baraON.command.BaraONCommand;

public class EmailAuthCommmand implements BaraONCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		try {
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest)map.get("request");
			JavaMailSender javaMailSender = (JavaMailSender)map.get("javaMailSender");
			
			MimeMessage message = javaMailSender.createMimeMessage();
			message.setHeader("Content-Type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("baraontest@gmail.com", "바라온"));
			
			HttpSession session = request.getSession();
			InternetAddress to = new InternetAddress((String)session.getAttribute("email"));
			
			message.addRecipient(RecipientType.TO, to);
			message.setSubject("인증 요청 메일입니다.", "utf-8");
			
			long authKey = (long)(Math.random() * 100000000L) + 1234567890;
			
			message.setText("인증코드: " + authKey, "utf-8");
			javaMailSender.send(message);
			
			System.out.println(authKey);
			model.addAttribute("authKey", authKey);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
