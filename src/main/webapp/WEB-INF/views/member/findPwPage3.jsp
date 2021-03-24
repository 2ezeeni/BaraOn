<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../template/header.jsp" >
	<jsp:param value="비밀번호 찾기" name="title"/>
</jsp:include> 
   
<link rel="stylesheet" href="resources/style/member/findPage.css">
<link rel="stylesheet" href="resources/style/member/findPwPage.css">
<script>
	$(function(){
		$("footer").css("background" , "white");

		if(${changePwResult >= 1}) {
			$('h3').text('비밀번호 변경이 성공적으로 이루어졌습니다.');
		} else {
			alert('오류가 발생했습니다. 다시 시도해주세요');
		}
		<%
			session.invalidate();
		%>
	});
</script>
<div class="box">
	<div class="inner-box">
		<div class="main-aside">
			<input type="radio" name="tab" id="findId" checked />
			<input type="radio" name="tab" id="findPw" />
			<div class="tab-buttons">
				<label for="findId">아이디 찾기<hr></label>&nbsp;&nbsp;&nbsp;
				<label for="findPw">비밀번호 찾기<hr></label>
			</div>
			<div class="resultBox Pw3">
				<h3></h3>
			</div>
			<input type="button" name="loginButton" class="loginButton" value="로그인" onclick="location.href='loginPage.member'" />
		</div>
	</div>
</div>

<%@ include file="../template/footer.jsp" %>