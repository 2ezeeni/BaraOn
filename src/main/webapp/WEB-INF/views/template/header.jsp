<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title");
	if (title == null || title.isEmpty()) {
		title = "BaraON";
	}
	pageContext.setAttribute("title", title);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<title>${title}</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<!-- favicon 적용 -->
<link rel="icon" type="image/png" sizes="16x16" href="resources/images/favicon_16x16.png">
<!-- header, footer css -->
<link rel="stylesheet" href="resources/style/common.css">
<!-- Fontawesome CDN -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" >

</head>
<body class="body">

	<div class="wrap">
		<header>
			<div class="head-wrap">
				<a id="logo" href="index">
					<img  alt="로고사진" src="resources/images/logo.jpg" style="height: 100%;">
				</a>
				<div class="right-head-wrap">
					<nav id="right-head">
						<!-- 로그인 전 -->
						<c:if test="${loginDto eq null}">
							<div><a href="loginPage.member">로그인</a></div>
							<div><a href="memberJoin3.member">회원가입</a></div>
							<div><a href="faqListPage.faq">FAQ</a></div>
						</c:if>
						<!-- 로그인 후 -->
						<c:if test="${loginDto ne null}">
							<div><a href="logout.member">로그아웃</a></div>
							 <c:if test="${grade eq 'member' && not admin}">
								<div><a href="memberView.member">마이페이지</a></div>
								<div><a href="faqListPage.faq">FAQ</a></div>
							</c:if>
							 <c:if test="${grade eq 'seller' && not admin}">
								<div><a href="sellerView.seller">마이페이지</a></div>
								<div><a href="faqListPage.faq">FAQ</a></div>
							</c:if>
						</c:if>
						<c:if test="${admin}">
							<div><a href="admin">관리자</a></div>
						</c:if>
					</nav>
				</div>
			</div>	<!-- end : header-wrap -->
			<div class="head-content">
				<nav id="bottom-head">
					<div><a href="clubListPage.club">정기 활동</a></div>
					<div><a href="instanceClubListPage.club">번개 활동</a></div>
					<div><a href="placeListPage.place">장소 공유</a></div>
					<div><a href="noticeListPage.notice">공지사항&amp;이벤트</a></div>
				</nav>
			</div>
		</header>
			
		<div class="body-wrap">