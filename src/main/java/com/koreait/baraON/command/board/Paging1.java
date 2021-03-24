package com.koreait.baraON.command.board;

public class Paging1 {

	public static String getPaging(String path, int totalRecord, int recordPerPage, int page , int f_category) {
		
		
		int totalPage;	
		totalPage = totalRecord / recordPerPage;
		if (totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		totalPage = (totalPage >= page) ? totalPage : page;
		
		
		
		int pagePerBlock = 4;	
		
		int beginPage = 0;		
		int endPage = 0;		
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock + f_category - 1;
		endPage = endPage < totalPage ? endPage : totalPage;
		
		
		// 페이징 (< 1 2 3 4 5 >) 만들기
		StringBuffer sb = new StringBuffer();
		
	
		
		// 1) 이전 버튼
		if (beginPage <= pagePerBlock) {
			sb.append("◀&nbsp;");
		} else {
			if (path.indexOf("?") > 0) {  
				sb.append("<a href=\"" + path + "&page=" + (beginPage - 1) + "\">◀&nbsp;</a>");
			} else {  // 경로에 ?가 없을 때(파라미터가 없을 때)
				sb.append("<a href=\"" + path + "?page=" + (beginPage - 1) + "\">◀&nbsp;</a>");				
			}
		}
		
		// 2) 페이지 번호
		for (int p = beginPage; p <= endPage; p++) {
			if (p == page) {
				sb.append("<span class=\"disable\">" + p + "&nbsp;</span>");
			} else {
				if (path.indexOf("?") > 0) {
					sb.append("<a href=\"" + path +"f_category="+ f_category + "&page=" + p +"\">" + p + "&nbsp;</a>");
				} else {
					sb.append("<a href=\"" + path + "f_category="+ f_category + "?page=" + p + "\">" + p + "&nbsp;</a>");
				}
			}
		}
		
	
		
	
		// 3) 다음 버튼
		if (endPage >= totalPage) { 
			sb.append("▶");
		} else {
			if (path.indexOf("?") > 0) {
				sb.append("<a href=\"" + path + "&page=" + (endPage + 1) + "\">▶</a>");
			} else {
				sb.append("<a href=\"" + path + "?page=" + (endPage + 1) + "\">▶</a>");
			}
		}
		
		return sb.toString();
		
	}

}
