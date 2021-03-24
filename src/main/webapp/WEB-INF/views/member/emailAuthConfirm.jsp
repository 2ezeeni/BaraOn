<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function fn_emailAuthConfirm(f){
		
		var authKey = '${authKey}';
		var userKey = f.userKey.value;
		
		if(authKey != userKey){
			alert('잘못된 인증코드입니다.');
			return;
		}else{
			alert('인증되었습니다. 게시판으로 이동합니다.');
			location.href='boardListPage.do';
		}
		
	}
</script>
</head>
<body>

	<form>
		여기에 인증코드를 입력하세요<br/><br/>
		<input type="text" name="userKey"/>
		<input type="button" value="인증하기" onclick="fn_emailAuthConfirm(this.form)"/>
	</form>

</body>
</html>