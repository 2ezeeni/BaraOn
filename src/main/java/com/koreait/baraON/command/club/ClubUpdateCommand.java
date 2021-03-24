package com.koreait.baraON.command.club;

import java.io.File;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ClubDao;

public class ClubUpdateCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		RedirectAttributes rttr = (RedirectAttributes)map.get("rttr");
		
		String realPath = multipartRequest.getServletContext().getRealPath("resources/images/club");
		
		int c_no = Integer.parseInt(multipartRequest.getParameter("c_no"));
		int c_min = Integer.parseInt(multipartRequest.getParameter("c_min"));
		int c_max = Integer.parseInt(multipartRequest.getParameter("c_max"));
		String c_content = multipartRequest.getParameter("c_content");
		String c_desc = multipartRequest.getParameter("c_desc");
		
		MultipartFile newFile = multipartRequest.getFile("c_mainImg");
		
		String oldFile = multipartRequest.getParameter("c_mainImg2");
		
		if ( newFile != null && oldFile != null ) {
	
			File file = new File(realPath, oldFile);
			if(file.exists()) {
				file.delete();
			}
			
			String originalFilename = newFile.getOriginalFilename();
			
			String extension = originalFilename.substring( originalFilename.lastIndexOf(".")+1);
			
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
					
			String uploadFilename = filename + "-" + System.currentTimeMillis() + "." + extension;
			
			File uploadFile = new File(realPath, uploadFilename);
			
			try {
				newFile.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
		int updateResult = clubDao.clubUpdate(uploadFilename, c_desc, c_min, c_max, c_content, c_no);
		
		boolean afterUpdate = false;
		if(updateResult > 0) { 
			afterUpdate = true;
		}
		
		rttr.addFlashAttribute("updateResult", updateResult);
		rttr.addFlashAttribute("afterUpdate", afterUpdate);

		}
	}

}
