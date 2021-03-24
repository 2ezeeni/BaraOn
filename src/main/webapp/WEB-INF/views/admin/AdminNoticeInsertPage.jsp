<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">
	body{
		margin-top: 50px;
		background: #F8EBEE;
	}
	#titlebox{
		width: 400px;
		height: 30px;
		font-size: 16px;
		margin-top: 10px;
	}
</style>
<script type="text/javascript">
	function fn_insert(){
		alert('공지사항이 추가되었습니다.');
	}
	function fn_checkForm(f) {

	    if(f.title.value == '' || f.title.value.length < 2 ) {
	        alert("제목을 확인하세요.");
	        f.title.focus();
	        return false;
	    }
	    if(f.content.value == ''){
	        alert('내용을 입력하세요.');
	        f.content.focus();
			return false;
	    }
	    fn_insert();
	    f.submit();
	    	
	    }
	

</script>
</head>
<body>
	<div style="width :1020px; height:555px; margin: 20px 0 0 100px; ">
		<h1>공지사항 추가 페이지</h1><br/>
		<form action="noticeInsert.admin">
			<span style="font-size: 20px">제목</span><br/>
			<input type="text" id="n_title" name="title" /><br/><br/>
			<span style="font-size: 20px">내용</span><br/>
			<textarea rows="4" cols="20" id="n_content" name="content" style="width:400px; height:200px; font-size:20px; margin-top:10px"></textarea><br/><br/>
			<input type="button" value="작성완료" onclick="fn_checkForm(this.form)"/>
		</form>
	</div><br/><br/>
<%@ include file="../template/footer.jsp" %>
</body>
</html>