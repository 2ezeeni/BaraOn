<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	if(document.referrer.indexOf('index')!=-1){
		alert('baraON 관리자페이지 입니다.');
	}
</script>
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">


	a{
		font-size: 25px;
		text-decoration: none;
	}
	.main{
		width: 900px;
		margin: 0 auto;
	}
	.body-wrap{
		display:flex;
		flex-direction: row-reverse;
		justify-content: space-between;
		width: 100%;
	}
	.head{
		font-family: 'Do Hyeon', sans-serif;
		font-size: 20px;
		font-weight: bold;
		line-height: 100px;
	}
	.board, .member, .review{
		margin-left: 10px;
		font-size: 30px;
		font-weight: bold;
		color:cadetblue;
	}	
	.exit{
		float: right;
		line-height: 150px;
		margin-right: 50px;
		font-size: 20px;
		color : blue;
	}
</style>
<title></title>
</head>
<body>
	<div style="height: 150px">
	<img alt="logo" style="height:150px"src="resources/images/logo.jpg">
		<a href="index" onclick="alert('관리자 페이지를 종료합니다.')" class="exit">⇒ 관리자페이지 나가기</a>
	</div>
	<hr>
	<div class="body-wrap">
		<div class="main">
			<span style="font-size: 70px">BARAON 관리자 페이지 입니다.</span><br/><br/><br/><br/><br/>
			<span style="font-size: 40px">BARAON 멤버님들의 소중한 정보를 수정하는 공간이오니</span><br/>
			<span style="font-size: 40px">정보 수정 시 다시한번 확인 후 수정해주시기 바랍니다.</span><br/>
		</div>
		<aside class="side-bar">
			<div>
				<span class="board">게시판 관리</span>
					<div style="margin: 20px 0 0 30px"><a href="adminNoticeListPage.admin">공지사항</a></div>
					<div style="margin: 20px 0 10px 30px"><a href="adminFaqListPage.admin">FAQ</a></div>
					<div style="margin: 20px 0 10px 30px"><a href="adminEventListPage.admin">이벤트</a></div>
					<div style="width: 150px;"></div>	
			</div>
			<div>
				<span class="member">이용자 관리</span>
					<div style="margin: 20px 0 0 30px;"><a href="adminMemberPage.admin">회원 관리</a></div>
					<div style="margin: 20px 0 10px 30px;"><a href="adminSellerPage.admin">판매자 관리</a></div>
					<div style="margin: 20px 0 10px 30px;"><a href="adminConfirmPage.admin">공간 승인관리</a></div>
					<div style="width: 150px;"></div>	
			</div>
			<div>
				<span class="review">댓글 관리</span>
				<!-- <div style="margin: 20px 0 0 30px;"><a href="adminReplyPage.admin">답글 관리</a></div> -->
				<div style="margin: 20px 0 10px 30px;"><a href="adminReviewPage.admin">리뷰 관리</a></div>
			</div>
		</aside>
	</div>
<%@ include file="../template/footer.jsp" %>	
</body>
</html>