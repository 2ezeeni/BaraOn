package com.koreait.baraON.command.club;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.baraON.dao.ClubDao;
import com.koreait.baraON.dto.ClubDto;

public class InstanceClubListCommand implements ClubCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		ClubDao clubDao = sqlSession.getMapper(ClubDao.class);
		
		List<ClubDto> list2 = clubDao.instanceClubList();
		
		model.addAttribute("list2", list2);
	}

}