<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<style type="text/css">
	 a:link { color: black; text-decoration: none;}
 	 a:visited { color: black; text-decoration: none;}
	 a:hover { color: blue; text-decoration: underline;}
	 
	 #wrap{
	 	height: 600px;
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
<script>
	$(function(){
		if('${insertResult}'>0){
			alert('이벤트가 등록되었습니다');
		}
	})
</script>
</head>
<body>
	<div id="wrap" style="width :1020px; margin: 0 auto;">
	<br/>
	<span style="font-size: 40px; font-weight: bold">이벤트(NOTICE)-관리자모드</span><br/><br/>
	<span style="font-size: 15px; color:blue; margin-left:10px;">전체 :${totalRecord}개 이벤트</span>
	<input type="button" value="이벤트 추가" style="float: right" onclick="location.href='eventInsertPage.admin'" /><br/><br/>
		<div>
			<section>
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
			</section>
			<section class="content">
			<c:if test="${empty list}"></c:if>
			<c:if test="${not empty list}">
				<c:forEach var="eventDto" items="${list}" varStatus="k">
						<div style="width:620px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px">
							<a href="eventViewPage.admin?no=${eventDto.e_no}&page=${page}">${eventDto.e_title}</a></div>
						<div>
						<div style="width:400px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px">
							${eventDto.e_postDate}
						</div>
					</div>	
				</c:forEach>
			</c:if>
			<input type="button" value="관리 목록페이지" style="float: left; margin:10px 0 10px 0" onclick="location.href='admin'" />
			<div id="paging"style="width:1020px; margin: 0 auto; float: left; text-align: center; font-weight: 800px; font-size: 20px">
				${paging}
			</div>
			</section>		
		</div>
	</div>
<%@ include file="../template/footer.jsp" %>			
				
</body>
</html>