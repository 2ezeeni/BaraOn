<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="myPage.jsp" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
<link rel="stylesheet" href="resources/style/myPage/clubManageDetailPage.css">	
<script>
	
	$(function() {
		if(${state == 1}) {   // 지난 모임이면
			$('.update').remove();
			$('.delete').remove();
			$('.addPlace').remove();
			$('.detailBtn').remove();
		}
	});
	
	function fn_clubDelete(f){
		if(confirm('정말로 모임을 삭제하시겠습니까?')) {
			f.action='clubDelete.club';
			f.submit();
		}
	}
	/* 팝업 */
	var openWin;
	
	function fn_infoPopUp(f) {
		var url = 'infoPopUp.myPage?nick=' + f.nick.value;
		var options = 'width=320, height=207, top=30, left=400, resizable=no, scrollbars=no, location=no';
		openWin = window.open(url, 'infoPopUp', options);	
		
		openWin.onbeforeunload = function(){
			window.location.reload();
		};
	}
	
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
		
		<div class="buttons">
			<form method="post">
				<!-- hidden -->
				<input type="hidden" name="c_no" value="${clubDto.c_no}" />
				<input type="hidden" name="c_mainImg" value="${clubDto.c_mainImg}" />
				<input type="hidden" name="isDetailPage" value="true" />
				<input type="button" class="button delete" value="삭제하기" onclick="fn_clubDelete(this.form)"/>
			</form>
			<input type="button" class="button back" value="뒤로가기" onclick="history.back()"/>
		</div>
		<div class="clubSimple-wrap">
			<div class="clubImage"><a href="clubViewPage.club?c_no=${clubDto.c_no}&m_no="${loginDto.m_no}><img alt="모임이미지" src="resources/images/club/${clubDto.c_mainImg}"></a></div>
			<div class="clubContent">
				<h1 class="title">
					<span class="part">
						<c:if test="${clubDto.c_part == 0}">
							(정기)
						</c:if>
						<c:if test="${clubDto.c_part == 1}">
							(번개)
						</c:if>
					</span>
					${clubDto.c_title}
				</h1>
				<div class="date">${clubDto.c_startDate} ~ ${clubDto.c_endDate}</div><br/>
			</div>
		</div>
		
		<div class="space-wrap">
			<h2 class="sub_title">공간 내역</h2>
			<input type="button"class="button addPlace" value="장소 추가" onclick="location.href='placeListPage.place'" />
			<table border="1">
				<thead>
					<tr>
						<th>예약번호</th>
						<th>장소명</th>
						<th>예약일자</th>
						<th>예약상태</th>
					</tr>
				</thead>
				<c:forEach var="reservationDto" items="${reservationList}">
					<tr>
						<td>${reservationDto.res_no}</td>
						<td>${reservationDto.p_name}</td>
						<td>${reservationDto.res_date}</td>
						<td>${reservationDto.res_state}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="membersInfo-wrap">
			<h2 class="sub_title">참가 멤버</h2>
			<div class="clubCount">총 인원: ${memberCount}명</div>
			
			<c:if test="${empty clubList}">
				<div class="empty">아직 참가한 인원이 없습니다. :(</div>
			</c:if>
			<c:if test="${not empty clubList}">
				<div class="membersInfo">
				<c:forEach var="memberDto" items="${clubList}" >
						<div class="memberInfo">
							<div class="memberImg"><i class="fas fa-user fa-2x"></i></div>
							<div class="memberDetail">
								<div class="nickAndName">${memberDto.m_nick}<span class="memberName">(${memberDto.m_name})</span></div>
								<div class="cl_card">
									<c:forEach begin="1" end="${memberDto.cl_card}" step="1">
										<i class="far fa-frown"></i>
									</c:forEach>
								</div>
								<form name="infoForm" method="get">
									<!-- hidden -->
									<input type="hidden" name="name" id="name" value="${memberDto.m_name}" />
									<input type="hidden" name="nick" id="nick" value="${memberDto.m_nick}" />
									<input type="hidden" name="birth" id="birth" value="${memberDto.m_birth}" />
									<input type="hidden" name="phone" id="phone" value="${memberDto.m_phone}" />
									<input type="hidden" name="email" id="email" value="${memberDto.m_email}" />
									<input type="hidden" name="cl_no" id="cl_no" value="${memberDto.cl_no}" />
									<input type="hidden" name="card" id="card" value="${memberDto.cl_card}" />
									<input type="button" class="detailBtn" value="자세히" onclick="fn_infoPopUp(this.form)" />
								</form>
							</div>
						</div>
				</c:forEach>
				</div>
			</c:if>
			
		</div>
		<hr class="content-section">
		<div class="mainContent">
			<h2 class="sub_title">내용</h2>
			<input type="button" class="button update" value="수정하기" onclick="location.href='clubViewPage.club?c_no=${clubDto.c_no}&m_no=${loginDto.m_no}'" />
			
			<div class="min">최소 인원:  ${clubDto.c_min}명</div>
			<div class="max">최대 인원:  ${clubDto.c_max}명</div>
			<h3>한줄설명</h3>
			<div class="desc">${clubDto.c_desc}</div>
			<h3>설명</h3>
			<div class="content">${clubDto.c_content}</div>
			
		</div>
	</div>
</div>
<%@ include file="../template/footer.jsp" %>