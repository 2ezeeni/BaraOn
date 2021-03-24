<%@page import="com.koreait.baraON.dto.MemberDto"%>
<%@page import="com.koreait.baraON.command.member.Sha256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<link rel="stylesheet" href="resources/style/myPage/infoDelete.css">	    
<jsp:include page="myPage.jsp" />
<script type="text/javascript">

	function fn_delete() {
		$('.content-wrap').empty();
		$('.content-wrap').html('<div>비밀번호 확인</div><span>비밀번호</span><input type="password" name="pw" />');
		$('.buttons').empty();
		$('.buttons').html('<input type="button" name="pwCheck" value="확인" placeholder="비밀번호를 입력해주세요." onclick="fn_pwCheck(this.form)" />');
	}

	function fn_pwCheck(f) {
		
		if(f.pw.value == '') {
			alert('비밀번호를 입력해주세요.');
			f.pw.focus();
			return;
		}

		let pw = f.pw.value;
		$.ajax({
			url:'deletePwCheck.myPage/' + pw,
			type: 'get',
			dataType: 'json',
			success: function(responseObj){
				if(responseObj.result) {
					location.href = 'infoDelete.myPage';
				} else {
					alert('비밀번호가 일치하지 않습니다. 다시 시도하세요.');
					f.pw.focus();
				}
			},
			error: function(){
				alert('비밀번호를 체크하는데 오류가 발생했습니다.');
			}
		});
	}
	
</script>
	<div class="mypage-contents">
		<h2>탈퇴하기</h2>
		<hr class="top">
		
		<div class="deleteBody">
			<form method="post">
				<div class="content-wrap">
					<p>지금 탈퇴하시면</p>
					<p>바라온과의 추억들은 되돌릴 수 없습니다.<i class="far fa-sad-tear"></i></p><br/>
					<p>정말로 떠나실건가요?</p>
				</div><br/>
				<div class="buttons">
					<input type="button" value="확인" onclick="fn_delete()" />
					<input type="button" value="취소" onclick="location.href='profile.myPage'" />
				</div>
			</form>
		</div>
	</div>
	
<%@ include file="../template/footer.jsp" %>