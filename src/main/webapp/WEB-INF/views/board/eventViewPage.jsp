<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../template/header.jsp">
	<jsp:param value="BaraON::이벤트" name="title"/>
</jsp:include>
<style>
	#bottom-head div:nth-of-type(4){
		border-bottom: 2px solid rgba(95, 158, 160, 1);
	}
	#bottom-head div:nth-of-type(4)>a{
		color: rgba(95, 158, 160, 1);
	}
</style>
	<div style="width :1020px; height:auto; margin: 0 auto;">
		<h1 style="margin-left: 10px">이벤트(EVENT)</h1><br/>
		<div style="background:cadetblue; border-top: 1px solid black; border-bottom: 1px solid black">
			<div style="width:600px; margin: 0 0 0 10px; font-weight: bold; font-size: 25px;">
				${eventDto.e_title}
			</div>
			<div style="width:600px; margin:10px 0 10px 10px;  font-size: 16px;">
				${eventDto.e_postDate}
			</div>
		</div>
		<div>
			<div style="width:500px; height:auto; margin:30px 0 0 20px;  font-size: 18px">
				<img style="width:500px; height:300px;" alt="${eventDto.e_filename}" src="resources/images/event/${eventDto.e_filename}"><br/><br/>
			</div>
			<div style="height:auto; margin:30px auto;  font-size: 18px; border-bottom:1px solid black">
				<pre>${eventDto.e_content}</pre><br/><br/>
			</div>
		</div>
		<div class="c_foot" >
			<form method="post">
				<input type="button" name="list" value="목록보기" onclick="location.href='eventListPage.event'" />
				<input type="hidden" name="no" value="${eventDto.e_no}" />
				<input type="hidden" name="title" value="${eventDto.e_title}" />
				<input type="hidden" name="content" value="${eventDto.e_content}" />
			</form><br/>
		</div>
	</div>
<%@ include file="../template/footer.jsp" %>
