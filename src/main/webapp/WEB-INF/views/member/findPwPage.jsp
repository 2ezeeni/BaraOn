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
	});

	function fn_emailAuthConfirm(f) {
		var authKey = '${authKey}';
		var userKey = f.userKey.value;
		console.log(authKey);
		if(userKey != null) {
			if(authKey == userKey) {
				alert('인증되었습니다.');
				location.href = 'findPwPage2.member';
			} else {
				alert('잘못된 인증코드입니다.');
				return;
			}
			
		}
	}
</script>
<div class="box">
	<div class="inner-box">
		<div class="main-aside">
			<input type="radio" name="tab" id="findId" checked />
			<input type="radio" name="tab" id="findPw" />
			<div class="tab-buttons">
				<label for="findId">아이디 찾기<hr></label>&nbsp;&nbsp;&nbsp;
				<label for="findPw">비밀번호 찾기<hr class="check"></label>
			</div>
			<div class="resultBox">
				<h4>인증번호가 메일로 전송되었습니다.</h4>
			</div>
			<form>
				<div class="authInputBox">
					<input type="text" name="userKey" placeholder="인증번호를 입력해주세요" />	&nbsp;&nbsp;			
					<input type="button" value="확인" onclick="fn_emailAuthConfirm(this.form)"/>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="../template/footer.jsp" %>