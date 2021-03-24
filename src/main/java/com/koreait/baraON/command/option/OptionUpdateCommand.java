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

public class OptionUpdateCommand {
public Map<String, Object> execute(SqlSession sqlSession, Model model){
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		Map<String, Object> resultMap = new HashMap<>();
		PlaceOptionDao placeOptionDao = sqlSession.getMapper(PlaceOptionDao.class);
		
		int po_no = Integer.parseInt(multipartRequest.getParameter("po_no"));
		String po_name = multipartRequest.getParameter("po_name");
		int po_min = Integer.parseInt(multipartRequest.getParameter("po_min"));
		int po_max = Integer.parseInt(multipartRequest.getParameter("po_max"));
		int po_dayPrice = Integer.parseInt(multipartRequest.getParameter("po_dayPrice"));
		int po_holiday = Integer.parseInt(multipartRequest.getParameter("po_holiday"));
		String[] facilityList = multipartRequest.getParameterValues("po_facility");
		int change = Integer.parseInt(multipartRequest.getParameter("change"));
		
		PlaceOptionDto placeOptionDto = new PlaceOptionDto();
		

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
		
		placeOptionDto.setPo_no(po_no);
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
		if(change==1) {
			MultipartFile file = multipartRequest.getFile("po_img");
			String originalFilename = file.getOriginalFilename().replace("'","");
			String extension = originalFilename.substring( originalFilename.lastIndexOf(".")+1);
			if(!extensionList.contains(extension)) {
				resultMap.put("result", -1);
				return resultMap;
			}
			// 썸네일을 변경했으면 기존 파일을 삭제
			PlaceOptionDto insertedDto = placeOptionDao.getPlaceOptionDto(po_no);
			
			String realPath = multipartRequest.getServletContext().getRealPath("resources/images/PlaceOptionImages");
			
			File uploadedImg = new File(realPath, insertedDto.getPo_img());
			
			if(uploadedImg.exists()) {
				uploadedImg.delete();
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
			placeOptionDto.setPo_img(uploadFilename);
		} else {
			placeOptionDto.setPo_img(null);
		}
		
		resultMap.put("result", placeOptionDao.placeOptionUpdate(placeOptionDto));
		
		return resultMap;
	}
}
