<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>

	var loginDto = '${loginDto}';
	
	if (loginDto != '') {
		alert('${loginDto.s_name}님 환영합니다.');
		location.href='index';
	} else {
		alert('일치하는 회원이 없습니다.');
		location.href='loginPage.member';
	}
</script>