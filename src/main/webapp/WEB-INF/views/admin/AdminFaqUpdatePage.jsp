<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
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
<script type="text/javascript">
function fn_faqUpdate(f){
	if (confirm('수정하시겠습니까?')) {
		f.action = 'faqUpdate.admin';
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
    if(f.f_category.value == '선택'){
    	alert('카테고리를 선택하세요.');
        f.f_category.focus();
		return false;
    }
    fn_faqUpdate(f);
    f.submit();
    	
    }
</script>
<title>I</title>
</head>
<body>
	<div style="width :1020px; height:autopx; margin: 20px auto">
		<h1>FAQ 수정 페이지</h1><br/>
		<h3>${faqDto.f_title} 제목의 FAQ입니다.</h3><br/>
		<form action="faqUpdate.admin">
			카테고리
			<select name="f_category">
				<option>선택</option>
				<option value="2">결제 및 환불</option>
				<option value="3">계정</option>
				<option value="4">이용안내</option>
			</select><br/><br/>
			<span style="font-size: 20px">제목</span><br/>
			<input type="text" id="titlebox" name="title" value="${faqDto.f_title}" /><br/><br/>
			<span style="font-size: 20px">내용</span><br/>
			<textarea rows="4" cols="20" name="content" style="width:1200px; height:400px; font-size:18px; margin-top:10px" name="content">${faqDto.f_content}"</textarea><br/><br/>
			<input type="hidden" name="no" value="${faqDto.f_no}" />
			<input type="button" value="수정하기" onclick="fn_checkForm(this.form)">
		</form>
	</div><br/><br/>
<%@ include file="../template/footer.jsp" %>	
</body>
</html>