<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">
	body{
		margin-top: 50px;
		background: #F8EBEE;
	}
</style>
</head>
<body>
	<div style="width :1200px; height: auto;margin: 0 auto;">
	<h1>이벤트(EVENT) - 관리자모드</h1>
		<div style="background:#3ED0C8; border-top: 1px solid black; border-bottom: 1px solid black">
			<div style="width:600px; margin: 0 0 0 10px; font-weight: 800px; font-size: 24px;">
				${eventDto.e_title}
			</div>
			<div style="width:600px; margin:10px 0 10px 10px; font-weight: 800px; font-size: 16px;">
				${eventDto.e_postDate}
			</div>
		</div>
		<hr>
		<div>
			<div style="width 500px; height:auto; margin:10px 0 0 10px; padding-top:10px; font-weight: 800px; font-size: 20px;">
				<img style="width:500px; height:300px;"alt="${eventDto.e_filename}" src="resources/images/event/${eventDto.e_filename}">
			</div>
			<div style="height:auto; margin:10px 0 0 10px; padding-top:10px; font-weight: 800px; font-size: 18px;border-bottom:1px solid black">
				<pre>${eventDto.e_content}</pre><br/>
			</div><br/>
		</div>
		<div class="c_foot">
		<form>
			<input type="button" name="list" value="목록보기" style="font-size: 16px" onclick="location.href='adminEventListPage.admin'"/>
			<input type="hidden" name="no" value="${eventDto.e_no}" />
			<input type="hidden" name="title" value="${eventDto.e_title}" />
			<input type="hidden" name="content" value="${eventDto.e_content}" />
			<input type="button" value="수정하러가기" style="font-size: 16px" onclick="fn_eventUpdatePage(this.form)" />
			<input type="button" value="삭제하기" style="font-size: 16px" onclick="fn_eventDelete(this.form)" />
			
			<input type="hidden" name="e_no" value="${eventDto.e_no}" />
			<input type="hidden" name="e_title" value="${eventDto.e_title}" />
			<input type="hidden" name="e_content" value="${eventDto.e_content}" />
		</form><br/>
		</div>
	</div>
	
	
	<script type="text/javascript">
		function fn_eventUpdatePage(f) {
				f.action = 'eventUpdatePage.admin';
				f.submit();
		}
		function fn_eventDelete(f) {
			if (confirm('삭제할까요?')) {
				f.action = 'eventDelete.admin';
				f.submit();
			}
		}
	</script>
<%@ include file="../template/footer.jsp" %>
</body>
</html>