<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../template/header.jsp">
	<jsp:param value="BaraON::FAQ" name="title"/>
</jsp:include>
<style>
	#bottom-head div:nth-of-type(4){
		border-bottom: 2px solid rgba(95, 158, 160, 1);
	}
	#bottom-head div:nth-of-type(4)>a{
		color: rgba(95, 158, 160, 1);
	}
</style>
	<div style="width :1200px; height:auto; margin: 0 auto;">
		<h1 style="margin-left: 10px">자주묻는질문(FAQ)</h1><br/>
		<div style="background:cadetblue; border-top: 1px solid black; border-bottom: 1px solid black">
			<div style="width:600px; margin: 0 0 0 10px; font-weight: bold; font-size: 25px;">
				${faqDto.f_title}
			</div>
			<div style="width:600px; margin:10px 0 10px 10px; font-size: 16px;">
				${faqDto.f_regDate}
			</div>
		</div>
			<div style="height:auto; padding:30px 0 0 10px; font-size: 18px; border-bottom:1px solid black">
				<pre>${faqDto.f_content}</pre><br/><br/>
		</div><br/>
		<div>
			<form method="post">
				<input type="button" id="list" name="list" value="목록보기" onclick="location.href='faqListPage.faq'" />
				<input type="hidden" name="no" value="${faqDto.f_no}" />
				<input type="hidden" name="title" value="${faqDto.f_title}" />
				<input type="hidden" name="content" value="${faqDto.f_content}" />
			</form><br/>
		</div>
	</div>
<%@ include file="../template/footer.jsp" %>