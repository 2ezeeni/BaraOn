<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<div style="width :1200px; height: auto;margin: 0 auto;">
	<h1>공지사항(NOTICE) - 관리자모드</h1>
		<div style="background:#3ED0C8; border-top: 1px solid black; border-bottom: 1px solid black" >
			<div style="width:600px; margin: 0 0 0 10px; font-weight: 800px; font-size: 24px;">
				${noticeDto.n_title}
			</div>
			<div style="width:600px; margin:10px 0 10px 10px; font-weight: 800px; font-size: 16px;">
				${noticeDto.n_postDate}
			</div>
		</div>
			<div style="height:auto; padding:30px 0 0 10px; font-weight: 800px; font-size: 18px; border-bottom:1px solid black">
				<pre>${noticeDto.n_content}</pre><br/><br/>
			</div><br/>
		<div>
			<form>
				<input type="button" name="list" value="목록보기" style="font-size: 16px" onclick="location.href='adminNoticeListPage.admin'" />
				<input type="hidden" name="no" value="${noticeDto.n_no}" />
				<input type="hidden" name="title" value="${noticeDto.n_title}" />
				<input type="hidden" name="content" value="${noticeDto.n_content}" />
				<input type="button" value="수정하러가기" style="font-size: 16px" onclick="fn_noticeUpdatePage(this.form)" />
				<input type="button" value="삭제하기" style="font-size: 16px" onclick="fn_noticeDelete(this.form)" />
				
				<input type="hidden" name="n_no" value="${noticeDto.n_no}" />
				<input type="hidden" name="n_title" value="${noticeDto.n_title}" />
				<input type="hidden" name="n_content" value="${noticeDto.n_content}" />
			</form><br/>
		</div>
	</div>
	
	<script type="text/javascript">
		function fn_noticeUpdatePage(f) {
				f.action = 'noticeUpdatePage.admin';
				f.submit();
		}
		function fn_noticeDelete(f) {
			if (confirm('삭제할까요?')) {
				f.action = 'noticeDelete.admin';
				f.submit();
			}
		}
	</script>
<%@ include file="../template/footer.jsp" %>
</body>
</html>