package com.koreait.baraON.command.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.PlaceDao;
import com.koreait.baraON.dto.PlaceDto;
import com.koreait.baraON.dto.PlaceListDto;

public class PlaceCaSearch2Command implements SellerCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		
		PlaceDto placeDto = (PlaceDto) map.get("placeDto");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
		
		List<PlaceListDto> list = placeDao.allPlaceSearch(placeDto);

		StringBuffer sb = new StringBuffer();
		sb.trimToSize();
		sb.append("[");
		for(int i = 0, size=list.size();i<size;i++) {
			if(i!=(size-1)) {
				sb.append(list.get(i).getP_img()+", ");
			} else {
				sb.append(list.get(i).getP_img()+"]");
				
			}
		}
		
		resultMap.put("list", list);
		resultMap.put("imgList", sb.toString());
		
		if(list.size() > 0) {	
			resultMap.put("result", true);
		}else {
			resultMap.put("result", false);
		}
		
		return resultMap;
		
	}

}	