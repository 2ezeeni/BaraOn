<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<script type="text/javascript">
	function fn_eventUpdate(f){
		if (confirm('수정하시겠습니까?')) {
			f.action = 'eventUpdate.admin';
			f.submit();
			}
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
	    if(f.filename.value == ''){
	        alert('파일을 선택해주세요.');
	        f.filename.focus();
			return false;
	    }
	    fn_eventUpdate(f);
	    f.submit();
	    	
	    }
</script>
<title></title>
<style type="text/css">
	body{
	background: #F8EBEE;
	}
	#titlebox{
		width: 1200px;
		height: 30px;
		font-size: 24px;
		margin-top: 10px;
	}
</style>
</head>
<body>
	<div style="width :1200px; height:auto; margin: 20px auto ">
			<h1>이벤트 수정 페이지</h1><br/>
			<h3>${eventDto.e_title} 제목의 이벤트입니다.</h3><br/>
			<form action="eventUpdate.admin">
				<span style="font-size: 20px">제목</span><br/>
				<input type="text" id="titlebox" name="title" value="${eventDto.e_title}" /><br/><br/>
				<span style="font-size: 20px">내용</span><br/>
				<textarea rows="4" cols="20" name="content"style="width:1200px; height:400px; font-size:18px; margin-top:10px" name="content">${eventDto.e_content}</textarea><br/><br/>
				파일첨부<br/>
				<input type="file" name="filename" value="${eventDto.e_filename}" /><br/><br/>
				<input type="hidden" name="no" value="${eventDto.e_no}" />
				<input type="button" value="수정하기" onclick="fn_checkForm(this.form)">
			</form>
	</div>
<%@ include file="../template/footer.jsp" %>
</body>
</html>