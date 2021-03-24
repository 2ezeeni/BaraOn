package com.koreait.baraON.command.place;

import java.io.File;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.baraON.dao.PlaceDao;

public class PlaceDeleteCommand implements PlaceCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		HttpServletRequest request = (HttpServletRequest)model.asMap().get("request");
		RedirectAttributes rttr = (RedirectAttributes)model.asMap().get("rttr");
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		
		
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		String fileList = placeDao.getPlaceDto(p_no).getP_img(); 
		fileList.replace("[", "").replace("]", "");
		StringTokenizer st = new StringTokenizer(fileList, ", ");
		String realPath = request.getServletContext().getRealPath("resources/images/PlaceImages");
		
		while(st.hasMoreTokens()) {
			File img = new File(realPath, st.nextToken());
			if(img.exists()) {
				img.delete();
			}
		}
		
		rttr.addFlashAttribute("deleteResult", placeDao.placeDelete(p_no));
	}

}
