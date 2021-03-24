package com.koreait.baraON.command.review;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.ReviewDao;

public class ReviewInsertCommand implements ReviewCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)model.asMap().get("multipartRequest");
		RedirectAttributes rttr = (RedirectAttributes)model.asMap().get("rttr");
		
		int m_no = Integer.parseInt(multipartRequest.getParameter("m_no"));
		int p_no = Integer.parseInt(multipartRequest.getParameter("p_no"));
		String rv_content = multipartRequest.getParameter("rv_content");
		String rv_img = null;
		int rv_star = Integer.parseInt(multipartRequest.getParameter("rv_star"));
		ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
		
		List<MultipartFile> files = multipartRequest.getFiles("rv_img");
		int size = files.size();
		List<String> extensionList = new ArrayList<>();
		extensionList.add("jpg");
		extensionList.add("jpeg");
		extensionList.add("png");
		
		StringBuffer sb = new StringBuffer();
		sb.trimToSize();
		// System.out.println(sb.length());
		// 지원하는 이미지파일 확장자는 jpg, jpeg, png로 한다.
		for (MultipartFile file : files) {
			if (file != null && !file.isEmpty()) {
				String originalFilename = file.getOriginalFilename().replace("'", "");
				String extension = originalFilename.substring( originalFilename.lastIndexOf(".")+1);
				
				String realPath = multipartRequest.getServletContext().getRealPath("resources/images/ReviewImages");
				
				if(!extensionList.contains(extension)) {
					// 이 때 이 전에 업로드 받았던 파일들은 다 삭제해야한다.
					// 업로드 된 파일들은 sb에 목록으로 저장되어있다.
					String fileList = sb.toString().replace("\"", "").replace("[", "").replace("]", "");
					StringTokenizer st = new StringTokenizer(fileList, ", ");
					
					while(st.hasMoreTokens()) {
						File uploadedFile = new File(realPath, st.nextToken());
						if (uploadedFile.exists()) {
							uploadedFile.delete();
						}
					}
					
					rttr.addFlashAttribute("insertResult", -1);
				}
				
				String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
				String uploadFilename = filename + "-" + System.currentTimeMillis() + "." + extension;
				
				
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
				
				if(files.get(size-1) != file) {
					sb.append("\""+uploadFilename+"\", ");
				} else {
					sb.append("\""+uploadFilename+"\"]");
					sb.insert(0,"[");
				}
			}
		}
		if ( !sb.toString().isEmpty() && sb.toString() != null) {
			rv_img = sb.toString();
			rttr.addFlashAttribute("insertResult", reviewDao.reviewInsert(m_no, p_no, rv_star, rv_img, rv_content));
		} else {
			rttr.addFlashAttribute("insertResult", reviewDao.reviewInsert(m_no, p_no, rv_star, sb.toString(), rv_content));
		}
	}

}
