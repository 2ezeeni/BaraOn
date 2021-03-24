<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">
	body{
		margin-top: 50px;
		background: #F8EBEE;
	}
</style>
<title></title>
</head>
<body>
	<div style="width :1200px; margin: 0 auto;">
	<h1>자주묻는질문(FAQ) - 관리자모드</h1>
		<div style="background:#3ED0C8; border-top: 1px solid black; border-bottom: 1px solid black">
			<div style="width:600px; margin: 0 0 0 10px; font-size: 24px;">	
				${faqDto.f_title}
			</div>
			<div style="width:600px; margin:10px 0 10px 10px; font-size: 16px;">
				${faqDto.f_regDate}
			</div>
		</div>
			<div style="height:auto; padding:10px 0 0 10px; font-size: 18px; border-bottom:1px solid black">
				<pre>${faqDto.f_content}</pre><br/><br/>
			</div><br/>
	
		<div>
			<form>
				<input type="button" name="list" value="목록보기" style="font-size: 16px" onclick="location.href='adminFaqListPage.admin'" />
				<input type="hidden" name="no" value="${faqDto.f_no}" />
				<input type="hidden" name="title" value="${faqDto.f_title}" />
				<input type="hidden" name="content" value="${faqDto.f_content}" />
				<input type="button" value="수정하러가기" style="font-size: 16px" onclick="fn_faqUpdatePage(this.form)" />
				<input type="button" value="삭제하기" style="font-size: 16px" onclick="fn_faqDelete(this.form)" />
				
				<input type="hidden" name="f_no" value="${faqDto.f_no}" />
				<input type="hidden" name="f_title" value="${faqDto.f_title}" />
				<input type="hidden" name="f_content" value="${faqDto.f_content}" />
			</form><br/>
		</div>
	</div>
	<script type="text/javascript">
		function fn_faqUpdatePage(f) {
			f.action = 'faqUpdatePage.admin';
			f.submit();
		}
		function fn_faqDelete(f) {
			if (confirm('삭제할까요?')) {
				f.action = 'faqDelete.admin';
				f.submit();
			}
		}
	</script>
<%@ include file="../template/footer.jsp" %>	
</body>
</html>