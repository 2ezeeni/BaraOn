<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<jsp:include page="myPage.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
<link rel="stylesheet" href="resources/style/myPage/clubReservationPage.css">	 
<script>
	let imgList = new Array();
	
	<c:forEach var="clubReservationDto" items="${list}">
		imgList.push(JSON.parse('${clubReservationDto.p_img}'));	
	</c:forEach>
	
	$(function(){
		$.each(imgList, function(idx, thumbnail){
			$('.img > a').eq(idx).append('<img alt="장소 이미지" src="resources/images/PlaceImages/'+thumbnail[0]+'">');
		});
		
		
	})
</script>


	<div class="mypage-contents">
		<h2>예약내역</h2>
		<hr class="top">
		
		<c:if test="${empty list}">
			<div class="emptyList">
				<div><i class="fas fa-exclamation-circle"></i></div>
				<div class="empty">예약내역이 없습니다.</div>
			</div>
		</c:if>
		<c:if test="${not empty list}">
			<div class="reservation-wrap">
				<c:forEach var="clubReservationListDto" items="${list}">
					<div class="reservation">
						<h3>${clubReservationListDto.res_date}</h3>
						<div class="res_state">${clubReservationListDto.res_state}</div>
						<div class="reservation-info">
							<div class="img">
								<a href="placeViewPage.place?no=${clubReservationListDto.p_no}"></a>
							</div>
							<div class="info">
								<h2>${clubReservationListDto.p_title}</h2>
								<h3>${clubReservationListDto.res_price}원</h3>
								<div class="res_people">예약인원 | ${clubReservationListDto.res_people}명</div>
								<div>
									옵션     | ${clubReservationListDto.po_name}
									<!-- <span class="cancelPayment"><input type="button" value="결제취소" onclick="" /></span> -->
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
		
		
	</div>
</div>
<%@ include file="../template/footer.jsp" %>