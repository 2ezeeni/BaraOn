<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../template/header.jsp" >
	<jsp:param value="BaraON :: 예약" name="title"/>
</jsp:include>

<script type="text/javascript" src="resources/js/reserve.js" ></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link rel="stylesheet" href="resources/style/reservation/reservation-insert.css" />
<script>
	let remarkList = JSON.parse('${placeDto.p_remark}');
	let facilityList = JSON.parse('${placeOptionDto.po_fxility}');
	let placeName='${placeOptionDto.po_name}';
	let resNo='${reservationDto.res_no}';
	let isProgress=false;
	let isPossible=false;
</script>

<div>
	<form method="post" id="res-form">
	<section class="left">
		<article class="res-insert-list">
			<div class="subtitle">예약 공간</div>
			<div class="sub-content">
				<div class="place-detail">
					<h4>${placeDto.p_title}</h4>
					<span>${placeDto.p_desc}</span>
					<div class="option-img-box">
						<img alt="${placeOptionDto.po_name}" src="resources/images/PlaceOptionImages/${placeOptionDto.po_img}" />
					</div>
				</div>
				<ul class="option-detail">
					<li>
						<span>카테고리</span>
						<span># ${category}</span>
					</li>
					<li>
						<span>예약인원</span>
						<span>최소 ${placeOptionDto.po_min}명 ~ 최대 ${placeOptionDto.po_max}명</span>
					</li>
				</ul>
				<div id="facility-list">
					
				</div>
			</div>
		</article>
		<article class="res-insert-list">
			<div class="subtitle">예약자 정보</div>
			<div class="sub-content">
				<ul>
					<li>
						예약자<span class="required-data">필수사항</span><br/>
						<input type="text" name="res_name" value="${memberDto.m_name}" />
					</li>
					<li>
						연락처<span class="required-data">필수사항</span><br/>
						<input type="text" name="res_phone" value="${memberDto.m_phone}" />
					</li>
					<li>
						이메일<br/>
						<input type="text" name="res_email" value="${memberDto.m_email}" />
					</li>
					<li>
						사용목적<span class="required-data">필수사항</span><br/>
						<input type="text" name="res_purpose" />
					</li>
					<li>
						요구사항<br/>
						<textarea rows="8" cols="50" name="requirement" style="resize: none;" ></textarea>
					</li>
				</ul>
			</div>
		</article>
		<article class="res-insert-list">
			<div class="subtitle">호스트 정보</div>
			<div class="sub-content">
				<table>
					<tbody>
						<tr>
							<th>상호</th>
							<td>${placeDto.p_name}</td>
						</tr>
						<tr>
							<th>대표자명</th>
							<td>${sellerDto.s_name}</td>
						</tr>
						<tr>
							<th>사업장 위치</th>
							<td>${placeDto.p_addr}&nbsp;${placeDto.p_addrdetail}</td>
						</tr>
						<tr>
							<th>사업자 번호</th>
							<td>${sellerDto.s_companyNo}</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td>${sellerDto.s_phone}</td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>${sellerDto.s_email}</td>
						</tr>
					</tbody>
				</table>
				
			</div>
		</article>
		<article class="res-insert-list">
			<div class="subtitle">예약시 주의사항</div>
			<div class="sub-content">
				<ol id="remark-list">
					
				</ol>
			</div>
		</article>
		<article class="res-insert-list">
			<div class="subtitle">서비스 동의<span class="required-data" >필수 사항</span></div>
			<div class="sub-content">
				<ul>
					<li>
						<input id="terms-all" type="checkbox" />
						<label for="terms-all">전체 동의</label>
					</li> 
					<li>
						<input class="terms" id="terms1" type="checkbox" />
						<label for="terms1">위 공간의 예약조건 확인 및 결제진행 동의
							<span class="required-data">필수</span>
						</label>
					</li> 
					<li>
						<input class="terms" id="terms2" type="checkbox" />
						<label for="terms2">환불규정 안내에 대한 동의
							<span class="required-data">필수</span>
						</label>
					</li> 
					<li>
						<div class="confirm-box">
							<input class="terms" id="terms3" type="checkbox" />
								<label for="terms3">개인정보 제3자 제공동의
								<span class="required-data">필수</span>
							</label>
							<button class="more-btn" type="button" >▼</button>
							<a></a>
						</div>
						<div class="scroll-box">
							<ol>
								<li>개인정보를 제공받는 자: 해당 공간의 호스트</li>
								<li>
									제공하는 개인정보 항목
									<ul>
										<li>- 필수항목: 네이버 아이디, 이름, 연락처, 결제정보(결제방식 및 결제금액)</li>
										<li>- 선택항목: 이메일 주소</li>
									</ul>
								</li>
								<li>개인정보의 제공목적: 공간예약 및 이용 서비스 제공, 환불처리</li>
								<li>개인정보의 제공기간: 서비스 제공기간(단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우 및 사전 동의를 득한 경우에는 해당 기간 동안 보관합니다.)</li>
								<li>개인정보의 제공을 거부할 권리: 개인정보 주체는 개인정보의 제공을 거부할 권리가 있으나, 공간 예약을 위해 반드시 필요한 개인정보의 제공으로서 이를 거부할 시 공간 예약이 어려울 수 있습니다.</li>
							</ol>
						</div>
					</li>
					<li>
						<div class="confirm-box">
							<input class="terms" id="terms4" type="checkbox" />
								<label for="terms4">개인정보 수집 및 이용동의
								<span class="required-data">필수</span>
							</label>
							<button class="more-btn" type="button" >▼</button>
						</div>
						<div class="scroll-box">
							<ol>
								<li>
									수집하는 개인정보의 항목
									<ul>
										<li>- 예약정보(성명, 이메일주소, 휴대전화번호), 결제정보(신용카드 번호 및 은행계좌정보 일부 등)</li>
									</ul>
								</li>
								<li>
									개인정보의 이용목적
									<ul>
										<li>- 공간 예약 및 이용</li>
									</ul>
								</li>
								<li>
									개인정보의 보관기간
									<ul>
										<li>- 예약 완료 후 관련 법령에 따라 5년간 개인정보를 보관합니다.</li>
									</ul>
								</li>
								<li>
									개인정보의 수집 및 이용을 거부할 권리
									<ul>
										<li>- 개인정보 주체는 개인정보의 수집 및 이용을 거부할 권리가 있으나, 공간 예약을 위한 최소한의 개인정보 수집으로서 이를 거부 할 시 공간 예약이 어려울 수 있습니다.</li>
									</ul>
								</li>
							</ol>
						</div>
					</li>
				</ul>
			</div>
		</article>
	</section>
	<section class="right">
		<aside class="res-insert-list">
			<div class="subtitle">결제 예정금액</div>
			<div class="sub-content">
				<strong>예약날짜</strong><br/>
				<span class="res-data">${reservationDto.res_date}</span><br/>
				<strong>예약인원</strong><br/>
				<span class="res-data">${reservationDto.res_people}</span><br/>
				<div class="res-data" id="res-price">
					<c:if test="${isWeekend}">
						<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${placeOptionDto.po_holiday}" /> 
						<input type="hidden" name="res_price" value="${placeOptionDto.po_holiday}" />
					</c:if>
					<c:if test="${not isWeekend}">
						<fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${placeOptionDto.po_dayPrice}" />
						<input type="hidden" name="res_price" value="${placeOptionDto.po_dayPrice}" />
					</c:if>
				</div>
			</div>
			<div class="btn-box">
				<button id="res-update" type="button">결제하기</button>
			</div>
		</aside>
	</section>
	</form>
</div>
<%@ include file="../template/footer.jsp" %>