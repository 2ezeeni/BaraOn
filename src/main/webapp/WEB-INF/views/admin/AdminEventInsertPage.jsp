<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="resources/style/common.css">
<link rel="stylesheet" href="resources/style/common.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
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
		alert('이벤트가 추가되었습니다.');

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
	    if(f.files.value == ''){
	        alert('파일을 선택해주세요.');
	        f.files.focus();
			return false;
	    }
	    fn_insert();
	    f.submit();
	    	
	    }
</script>
</head>
<body>
	<div style="width :1020px; height:555px; margin: 20px 0 0 100px; ">
	<h1>이벤트 추가 페이지</h1><br/>
		<form  method="post" enctype="multipart/form-data">
			<span style="font-size: 20px">제목</span><br/>
			<input type="text" id="titlebox" name="title" /><br/>
			<span style="font-size: 20px">내용</span><br/>
			<textarea rows="4" cols="20" name="content" style="width:400px; height:200px; font-size:20px; margin-top:10px"></textarea><br/>
			썸네일 이미지첨부<br/>
			<input type="file" name="files" /><br/><br/>
			<input type="button" value="작성완료" onclick="fn_checkForm(this.form)"/>

		</form>
	</div>
<%@ include file="../template/footer.jsp" %>
</body>
</html>