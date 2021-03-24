<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../template/header.jsp" >
	<jsp:param value="아이디 찾기" name="title"/>
</jsp:include>

<link rel="stylesheet" href="resources/style/member/findPage.css">
<link rel="stylesheet" href="resources/style/member/findIdPage.css">

<script>
	$(document).ready(function(){
		fn_findIdResult();
		$("footer").css("background" , "white");
	});
	
	function fn_findIdResult(){
		$('#findIdResult').text('아이디: ${param.id}');
	}
</script>
<div class="box">
	<div class="inner-box">
		<div class="main-aside">
			<input type="radio" name="tab" id="findId" checked />
			<input type="radio" name="tab" id="findPw" />
			<div class="tab-buttons">
				<label for="findId">아이디 찾기<hr class="check"></label>&nbsp;&nbsp;&nbsp;
				<label for="findPw">비밀번호 찾기<hr></label>
			</div>
			<div class="resultBox">
				<h4>아이디 찾기가 완료되었습니다.</h4>
				<div id="findIdResult"></div>
			</div>
			<div class="login-button">
				<input type="button" value="로그인" onclick="location.href='loginPage.member'"/>
			</div>
		</div>
	</div>
</div>

<%@ include file="../template/footer.jsp" %>