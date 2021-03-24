package com.koreait.baraON.common;

public class Paging {

	// 매개변수들은 ListCommand에게서 받아오는 데이터
	// 판단 -> 직접하는지 아닌지 보고, 받아오는 데이터인지 판단
	
	// 1.String path				// 어떤 게시판에서 사용하는 페이징인가?
	// 예) /MyHome/bbsListPage.bbs 또는 /MyHome/boardListPage.board 등
	
	// 2. int totalRecord;		 	// 전체 레코드(게시물)의 개수

	// 3. int recordPerPage 		// 페이지당 레코드(게시물) 개수
	// 모든 게시판이 한 페이지당 일정한 수의 게시물 List를 보여준다 -> 메소드 안에 둔다
	// 각 게시판마다 다르다 -> parameter로 받아온다.

	// 4. int page = 1;				// 현재 페이지 번호(기본 1페이지 번호)
	// beginRecord와 endRecord는 Paging에서는 쓸모 없는 정보이다.
	// 따라서 ListCommand에서 필요한 정보이며 ListCommand에서 만들어 DB로 준다.
	// int beginRecord = 0; 		// 한 페이지의 표시되는 시작 레코드(게시물)의 번호
	// int endRecord = 0; 			// 한 페이지의 표시되는 종료 레코드(게시물)의 번호

	// int pagePerBlock				// 한 블록당 페이지의 개수
	// 이 부분도 parameter로 받아 줄 수 있지만 굳이 그러지 않아도 된다.
	// 게시판 별로 바꿔줄 필요성이 그다지 없다.

	
	public static String getPaging(String path, int totalRecord, int recordPerPage, int page) {

		// 게시물 : record
		// 페이지 : page
		// 블록 : block

		// 게시물 3개를 한 페이지에 배치
		// 페이지 5개를 한 블록에 배치

		// 전체 페이지 개수는 받아오지 않고 스스로 계산한다.
		int totalPage; // 전체 페이지의 개수
		totalPage = totalRecord / recordPerPage;
		if (totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		totalPage = totalPage > page ? totalPage : page;

		int pagePerBlock = 5; // 한 블록당 페이지의 개수
		int beginPage = 0;
		int endPage = 0;
		
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1; // 한 블록에 표시되는 시작 페이지의 번호
		endPage = beginPage + pagePerBlock - 1;
		endPage = endPage < totalPage ? endPage : totalPage; // 한 블록에 표시되는 종료 페이지의 번호
		
		// 페이징 (◀ 1 2 3 4 5 ▶) 만들기
		StringBuffer sb = new StringBuffer();
		
		// 1) 이전 버튼
		if (beginPage <= pagePerBlock) {
			sb.append("<i class=\"far fa-arrow-alt-circle-left\"></i>&nbsp;");
		} else {
			if(path.indexOf("?") > 0) {
				sb.append("<a href=\""+path+"&page="+(beginPage-1)+"\"><i class=\"far fa-arrow-alt-circle-left\"></i>&nbsp;</a>");
			} else {
				sb.append("<a href=\""+path+"?page="+(beginPage-1)+"\"><i class=\"far fa-arrow-alt-circle-left\"></i>&nbsp;</a>");
			}
		}
		
		
		// 2) 페이지 번호
		for(int p = beginPage; p <= endPage; p++) {
			if (p == page) {
				sb.append("<span class=\"disable\">"+p+"&nbsp;</span>");
			} else {
				if(path.indexOf("?")>0) {
					sb.append("<a href=\""+path+"&page="+p+"\">"+p+"&nbsp;</a>");
				} else {
					sb.append("<a href=\""+path+"?page="+p+"\">"+p+"&nbsp;</a>");
				}
			}
		}
		
		// 3) 다음 버튼
		if (endPage >= totalPage) {	// endPage == totalPage
			sb.append("<i class=\"far fa-arrow-alt-circle-right\"></i>");
		} else {
			if(path.indexOf("?")>0) {
				sb.append("<a href=\""+path+"&page="+(endPage + 1)+"\"><i class=\"far fa-arrow-alt-circle-right\"></i></a>");
			} else {
				sb.append("<a href=\""+path+"?page="+(endPage + 1)+"\"><i class=\"far fa-arrow-alt-circle-right\"></i></a>");
			}
		}
		
		return sb.toString();
	}
}
