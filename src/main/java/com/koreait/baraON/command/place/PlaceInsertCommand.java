package com.koreait.baraON.command.place;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dao.PlaceOptionDao;
import com.koreait.baraON.dto.PlaceDto;
import com.koreait.baraON.dto.PlaceOptionDto;
import com.koreait.baraON.dto.SellerDto;

public class PlaceInsertCommand implements PlaceCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)model.asMap().get("multipartRequest");
		RedirectAttributes rttr = (RedirectAttributes)model.asMap().get("rttr");
		
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		PlaceOptionDao placeOptionDao = sqlSession.getMapper(PlaceOptionDao.class);
		
		PlaceDto placeDto = new PlaceDto();
		// 먼저 place에 대한 데이터를 insert한다
		SellerDto sellerDto = (SellerDto)(multipartRequest.getSession().getAttribute("loginDto"));
		int s_no=sellerDto.getS_no();
		int pc_no=Integer.parseInt(multipartRequest.getParameter("pc_no"));
		String p_title=multipartRequest.getParameter("p_title");
		String p_name=multipartRequest.getParameter("p_name");
		String p_desc=multipartRequest.getParameter("p_desc");
		String p_content=multipartRequest.getParameter("p_content");
		String[] p_infoList=multipartRequest.getParameterValues("p_info");
		String[] p_remarkList=multipartRequest.getParameterValues("p_remark");
		String p_url=multipartRequest.getParameter("p_url");
		String p_addr=multipartRequest.getParameter("p_addr");
		String p_bname=multipartRequest.getParameter("p_bname");
		String p_addrdetail=multipartRequest.getParameter("p_addrdetail");
		
		List<String> list = new ArrayList<>();
		// info나 remark는 문자열에 ","가 포함되어있을 수 있으니 list로 넣은 후
		// js에서 split()으로 만드는것이 타당치 못하다
		StringBuffer sb = new StringBuffer();
		sb.trimToSize();
		sb.append("[");
		for(int i = 0; i<p_infoList.length;i++) {
			if(i!=(p_infoList.length-1)) {
				p_infoList[i]=p_infoList[i].replace("'", "&#39;").replace("\"", "&quot;");
				sb.append("\""+p_infoList[i]+"\",");
			} else {
				p_infoList[i]=p_infoList[i].replace("'", "&#39;").replace("\"", "&quot;");
				sb.append("\""+p_infoList[i]+"\"]");
			}
		}
		placeDto.setP_info(sb.toString());
		
		sb.setLength(0);
		sb.append("[");
		for(int i = 0; i<p_remarkList.length;i++) {
			if(i!=(p_remarkList.length-1)) {
				p_remarkList[i]=p_remarkList[i].replace("'", "&#39;").replace("\"", "&quot;");
				sb.append("\""+p_remarkList[i]+"\",");
			} else {
				p_remarkList[i]=p_remarkList[i].replace("'", "&#39;").replace("\"", "&quot;");
				sb.append("\""+p_remarkList[i]+"\"]");
			}
		}
		placeDto.setP_remark(sb.toString());
		
		sb.setLength(0);
		
		placeDto.setS_no(s_no);
		placeDto.setPc_no(pc_no);
		placeDto.setP_title(p_title);
		placeDto.setP_name(p_name);
		placeDto.setP_desc(p_desc);
		placeDto.setP_content(p_content);
		placeDto.setP_url(p_url);
		placeDto.setP_addr(p_addr);
		placeDto.setP_bname(p_bname);
		placeDto.setP_addrdetail(p_addrdetail);
		
		
		List<MultipartFile> files = multipartRequest.getFiles("p_img");
		int size = files.size();
		List<String> extensionList = new ArrayList<>();
		extensionList.add("jpg");
		extensionList.add("jpeg");
		extensionList.add("png");
		
		
		// 지원하는 이미지파일 확장자는 jpg, jpeg, png로 한다.
		for (MultipartFile file : files) {
			if (file != null && !file.isEmpty()) {
				String originalFilename = file.getOriginalFilename().replace("'", "");
				String extension = originalFilename.substring( originalFilename.lastIndexOf(".")+1);
				
				String realPath = multipartRequest.getServletContext().getRealPath("resources/images/PlaceImages");
				
				if(!extensionList.contains(extension)) {
					// 이 때 이 전에 업로드 받았던 파일들은 다 삭제해야한다.
					// 업로드 된 파일들은 sb에 목록으로 저장되어있다.
					String fileList = sb.toString().replace("\"", "").replace("[", "").replace("]", "");
					StringTokenizer st = new StringTokenizer(fileList, ",");
					
					while(st.hasMoreTokens()) {
						File uploadedImg = new File(realPath, st.nextToken());
						if (uploadedImg.exists()) {
							uploadedImg.delete();
						}
					}
					// 이미지파일이 아닐경우 -1
					// 파일형식이 맞지 않으므로 메소드를 끝낸다.
					rttr.addFlashAttribute("insertResult", -1);
					return;
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
					sb.append("\""+uploadFilename+"\",");
				} else {
					sb.append("\""+uploadFilename+"\"]");
					sb.insert(0,"[");
				}
			} else {
				// 이미지파일을 첨부하지 않은경우 -2
				// 최소 하나의 파일을 첨부해야하는 상황이므로 메소드를 끝낸다.
				rttr.addFlashAttribute("insertResult", -2);
				return;
			}
		}
		if ( !sb.toString().isEmpty() && sb.toString() != null) {
			placeDto.setP_img(sb.toString());
		}
		placeDao.placeInsert(placeDto);
		int currPNo = placeDto.getP_no();
		
		String[] poNameList= multipartRequest.getParameterValues("po_name");
		String[] poDayPriceList = multipartRequest.getParameterValues("po_dayPrice");
		String[] poHolidayList = multipartRequest.getParameterValues("po_holiday");
		String[] poMinList = multipartRequest.getParameterValues("po_min");
		String[] poMaxList = multipartRequest.getParameterValues("po_max");
		
		PlaceOptionDto placeOptionDto = new PlaceOptionDto();
		placeOptionDto.setP_no(currPNo);
		
		// facility의 경우 모두 다 같은 name을 가진 input에 들어 있다.
		// name=facility-count의 개수로 몇개의 옵션을 추가했는지 알 수 있고,
		// 그 input의 value로 각 option에 추가된 편의시설의 개수를 알 수 있다.
		// 이를 이용해 for문으로 List<PlaceOptionDto>를 만들고 mapper에서
		// <foreach> 태그로 insert한다.
		// 이 때, p_no는 PLACE_SEQ.CURRVAL로 한다. -> selectKey 사용
		String[] facilityCount= multipartRequest.getParameterValues("facility-count");
		
		int prevCount = 0;
		int insertCount = 0;
		List<MultipartFile> optionImgList = multipartRequest.getFiles("po_img");
		String[] po_facilityList = multipartRequest.getParameterValues("po_facility");
		for(int i = 0; i<facilityCount.length;i++) {
			sb.setLength(0);
			
			MultipartFile file = optionImgList.get(i);
			String originalFilename = file.getOriginalFilename().replace("'", "");
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			String realPath = multipartRequest.getServletContext().getRealPath("resources/images/PlaceOptionImages");
			
			if(!extensionList.contains(extension)) {
				List<PlaceOptionDto> insertedPlaceOptionList = placeDao.getPlaceOptionList(currPNo);
				String placeImgList = placeDto.getP_img().replace("\"", "").replace("[", "").replace("]", "");
				StringTokenizer st = new StringTokenizer(placeImgList, ",");
				while(st.hasMoreTokens()) {
					File uploadedImg = new File(multipartRequest.getServletContext().getRealPath("resources/images/PlaceImages"), st.nextToken());
					if(uploadedImg.exists()) {
						uploadedImg.delete();
					}
				}
				for(int j = 0, listSize = insertedPlaceOptionList.size();j<listSize;j++) {
					File uploadedImg = new File(realPath, insertedPlaceOptionList.get(j).getPo_img());
					if(uploadedImg.exists()) {
						uploadedImg.delete();
					}
				}
				// insert된 place데이터를 지운다.
				placeDao.placeFullDelete(currPNo);
				
				rttr.addFlashAttribute("insertResult", -1);
				return;
			}
			
			
			String filename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			String po_img = filename + "-" + System.currentTimeMillis() + "." + extension;
			
			File dir = new File(realPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			File uploadFile = new File(realPath, po_img);
			
			try {
				file.transferTo(uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			placeOptionDto.setPo_name(poNameList[i]);
			placeOptionDto.setPo_dayPrice(Integer.parseInt(poDayPriceList[i]));
			placeOptionDto.setPo_holiday(Integer.parseInt(poHolidayList[i]));
			placeOptionDto.setPo_min(Integer.parseInt(poMinList[i]));
			placeOptionDto.setPo_max(Integer.parseInt(poMaxList[i]));
			placeOptionDto.setPo_img(po_img);
			int count = Integer.parseInt(facilityCount[i]);
			for(int j =0;j<count;j++) {
				if(j!=(count-1)){
					po_facilityList[j+prevCount]=po_facilityList[j+prevCount].replace("'", "&#39;").replace("\"", "&quot;");
					sb.append("\""+po_facilityList[j+prevCount]+"\",");
				} else {
					po_facilityList[j+prevCount]=po_facilityList[j+prevCount].replace("'", "&#39;").replace("\"", "&quot;");
					sb.append("\""+po_facilityList[j+prevCount]+"\"]");
					sb.insert(0, "[");
				}
			}
			String po_facility = "";
			if (count!=0) {
				po_facility = sb.toString();
			}
			
			placeOptionDto.setPo_fxility(po_facility);
			
			insertCount += placeOptionDao.placeOptionInsert(placeOptionDto);
			
			prevCount = count;
		}
		if(insertCount == poNameList.length) {
			rttr.addFlashAttribute("insertResult", 1);
		} else {
			rttr.addFlashAttribute("insertResult", -3);
		}
		
		rttr.addFlashAttribute("currPNo", currPNo);
	}

}
