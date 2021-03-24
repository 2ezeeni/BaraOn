<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../template/header.jsp">
	<jsp:param value="클럽 활동 목록" name="title" />
</jsp:include>
<link rel="stylesheet" href="resources/style/common.css" />
<link rel="stylesheet" href="resources/style/club/clubList.css" />

<style>
#bottom-head div:nth-of-type(1){
	border-bottom: none;
}
#bottom-head div:nth-of-type(1)>a{
	color: black;
}
#bottom-head div:nth-of-type(2){
	border-bottom: 2px solid rgba(95, 158, 160, 1);
}
#bottom-head div:nth-of-type(2)>a{
	color: rgba(95, 158, 160, 1);
}
</style>
<script>

var afterInsert2 = ${afterInsert2};
if (afterInsert2) {  
	var insertResult2 = ${insertResult2};
	if (insertResult2 > 0) {   
		alert('삽입되었습니다.');
	} else {
		alert('실패하였습니다.');
	}
}

var afterDelete = ${afterDelete};
if (afterDelete) {
	var afterDelete = ${afterDelete};
	if (afterDelete > 0) {
		alert('삭제되었습니다.');
	} else {
		alert('실패했습니다.');
	}
} 

</script>

<form method="get">
	<div class="body_wrap">
	<hr class="line">
		<div id="main_title">번개 활동</div>
		<div id="btn_create">
			<c:if test="${loginDto ne null}">
				<input type="button" id="btn_create" value="새 클럽 등록하기" onclick="location.href='instanceClubInsertPage.club'" />
			</c:if>
			<br/>
		</div>
	</div>

	<c:if test="${empty list2}">
		<div id="none_content">등록된 클럽이 없습니다.</div>
	</c:if>

	<c:if test="${not empty list2}">
		<div class="group-list">
			<c:forEach var="clubDto" items="${list2}">
				<div class="clubList">
					<div class="mainImg">
						<a href="instanceClubViewPage.club?c_no=${clubDto.c_no}">
							<img class="img" alt="${clubDto.c_mainImg}" src="resources/images/club/${clubDto.c_mainImg}">
						</a>
					</div>
					<div class="title">
						<a href="instanceClubViewPage.club?c_no=${clubDto.c_no}">${clubDto.c_title}</a>
					</div>
					<div class="content">
						<a href="instanceClubViewPage.club?c_no=${clubDto.c_no}">${clubDto.c_desc}</a>
					</div>
				</div>	
			</c:forEach>
		</div>
	</c:if>
</form>

<%@ include file="../template/footer.jsp"%>
