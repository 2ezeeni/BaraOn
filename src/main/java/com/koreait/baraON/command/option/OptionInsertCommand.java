package com.koreait.baraON.command.option;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.baraON.dao.PlaceOptionDao;
import com.koreait.baraON.dto.PlaceOptionDto;

public class OptionInsertCommand {
	public Map<String, Object> execute(SqlSession sqlSession, Model model){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)model.asMap().get("multipartRequest");
		PlaceOptionDao placeOptionDao = sqlSession.getMapper(PlaceOptionDao.class);
		
		PlaceOptionDto placeOptionDto = new PlaceOptionDto();
		int p_no = Integer.parseInt(multipartRequest.getParameter("p_no"));
		String po_name = multipartRequest.getParameter("po_name");
		int po_min = Integer.parseInt(multipartRequest.getParameter("po_min"));
		int po_max = Integer.parseInt(multipartRequest.getParameter("po_max"));
		int po_dayPrice = Integer.parseInt(multipartRequest.getParameter("po_dayPrice"));
		int po_holiday = Integer.parseInt(multipartRequest.getParameter("po_holiday"));
		String[] facilityList = multipartRequest.getParameterValues("po_facility");
		
		StringBuffer sb = new StringBuffer();
		sb.trimToSize();
		
		sb.append("[");
		for(int i = 0;i<facilityList.length;i++) {
			if(i<facilityList.length-1) {
				facilityList[i]=facilityList[i].replace("'", "&#39;").replace("\"", "&quot;");
				sb.append("\""+facilityList[i]+"\",");
			} else {
				facilityList[i]=facilityList[i].replace("'", "&#39;").replace("\"", "&quot;");
				sb.append("\""+facilityList[i]+"\"]");
			}
		}
		String po_fxility = sb.toString();
		
		placeOptionDto.setP_no(p_no);
		placeOptionDto.setPo_name(po_name);
		placeOptionDto.setPo_min(po_min);
		placeOptionDto.setPo_max(po_max);
		placeOptionDto.setPo_dayPrice(po_dayPrice);
		placeOptionDto.setPo_holiday(po_holiday);
		placeOptionDto.setPo_fxility(po_fxility);
		
		List<String> extensionList = new ArrayList<>();
		extensionList.add("jpg");
		extensionList.add("jpeg");
		extensionList.add("png");
		
		Map<String, Object> resultMap = new HashMap<>();
		
		MultipartFile file = multipartRequest.getFile("po_img");
		String originalFilename = file.getOriginalFilename().replace("'","");
		String extension = originalFilename.substring( originalFilename.lastIndexOf(".")+1);
		if(!extensionList.contains(extension)) {
			resultMap.put("result", -1);
			return resultMap;
		}
		String realPath = multipartRequest.getServletContext().getRealPath("resources/images/PlaceOptionImages");
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
		placeOptionDto.setPo_img(uploadFilename);
		
		resultMap.put("result", placeOptionDao.placeOptionInsert(placeOptionDto));
		
		return resultMap;
	}
}
