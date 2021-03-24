<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../template/header.jsp">
	<jsp:param value="BaraON::공지사항&이벤트" name="title"/>
</jsp:include>
<link rel="stylesheet" href="resources/style/common.css">
<style type="text/css">
	#bottom-head div:nth-of-type(4){
		border-bottom: 2px solid rgba(95, 158, 160, 1);
	}
	#bottom-head div:nth-of-type(4)>a{
		color: rgba(95, 158, 160, 1);
	}
	.boardCategory > a:link { color: black; text-decoration: none;}
 	.boardCategory >  a:visited { color: black; text-decoration: none;}
	.boardCategory >  a:hover { color: blue; text-decoration: underline;}
	
	.list > a:link { color: black; text-decoration: none;}
	.list >  a:visited { color: black; text-decoration: none;}
	.list >  a:hover { color: blue; text-decoration: underline;}
	
	 #wrap{
	 	height: 300px;
	 	margin-bottom: 20px;
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
	 .notice{
	 	margin-right: 100px;
		font-weight: bold;
	 }
	 .event{
		 font-weight: bold;
	 }
</style>
		<div class="boardCategory"style="width: 1020px; margin:0 auto ; text-align: center" >
			<a href="noticeListPage.notice" class="notice">공지사항(NOTICE)</a>
			<a href="eventListPage.event"class="event">이벤트(NOTICE)</a>
		</div>
		<br/>
	<div id="wrap" style="width :1020px; height:450px; margin: 0 auto" >
		<h1>공지사항(NOTICE)</h1><br/>
			<section>
				<div style="background: #dbdbdb">
					<div style="width:620px; margin: 0 auto; height:50px; line-height:50px;
						float: left; text-align: center; 
						font-size: 24px; background:cadetblue; border-top: 1px solid black; border-bottom: 1px solid black">
						제목
					</div>
					<div style="width:400px; margin: 0 auto; height:50px; line-height:50px;
					float: left; text-align: center; 
					font-size: 24px; background:cadetblue; border-top: 1px solid black; border-bottom: 1px solid black">
						작성일
					</div>
				</div>
			</section>
			<section class="content">
			<c:if test="${empty list}"></c:if>
			<c:if test="${not empty list}">
				<c:forEach var="noticeDto" items="${list}" varStatus="k">
						<div class="list" style="width:620px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px">
							<a href="noticeViewPage.notice?no=${noticeDto.n_no}&page=${page}">${noticeDto.n_title}</a>
						</div>
						<div style="width:400px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px;">
							${noticeDto.n_postDate}
						</div>
				</c:forEach>
			</c:if>
				<div id="paging" style="width:1020px; margin: 50px auto; float: left; text-align: center; font-weight: 800px; font-size: 20px">
					${paging}
				</div>
			</section>		
		</div>
<%@ include file="../template/footer.jsp" %>		