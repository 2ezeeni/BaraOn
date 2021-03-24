<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="resources/style/member/memberJoin1.css">
<jsp:include page="../template/header.jsp" />
<script>

	function fn_btn1(){
		location.href='memberJoin.member';
	}
	
	function fn_btn2(){
		location.href='sellerJoin.seller';
	}

</script>

<div class="box">
	<input type="button" value="일반사용자" id="btn1" name="btn1" onclick="fn_btn1()"/>
	<input type="button" value="호스트" id="btn2" name="btn2" onclick="fn_btn2()"/>
</div>




<%@ include file="../template/footer.jsp" %>