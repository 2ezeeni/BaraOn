<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" />
<link rel="stylesheet" href="resources/style/seller/pwCheck.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script >

function fn_pwSearch(){
	var s_no= '${loginDto.s_no}';
	var s_pw= $('#sellerPw').val();
	var Obj={
			's_no':s_no
	};
	$.ajax({
		url:'sellerPwSearch.seller',
		type:'post',
		data: JSON.stringify(Obj),
		contentType:'application/json',
		dataType:'json',
		success:function(data){
			if(s_pw==data.result){
				alert('성공');	//지울것
				var obj={
						"s_no":'${loginDto.s_no}',
						"s_phone":'${s_phone}',	
						"s_email":'${s_email}'
					};
					$.ajax({
						url:'sellerUpdate.seller',
						type:'post',
						data:JSON.stringify(obj),
						contentType: 'application/json',
						dataType:'json',
						success:function(data){
							if(data.result == 1){
								alert('수정되었습니다');
								location.href='index';
							}else{
								alert('실패');
							}
						},
						error: function(){
							alert('실패');
						}
					});
			}else{
				alert('비밀번호가 틀립니다');
			}
		},
		error: function(){
			alert('실패');
		}
	});
}
</script>

	<div class="box">
	    <br><br><br><br><br>
	  	  <b><font size="4" color="cadetblue">비밀번호 확인</font></b>
	    <hr size="1" width="1000">
	    <br>
	    <div id="chk">
	        <form id="checkForm">
	            <input type="password" name="pwinput" id="sellerPw">
	            <input type="button" value="확인" id="pwCheck"onclick="fn_pwSearch()"/>
	        </form>
	        <br>
    	</div>
	</div> 
</body>
</html>


<%@ include file="../template/footer.jsp" %>
