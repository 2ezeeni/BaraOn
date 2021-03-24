<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<script type="text/javascript">
	function fn_noticeUpdate(f){
		if (confirm('수정하시겠습니까?')) {
			f.action = 'noticeUpdate.admin';
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
	    fn_noticeUpdate(f);
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
	<div style="width :1020px; height:auto; margin: 20px auto ">
			<h1>공지사항 수정 페이지</h1><br/>
			<h3>${noticeDto.n_title} 제목의 공지사항입니다.</h3><br/>
			<form action="noticeUpdate.admin">
				<span style="font-size: 20px">제목</span><br/>
				<input type="text" id="titlebox" name="title" value="${noticeDto.n_title}" /><br/><br/>
				<span style="font-size: 20px">내용</span><br/>
				<textarea rows="4" cols="20" style="width:1200px; height:400px; font-size:18px; margin-top:10px" name="content">${noticeDto.n_content}
				</textarea><br/><br/>
				
				<input type="hidden" name="no" value="${noticeDto.n_no}" />
				<input type="button" value="수정하기" onclick="fn_checkForm(this.form)">
			</form>
	</div><br/><br/>
<%@ include file="../template/footer.jsp" %>
</body>
</html>