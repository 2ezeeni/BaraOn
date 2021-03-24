<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="myPage.jsp" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
<link rel="stylesheet" href="resources/style/myPage/wishList.css">	
<script>
	if(${isDelete}) {  // wishDelete에서 왔으면
		if (${deleteResult < 1}){
			alert('오류가 발생했습니다. 관리자에게 문의하세요.');
		}
	}
	
</script>
	<div class="mypage-contents">
		<h2>위시 리스트</h2>
		<hr class="top">
		
		<c:if test="${empty list}">
			<div class="emptyList">
				<div><i class="fas fa-exclamation-circle"></i></div>
				<div>위시리스트에 저장된 내용이 없습니다.</div>
			</div>
		</c:if>
		<c:if test="${not empty list}">
			<div class="club-wrap">
				<c:forEach var="wishListDto" items="${list}">
					<div class="clubList">
						<div class="clubImage">
							<a href="clubViewPage.club?c_no=${wishListDto.c_no}"><img alt="모임이미지" src="resources/images/club/${wishListDto.c_mainimg}"></a>
							<div class="titleANdContent">
								<div class="title"><a href="clubViewPage.club?c_no=${wishListDto.c_no}">${wishListDto.c_title}</a></div>
								<div class="content"><a href="clubViewPage.club?c_no=${wishListDto.c_no}">${wishListDto.c_content}</a></div>
							</div>
							
						</div>
						<form action="wishDelete.myPage" method="post">
							<!-- hidden -->
							<input type="hidden" name="wNo" value="${wishListDto.w_no}" />
							<button class="wish-btn"><span class="wishIcon"><i class="fas fa-heart"></i></span></button>
						</form>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
<%@ include file="../template/footer.jsp" %>