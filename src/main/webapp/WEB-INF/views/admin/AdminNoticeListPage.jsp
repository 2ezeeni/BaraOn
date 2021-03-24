<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">
	 a:link { color: black; text-decoration: none;}
 	 a:visited { color: black; text-decoration: none;}
	 a:hover { color: blue; text-decoration: underline;}
	 
	 #wrap{
	 	height: 700px;
	 }
	 
	 body{
	 	background: #F8EBEE;
	 }
	 .disable{
	 	margin:0 10px 0 10px;
	 }
	 #paging > a{
	 	margin: 0 10px 0 10px;
	 }
	
	
</style>
</head>
<body>
		<div id="wrap" style="width :1020px; margin: 0 auto;">
	<br/>
	<span style="font-size: 40px; font-weight: bold">공지사항(NOTICE)-관리자모드</span><br/><br/>
	<span style="font-size: 15px; color:blue; margin-left:10px;">전체 : ${totalRecord}개 공지사항이 있습니다.</span>
	<input type="button" value="공지사항추가" style="float: right" onclick="location.href='noticeInsertPage.admin'" /><br/><br/>
		<div>
				<div style="background:#3ED0C8">
					<div style="width:620px; margin: 0 auto; height:50px; line-height:50px;
						float: left; text-align: center; font-weight: 800px; 
						font-size: 24px; background:#3ED0C8; border-top: 1px solid black; border-bottom: 1px solid black">
						제목
					</div>
					<div style="width:400px; margin: 0 auto; height:50px; line-height:50px;
					float: left; text-align: center; font-weight: 800px; 
					font-size: 24px; background:#3ED0C8; border-top: 1px solid black; border-bottom: 1px solid black">
						작성일
					</div>
				</div>
						<form method="post">
			<c:if test="${empty list}"></c:if>
			<c:if test="${not empty list}">
				<c:forEach var="noticeDto" items="${list}" varStatus="k">
						<div style="width:620px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px">
							<a href="noticeViewPage.admin?no=${noticeDto.n_no}&page=${page}">${noticeDto.n_title}</a></div>
						<div>
						<div style="width:400px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px;">
							${noticeDto.n_postDate}
						</div>
					</div>
				</c:forEach>
			</c:if>
						</form>
			<input type="button" value="관리 목록페이지" style="float: left; margin:10px 0 10px 0" onclick="location.href='admin'" />
			<div id="paging"style="width:1020px; margin: 0 auto; float: left; text-align: center; font-weight: 800px; font-size: 20px">
				${paging}
				</div>
		</div>
	</div>
	<br/><br/>
<%@ include file="../template/footer.jsp" %>
			
				
					
	
				
	

</body>
</html>