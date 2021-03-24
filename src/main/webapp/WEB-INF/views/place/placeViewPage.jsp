<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../template/header.jsp">
	<jsp:param value="BaraON :: 공간보기" name="title" />
</jsp:include>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js" integrity="sha512-XtmMtDEcNz2j7ekrtHvOVR4iwwaD6o/FUJe6+Zq+HgcCsk3kj4uSQQR8weQ2QVj1o0Pk6PwYLohm206ZzNfubg==" crossorigin="anonymous"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b735551da134940779a92513cdbca8f5&libraries=services"></script>
<script type="text/javascript" src="resources/js/place-view.js" defer ></script> 
<link rel="stylesheet" href="resources/style/place/place-view.css">
<link rel="stylesheet" href="resources/style/slick-1.8.1/slick/slick-theme.css" />
<link rel="stylesheet" href="resources/style/slick-1.8.1/slick/slick.css" />
<script>
	let facilityList = JSON.parse('${facilityList}');
	let isSeller = ${isSeller};
	let loginDtoMNo='';
	<c:if test="${loginDto ne null && not isSeller}">
		loginDtoMNo = '${loginDto.m_no}';
	</c:if>
	let thumbnail = JSON.parse('${placeDto.p_img}');
	let infoList = JSON.parse('${placeDto.p_info}');
	let remarkList = JSON.parse('${placeDto.p_remark}');
	let reviewImageList = null;
	if ('${reviewImage}'!=''){
		reviewImageList = JSON.parse('${reviewImage}');
	}
	let no = ${param.no};
	let pAddr = '${placeDto.p_addr}';
	let pTitle= '${placeDto.p_title}';
	let lastReviewRN = ${lastReviewRN};
	
	let isEnd = false;
	if ('${fn:length(reviewList)}'<3 || lastReviewRN == 0){
		isEnd = true;
	}
	let isProgress = false;
	
	/* 리뷰작성 성공 */
	
	if(${insertResult gt 0}){
		alert('작성해주셔서 감사합니다.');
	} else if (${insertResult eq -1}) {
		alert('지원되는 확장자가 아닙니다.(jpg, jpeg, png)');
	} else if (${insertResult eq 0}) {
		alert('리뷰 작성에 실패했습니다.');
	}
	
	function fn_reserve(f){
		if(isSeller){
			alert('호스트는 예약할 수 없습니다.');
			return;
		}
		if(${loginDto eq null}){
			alert('로그인이 필요합니다.');
			location.href='loginPage.member';
			return;
		}
		if($('.place-option-wrap input:checked').length!=1){
			alert('예약하려는 장소 하나만 선택해주세요');
			return;
		}
		let chked = $('.place-option-wrap input:checked');
		/* hidden res-date 선택하는 방법 생각하기 */
		if($(chked).next().next().find('input[name="res_date"]').length == 0){
			alert('날짜를 선택해 주세요');
			return;
		}
		f.action='reservationPage.reservation';
		f.submit();
		
	}
</script>

<div class="modal">
	<div class="modal-content">
		<div class="close" onclick="fn_modalClose()">&times;</div>
		<div class="content-wrap">
		<span id="modal-m-id">
			<c:if test="${not isSeller && loginDto ne null}">ID : ${loginDto.m_id}</c:if>
		</span><br/>
		
		<span class="modal-star">
			
		</span>
		<br/>
		<textarea id="modal-rv-content" name="rv_content" rows="5" cols="50"></textarea>
		<input id="modal-rv-no" type="hidden" />
		<input type="button" value="수정완료" onclick="fn_reviewUpdate()" />
		<input type="button" value="돌아가기" onclick="fn_modalClose()" />
		</div>
	</div>
</div>
<!-- 전화버튼눌렀을때 나올 modal -->
<div class="modal-phone">
	<div class="modal-content">
		<div class="content-wrap">
			<ul>
				<li>서로에게 기분 좋은 통화가 될 수 있도록<br/>
				인사부터 나누도록 해요!
				</li>
				<li>
				${placeDto.p_title}<br/>
				<span>${sellerDto.s_phone}</span>
				</li>
			</ul>
		</div>
		<button type="button" onclick="fn_modalPhoneClose()"> 확인</button>
	</div>
</div>
<div class="title-area">
	<div class="place-title">
		<h1>${placeDto.p_title}</h1>
	</div>
	<div class="place-line-desc1">
		<h3>${placeDto.p_desc}</h3>
	</div>
	<div class="category-name">
		<span>#${categoryName}</span>
	</div>
	<c:if test="${isSeller && (sellerDto.s_no eq loginDto.s_no)}">
		<div class="btn-wrap">
			<form id="update-form" method="post">
				<input type="hidden" name="p_no" value="${placeDto.p_no}" />
				<button type="button" id="place-update-btn">수정하기</button>
				<button type="button" id="place-delete-btn">삭제하기</button>
			</form>
		</div>
	</c:if>
</div>
<section>
	<aside>
		<h4>세부 공간 선택</h4>
		<!-- Todo : 세부옵션 추가 -->
		<form class="reserve-form" method="post">
			<c:if test="${optionList eq null}">
				<div>
					<h4>등록된 공간이 없습니다.</h4>
				</div>
			</c:if>
			<c:if test="${optionList ne null}">
				<div class="place-option-wrap">
					<c:forEach var="optionDto" items="${optionList}" varStatus="k">
						<input id="option${k.count}" type="checkbox" name="po_no" value="${optionDto.po_no}" />
						<label for="option${k.count}">
							${optionDto.po_name}
						</label>
							
						<div class="hidden-box">
							<div class="day-price"><strong>평일가격</strong>
							<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${optionDto.po_dayPrice}" />/일
							</div>
							<div class="holiday-price"><strong>공휴일가격</strong>
							<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${optionDto.po_holiday}" />/일
							</div>
							<div class="option-info-box">
								<div class="img-box">
									<img alt="" src="resources/images/PlaceOptionImages/${optionDto.po_img}" />
								</div>
								<div class="person-count">
									<strong>수용인원</strong>
									<span>최소 ${optionDto.po_min}명 ~ 최대 ${optionDto.po_max}</span>
								</div>
								<strong>편의시설</strong><br/>
								<div id="facilities${k.index}">
								</div>
								<div class="calendar-wrap">
								</div>
								<!-- <input type="hidden" name="res_date" /> -->
								<div class="people-count">
									<strong>예약인원</strong>
									<button type="button" class="count-minus" >&lt;</button>
									<input class="count" type="number" name="res_people" value="${optionDto.po_min}" min="${optionDto.po_min}" max="${optionDto.po_max}" placeholder="${optionDto.po_min}"/>
									<button type="button" class="count-plus" >&gt;</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>		
			</c:if>
			<div class="btn-wrap">
				<button type="button" onclick="fn_modalPhone()"><span><i class="fas fa-mobile-alt"></i>전화</span></button>
				<button type="button" onclick="fn_reserve(this.form)">예약신청하기</button>
			</div>
		</form>
	</aside>
	<article>
		<div class="thumbnail-box">
			
		</div>
		<div class="place-line-desc2">
			${placeDto.p_desc}
		</div>
		<div class="place-nav">
			<ul>
				<li><a href="#place-main-info">공간소개</a></li>
				<li><a href="#place-info">시설안내</a></li>
				<li><a href="#place-remark">유의사항</a></li>
				<li><a href="#place-review">리뷰</a></li>
			</ul>
		</div>
		<div id="place-main-info" class="place-main-info">
			<h3>공간소개</h3>
			<p style="white-space:pre-line">
				${placeDto.p_content}
			</p>
		</div>
		<div id="place-info" class="place-info">
			<h3>시설안내</h3>
			<ol id="info-list">
			
			</ol>
		</div>
		<div id="place-map-wrap">
			<h3>위치</h3>
			<div id="map" style="width : 100%; height: 500px; overflow:hidden;">

			</div>
			<h4>${placeDto.p_addr}&nbsp;${placeDto.p_addrdetail}</h4>
			<a href="${placeDto.p_url}" target="_blank" >${placeDto.p_url}</a>
		</div>
		<div id="place-remark" class="place-remark">
			<h3>예약시 주의사항</h3>
			<!-- db에 저장된건 json을 string으로 변환한 데이터 -->
			<!-- Todo : 다시 js에서 json타입으로 변환후에 뿌려준다. -->
			<ol id="remark-list">
			
			</ol>
		</div>
		<div id="place-review" class="place-review">
			<h3>리뷰</h3>
			<div class="review-insert">
				<form id="review-insert-form" method="post" enctype="multipart/form-data" >
					<div class="review-insert-star">
						<p>
						<!-- js 함수로 만들어서 뿌려보자 시간되면 -->
							<input id="star1" class="star" type="checkbox" value="1" /><label for="star1"></label> 
							<input id="star2" class="star" type="checkbox" value="2" /><label for="star2"></label>
							<input id="star3" class="star" type="checkbox" value="3" /><label for="star3"></label>
							<input id="star4" class="star" type="checkbox" value="4" /><label for="star4"></label>
							<input id="star5" class="star" type="checkbox" value="5" /><label for="star5"></label>
						</p>
						<input id="star-score" type="hidden" name="rv_star" />
					</div>
					<!-- 해당 공간을 이용한 클럽의 회원만 댓글가능하도록 수정해야함 -->
					<c:if test="${isSeller}">
						<textarea rows="5" cols="50" name="rv_content" placeholder="호스트는 댓글을 달 수 없습니다." readonly></textarea>
					</c:if>
					<c:if test="${loginDto eq null}">
						<textarea rows="5" cols="50" name="rv_content" placeholder="로그인한 회원만 리뷰작성이 가능합니다." readonly></textarea>
					</c:if>
					<c:if test="${(not isSeller) && (loginDto ne null)}">
						<textarea rows="5" cols="50" name="rv_content" placeholder="댓글은 마음의 창입니다." ></textarea>
						<input id="file-upload-btn" type="file" name="rv_img" accept="image/*" value="이미지첨부" multiple />
					</c:if>
					<div class="review-insert-btn-wrap">
						<!-- hidden -->
						<c:if test="${not isSeller && loginDto ne null}">
							<input type="hidden" name="m_no" value="${loginDto.m_no}" />
						</c:if>
						<input type="hidden" name="p_no" value="${placeDto.p_no}" />
						<label id="upload-btn" for="file-upload-btn" >파일 업로드</label>
						<input type="button" value="작성하기" onclick="fn_reviewInsert(this.form)" />
						<input type="button" value="다시작성하기" onclick="fn_reviewReset()" />
					</div>
				</form>
			</div>
			<div class="review-list">
				<c:if test="${fn:length(reviewList) eq 0}">
					<div>
						<h2>등록된 후기가 아직 없습니다.</h2>
					</div>
				</c:if>
				<c:if test="${fn:length(reviewList) ne 0}">
				<!-- Todo : 리뷰 불러오기는 홀수번째, 짝수번째에 나눠서 다른 css적용 -->
				<!-- 일단은 모두 같은 css를 적용한다. -->
				<!-- even, odd라는 선택자 기억해 둘 것. -->
					<c:forEach var="reviewDto" items="${reviewList}" varStatus="k" >
						<form>
							<input type="hidden" name="rn" value="${reviewDto.rn}" />
							<div class="review">
								<div class="reviewer-info">
									<c:if test="${reviewDto.m_nick eq null}">
										<span class="review-id">ID : ${reviewDto.m_id}</span>
									</c:if>
									<c:if test="${reviewDto.m_nick ne null}">
										<span class="review-nick">Nick name : ${reviewDto.m_nick}</span>
										<br/>
										<span class="review-id">ID : ${reviewDto.m_id}</span>
									</c:if>
								</div>
								<div class="review-star">
									<c:forEach begin="1" end="5" step="1" varStatus="k" >
										<c:if test="${k.count le reviewDto.rv_star}">
											<i class="fas fa-star"></i>
										</c:if>
										<c:if test="${k.count gt reviewDto.rv_star}">
											<i class="far fa-star"></i>
										</c:if>
									 </c:forEach>
								</div>
								<div class="review-date">
									작성일 : <fmt:formatDate value="${reviewDto.rv_postDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									<c:if test="${(reviewDto.rv_modifyDate ne reviewDto.rv_postDate) && (reviewDto.rv_modifyDate ne null)}">
										<br/>최근수정일 : <fmt:formatDate value="${reviewDto.rv_modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</c:if>
								</div>
								<div class="review-content" >
									<p>${reviewDto.rv_content}</p>
								</div>
								<div class="review-img-box" >
										
								</div>
								<div class="review-btns" >
									<c:if test="${(not isSeller) && (loginDto.m_no eq reviewDto.m_no)}">
										<!-- modal에 표시할 별점 -->
										<input type="hidden" name="rv_star" value="${reviewDto.rv_star}" />
										<!-- modal에 표시할 내용 -->
										<input type="hidden" name="rv_content" value="${reviewDto.rv_content}" />
										<input type="hidden" name="rv_no" value="${reviewDto.rv_no}" />
										<input type="button" value="수정하기" onclick="fn_modal(this.form)" />
										<input type="button" value="삭제하기" onclick="fn_reviewDelete(this.form)" />
									</c:if>
								</div>
							</div>
						</form>
					</c:forEach>
					<h2 id="more">리뷰 더보기 Scroll Down!</h2>
				</c:if>
			</div>
		</div>
	</article>
</section>

<%@ include file="../template/footer.jsp" %>