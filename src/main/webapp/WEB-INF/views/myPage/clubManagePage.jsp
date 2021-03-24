<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<jsp:include page="myPage.jsp" />
<link rel="stylesheet" href="resources/style/myPage/clubManagePage.css">	    
<script>
	$(document).ready(function() {
		if(${not empty list}) {
			clubCount();
		}
	});
	function clubCount() {
		
		var cNoList = $('input:hidden[name="c_no"]');
		var countList = $('.count');
		$.each(cNoList, function(idx, cNo){
			var cNo = $(cNo).attr('value');
			$.ajax({
				url: 'clubCount/' + cNo,
				type: 'get',
				dataType: 'json',
				success: function(responseObj){
					$(countList[idx]).html('총 인원 :  ' + responseObj.clubCount + '명');
				},
				error: function() {
					alert('총 인원을 구하는데 문제가 발생했습니다.');
				}
			});
		});
		
	}
	$(function(){
		if(${param.state == 1}) {    // 지난 모임이면
			$('.empty').text('진행했던 모임이 없습니다.');
			$('.clubImage > a').css('pointer-events', 'none').css('cursor', 'default');
			$('.clubImage > a > img').css('-webkit-filter', 'grayscale(100%)').css('filter', 'gray');
			$('.detailBtn > form button').css('background', '#d3d3d3').css('color', '#b2b2b2').css('border', '1px solid #d3d3d3');
		}
	});
</script>
	<div class="mypage-contents">
		<h2>모임 관리</h2>
		<hr class="top">
		
		<ul class="state">
			<li>
				<a href="clubManagePage.myPage?state=0">진행중&nbsp;</a>
			</li>
			<li>
				<a href="clubManagePage.myPage?state=1">지난모임</a>
			</li>
		</ul>
		
		<c:if test="${empty list}">
			<div class="emptyList">
				<div><i class="fas fa-exclamation-circle"></i></div>
				<div class="empty">진행중인 모임이 없습니다.</div>
			</div>
		</c:if>
		<c:if test="${not empty list}">
			<div class="clubwrap">
				<c:forEach var="clubDto" items="${list}">
					<div class="clubList">
						<div class="clubImage"><a href="clubViewPage.club?c_no=${clubDto.c_no}&m_no="${loginDto.m_no}><img alt="모임이미지" src="resources/images/club/${clubDto.c_mainImg}"></a></div>
						<div class="clubContent">
							<div class="title">
								<span class="part">
									<c:if test="${clubDto.c_part == 0}">
										(정기)
									</c:if>
									<c:if test="${clubDto.c_part == 1}">
										(번개)
									</c:if>
								</span>
								${clubDto.c_title}
								</div>
							<div class="startDate">${clubDto.c_startDate} 시작</div>
							<div class="min">최소 인원 : ${clubDto.c_min} 명</div>
							<div class="max">최대 인원 : ${clubDto.c_max} 명</div>
							<div class="count"></div>
						</div>
						<div class="detailBtn">
							<form action="clubManageDetailPage.myPage?state=${param.state}" method="post">
								<input type="hidden" id="c_no" name="c_no" value="${clubDto.c_no}" />
								<input type="hidden" name="c_part" value="${clubDto.c_part}" />
								<input type="hidden" name="state" value="${param.state}" />
								<button>자세히</button>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
</div>
<%@ include file="../template/footer.jsp" %>