package com.koreait.baraON.command.place;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dao.ReviewDao;
import com.koreait.baraON.dto.PlaceCategoryDto;
import com.koreait.baraON.dto.PlaceDto;
import com.koreait.baraON.dto.PlaceOptionDto;
import com.koreait.baraON.dto.ReviewListDto;
import com.koreait.baraON.dto.SellerDto;

public class PlaceViewCommand implements PlaceCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		HttpServletRequest request = (HttpServletRequest)model.asMap().get("request");
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		PlaceDto placeDto = placeDao.getPlaceDto(no);
		int p_no = placeDto.getP_no();
		int s_no = placeDto.getS_no();
		int pc_no = placeDto.getPc_no();
		
		SellerDto sellerDto = placeDao.getSellerDto(s_no);
		
		PlaceCategoryDto placeCategoryDto = placeDao.getPlaceCategoryByPCNo(pc_no);
		
		// OptionList의 facility 데이터를 받아와서 배열로 만들기
		List<PlaceOptionDto> placeOptionList = placeDao.getPlaceOptionList(p_no);
		
		StringBuilder sb = new StringBuilder();
		sb.trimToSize();
		sb.append("[");
		
		for(int i = 0, size=placeOptionList.size();i<size;i++) {
			if (i==size-1) {
				sb.append(placeOptionList.get(i).getPo_fxility());
			} else {
				sb.append(placeOptionList.get(i).getPo_fxility()+",");
			}
		}
		sb.append("]");
		
		// 후에 sb가 초기화 되므로 만든 후 바로 model에 저장한다.
		model.addAttribute("facilityList",sb.toString());
		
		// Review
		// 가장 최초에는 삭제되지 않은 리뷰중에 가장 최근의 리뷰부터 3개를 가져옴
		List<ReviewListDto> reviewList = placeDao.getReviewListOfViewPage(p_no, 1, 3);
		// rv_img칼럼에는 데이터가 JSON 객체로 들어있다.
		// 그 객체에는 review-image라는 프로퍼티에 배열로 첨부한 이미지들이 저장되어있다.
		/*
			[
				"a.jpg",
				"b.jpg"
				"c.gif"
				"d.jpeg"
				"e.png"
				"f.png"
			]
		*/
		// 이 때 리뷰가 3개보다 적을 수 있으니 reviewList의 size를 구하고 그 차이만큼만 가져온다.
		// rv_img들을 JSON 형식으로 저장하기 위해 위에서 사용했던 StringBuffer를 비운다.
		// 찾아보니 초기화할 때는 StringBuffer보다 StringBuilder가 훨씬 빠르다고한다.
		// 따라서 기존에 선언했던 StringBuffer 대신 StringBuilder로 교체한 후 진행한다.
		// 초기화에는 아래 세 가지 방법이 있다.
		
		// 1. 길이 초기화
		sb.setLength(0);
		sb.trimToSize();
		
		// 2. 0부터 마지막까지 삭제
		// sb.delete(0, sb.length());
		
		// 3. 객체 재생성
		// sb = null;
		// sb = new StringBuilder();
		
		if(reviewList != null && !reviewList.isEmpty()) {
			sb.append("[");
			for(int i=0, size=reviewList.size();i<size;i++) {
				String img = reviewList.get(i).getRv_img();
				if(i != size-1) {
					sb.append(img+",");
				} else if (img == null) {
					if(i!=size-1) {	
						sb.append("[],");
					} else {
						sb.append("[]");
					}
				} else {
					sb.append(img);
				}
			}
			sb.append("]");
		}
		boolean isSeller = false;
		try {
			SellerDto loginDto = (SellerDto)request.getSession().getAttribute("loginDto");
			if(loginDto != null) {
				isSeller=true;
			}
		} catch (ClassCastException e) {
			isSeller=false;
			
		}
		
		// 로그인된 멤버가 해당 공간을 예약해서 사용한적 있는지 판단
		
		if (!isSeller) {
			
		}
		model.addAttribute("isSeller", isSeller);
		model.addAttribute("reviewImage", sb.toString());
		
		model.addAttribute("placeDto", placeDto);
		model.addAttribute("sellerDto", sellerDto);
		model.addAttribute("categoryName", placeCategoryDto.getPc_name());
		model.addAttribute("optionList", placeOptionList);
		model.addAttribute("reviewList", reviewList);
		// page에서 받은 리뷰의 번호가 마지막 리뷰인지를 판단하기 위한 attribute
		model.addAttribute("lastReviewRN", reviewDao.lastReviewRN(p_no));
	}
}