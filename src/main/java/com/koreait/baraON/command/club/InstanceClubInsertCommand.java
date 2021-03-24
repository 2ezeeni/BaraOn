package com.koreait.baraON.command.club;

import java.io.File;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ClubDao;

public class InstanceClubInsertCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) map.get("multipartRequest");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
		
		int m_no = Integer.parseInt(multipartRequest.getParameter("m_no"));
		
		String c_title = multipartRequest.getParameter("c_title");
		String c_desc = multipartRequest.getParameter("c_desc");
		String c_content = multipartRequest.getParameter("c_content");
		int c_min = Integer.parseInt(multipartRequest.getParameter("c_min"));
		int c_max = Integer.parseInt(multipartRequest.getParameter("c_max"));
		
		
		String c_startDate = multipartRequest.getParameter("c_startDate");
		String c_endDate = c_startDate;
	
		MultipartFile file = multipartRequest.getFile("c_mainImg");
	
		if (file != null && !file.isEmpty()) {
	
			String originalFilename = file.getOriginalFilename();
			String extension = originalFilename.substring( originalFilename.lastIndexOf(".")+1);
			
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
					
			String uploadFilename = filename + "-" + System.currentTimeMillis() + "." + extension;
			
			String realPath = multipartRequest.getServletContext().getRealPath("resources/images/club");
			
			File dir = new File(realPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			File uploadFile = new File(realPath, uploadFilename);

			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int insertResult2 = clubDao.instanceclubInsert(m_no, c_title, c_desc, c_min, c_max, c_startDate, c_endDate, c_content, uploadFilename);
			
			boolean afterInsert2 = false;
			if(insertResult2 > 0) { 
				afterInsert2 = true;
			}
			
			rttr.addFlashAttribute("insertResult2", insertResult2);
			rttr.addFlashAttribute("afterInsert2", afterInsert2);
		} 
		
	}

}