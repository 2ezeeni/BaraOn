<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../template/header.jsp">
	<jsp:param value="클럽 내용 수정" name="title" />
</jsp:include>

<link rel="stylesheet" href="resources/style/common.css" />
<link rel="stylesheet" href="resources/style/club/clubUpdate.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>


<script type="text/javascript">
	function fn_update(f) {
		if (confirm('클럽 내용을 수정하시겠습니까?')) {
			f.action = 'clubUpdate.club';
			f.submit();
		}
	}
</script>

<form method="post" enctype="multipart/form-data">
	<div class="insert-wrap">
	<hr class="line">
		<div class="main_image">
			<div class="title">파일첨부<br/></div>
			<input type="file" name="c_mainImg" /> <br/>
			[기존 첨부파일: ${clubDto.c_mainImg}]<br/><br/>
			<img class="img" alt="${clubDto.c_mainImg}" src="resources/images/club/${clubDto.c_mainImg}">
		</div>
		
		<div class="inputs">
		    <div class="title">클럽명</div>
			${clubDto.c_title}<br/>
			
			<div class="title">한 줄 설명</div> 
			<input type="text" name="c_desc" id="input" value="${clubDto.c_desc}" /><br/>
			
			<div class="title">최소인원</div> 
			<input type="text" name="c_min" id="input" value="${clubDto.c_min}" /><br/>
			
			<div class="title">최대인원</div> 
			<input type="text" name="c_max" id="input" value="${clubDto.c_max}" /><br/>
			 
			<div class="title">활동시작날짜</div> 
			${clubDto.c_startDate}<br/>
			
			<div class="title">활동종료날짜</div> 
			${clubDto.c_endDate}<br/>
			
			<div class="title">클럽내용</div><br />
			<textarea rows="20" cols="100" id="c_content" name="c_content">${clubDto.c_content}</textarea>
		</div>
		<br /><br /> 
		
		<input type="hidden" name="c_no" value="${clubDto.c_no}" />
		<input type="hidden" name="m_no" value="${clubDto.m_no}" />
		
		<c:if test="${not empty clubDto.c_mainImg}">
			<input type="hidden" name="c_mainImg2" value="${clubDto.c_mainImg}" />
		</c:if>
		
		<input type="button" value="수정하기" id="btn" onclick="fn_update(this.form)" />
		<input type="button" value="목록으로 돌아가기" id="btn" onclick="location.href='clubListPage.club'" />
	</div>
</form>

<%@ include file="../template/footer.jsp"%>
