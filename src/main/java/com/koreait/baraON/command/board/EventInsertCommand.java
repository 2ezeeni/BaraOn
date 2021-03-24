package com.koreait.baraON.command.board;

import java.io.File;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.EventDao;
import com.koreait.baraON.dto.EventDto;

public class EventInsertCommand implements NoticeCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
			
			Map<String, Object> map = model.asMap();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
			RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			EventDto eventDto = new EventDto();
			eventDto.setE_title(title);
			eventDto.setE_content(content);
			EventDao eventDao = sqlSession.getMapper(EventDao.class);
			
			MultipartFile thumbnail = multipartRequest.getFile("files");
			MultipartFile banner = multipartRequest.getFile("banner");
			
			if (thumbnail != null && !thumbnail.isEmpty()) {
				
				String originalFilename = thumbnail.getOriginalFilename();
				
				String extension = originalFilename.substring( originalFilename.lastIndexOf(".") + 1 );

				String filename = originalFilename.substring( 0, originalFilename.lastIndexOf(".") );
				
				String uploadFilename = filename +
						                "_" +
						                System.currentTimeMillis() +
						                "." +
						                extension;
				
				String realPath = multipartRequest.getServletContext().getRealPath("resources/images/event");
				
				File dir = new File(realPath);
				if ( !dir.exists() ) {
					dir.mkdirs();
				}
				File uploadFile = new File(realPath, uploadFilename);
				
				try {
					thumbnail.transferTo(uploadFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				eventDto.setE_filename(uploadFilename);
			} else {
				eventDto.setE_filename("첨부없음");  
				
			}
			if (banner != null && !banner.isEmpty()) {
				
				String originalFilename = banner.getOriginalFilename();
				
				String extension = originalFilename.substring( originalFilename.lastIndexOf(".") + 1 );
				
				String filename = originalFilename.substring( 0, originalFilename.lastIndexOf(".") );
				
				String uploadFilename = filename +
						"_" +
						System.currentTimeMillis() +
						"." +
						extension;
				
				String realPath = multipartRequest.getServletContext().getRealPath("resources/images/banner");
				
				File dir = new File(realPath);
				if ( !dir.exists() ) {
					dir.mkdirs();
				}
				File uploadFile = new File(realPath, uploadFilename);
				
				try {
					banner.transferTo(uploadFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				eventDto.setE_banner(uploadFilename);  
			} else {
				eventDto.setE_banner("첨부없음");  
				
			}
			rttr.addAttribute("insertResult", eventDao.eventInsert(eventDto));
			
		}

	}