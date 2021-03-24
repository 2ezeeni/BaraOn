<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../template/header.jsp">
	<jsp:param value="클럽 내용 상세 보기" name="title" />
</jsp:include>

<link rel="stylesheet" href="resources/style/common.css" />
<link rel="stylesheet" href="resources/style/club/clubView.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script type="text/javascript">
		function fn_delete(f) {
			if (confirm('정말 클럽을 해제하시겠습니까?')) {
				f.action='clubDelete.club';
				f.submit();
			}
		}
		
		function fn_update(f) {
			if (confirm('클럽 정보를 수정하시겠습니까?')) {
				f.action='clubUpdatePage.club';
				f.submit();
			}
		}
		
		function fn_joinClub(f) {
			if (confirm('해당 클럽을 참여하시겠습니까?')) {
				f.action = 'joinClub.club';
				f.submit();
			}
		}
		
		function fn_chkOut(f) {
			if (confirm('해당 클럽을 탈퇴하시겠습니까?')) {
				f.action = 'chkOutClub.club';
				f.submit();
			}
		}
		
</script>

 <script>
 	var afterUpdate = ${afterUpdate};
	if (afterUpdate) {
		var afterUpdate = ${afterUpdate};
		if (afterUpdate > 0) {
			alert('수정되었습니다.');
		} else {
			alert('수정을 실패했습니다.');
		}
	} 
	
	var afterJoin = ${afterJoin};
	if (afterJoin) {
		var joinResult = ${joinResult};
		if (joinResult > 0) {
			alert('클럽에 가입되었습니다.');
		} else {
			alert('클럽에 가입에 성공했습니다');
		}
	}
	
	var afterChkOut = ${afterChkOut};
	if (afterChkOut) {
		var chkOutResult = ${chkOutResult};
		if (chkOutResult > 0) {
			alert('클럽을 탈퇴했습니다.');
		} else {
			alert('클럽 탈퇴에 실패했습니다.');
		}
	} 
	
</script>
<hr class="line">
	<div class="body_wrap">
		<div class="left_side">
			<div class="thumnail">
				<img class="img" alt="${clubDto.c_mainImg}" src="resources/images/club/${clubDto.c_mainImg}">
					<c:if test="${loginDto ne null}">
						<c:if test="${clubDto.m_no ne loginDto.m_no}">
						<form method="post" action="likeClub.club">
							<input type="hidden" name="c_no" value="${clubDto.c_no}" />
							<input type="hidden" name="m_no" value="${loginDto.m_no}" />
							<c:if test="${wishListDto.m_no ne loginDto.m_no}">
								<button id="wish_btn"><div class="wishIcon"><i class="fa fa-heart" style="color:gray"></i></div></button>
							</c:if>
						</form>
						
						<form method="post" action="unLikeClub.club">
							<c:if test="${wishListDto.m_no eq loginDto.m_no}">
								<input type="hidden" name="w_no" value="${wishListDto.w_no}" />
								<button id="wish_btn"><div class="wishIcon"><i class="fa fa-heart" style="color:cadetblue"></i></div></button>
							</c:if>
						</form>
						</c:if>
					</c:if>
			</div>
			
			<div class="contents">
				<div id="title">클럽명</div> 
				<div id="content">${clubDto.c_title}</div>
			</div>

			<div class="contents">
				<div id="title">한 줄 설명</div> 
				<div id="content">${clubDto.c_desc}</div>
			</div>
	
			<div class="contents">
				<div id="title">리더 소개</div> 
				<div id="content">${memberDto.m_nick}</div>
			</div>

			<div class="contents">
				<div id="title">클럽내용</div> 
				<div id="content">${clubDto.c_content}</div>
			</div>
			
			<div class="contents">
				<div id="title">활동시작일</div> 
				<div id="content">${clubDto.c_startDate}</div>
			</div>
			
			<div class="contents">
				<div id="title">활동종료일</div>
				<div id="content">${clubDto.c_endDate}</div>
			</div>
			
			<div class="contents">
				<div id="title">등록일</div>
				<div id="content">${clubDto.c_postDate}</div>
			</div>
			
		</div>
		
		<c:if test="${clubDto.m_no ne loginDto.m_no}">
			<div class="right_box">
				<div class="right_content">
					<div class="side_content">
						<div id="title">${clubDto.c_title}</div>
					</div>
					<br/><br/>
					<hr class="line">
					<div class="side_content">
						<div id="title">활동 시작일 : </div>
						<div id="date">${clubDto.c_startDate}</div>
					</div>
					<br/>
					<div class="side_content">
						<div id="title">활동 종료일 : </div>
						<div id="date">${clubDto.c_endDate}</div>
					</div>
					<br/><br/>
					
					<c:if test="${loginDto ne null}">
						<div class="side_content">
							<div class="box_content">
								<form method="post">
									<input type="hidden" name="c_no" value="${clubDto.c_no}" />
									<input type="hidden" name="m_no" value="${loginDto.m_no}" />
									
									<c:if test="${clubListDto.m_no ne loginDto.m_no}">
										<input type="button" value="참여하기" id="btn1" onclick="fn_joinClub(this.form)"/>
									</c:if>
									<c:if test="${clubListDto.m_no eq loginDto.m_no}">
										<input type="hidden" name="cl_no" value="${clubListDto.cl_no}" />
										<input type="button" value="탈퇴하기" id="btn1" onclick="fn_chkOut(this.form)"/>
									</c:if>
								</form>
								
								<input type="button" value="목록으로 돌아가기" id="btn1" onclick="location.href='clubListPage.club'" />
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</c:if>
		
		
		<c:if test="${clubDto.m_no eq loginDto.m_no}">
			<div class="right_box">
				<div class="right_content">
					<div class="side_content">
						<div id="content">${clubDto.c_title}</div>
					</div>
					<br/><br/>
					<hr class="line">
					<div class="side_content">
						<div id="title">활동 시작일 : </div><br/>
						<div id="date">${clubDto.c_startDate}</div>
					</div>
					<br/>
					<div class="side_content">
						<div id="title">활동 종료일 : </div><br/>
						<div id="date">${clubDto.c_endDate}</div>
					</div>
					<br/><br/>
					<form method="post">
						<%-- hidden --%>
						<input type="hidden" name="c_no" value="${clubDto.c_no}" />
						<input type="hidden" name="m_no" value="${loginDto.m_no}" />
						<input type="hidden" name="c_mainImg" value="${clubDto.c_mainImg}" />
						<input type="hidden" name="c_title" value="${clubDto.c_title}" />
						<input type="hidden" name="c_desc" value="${clubDto.c_desc}" />
						<input type="hidden" name="c_min" value="${clubDto.c_min}" />
						<input type="hidden" name="c_max" value="${clubDto.c_max}" />
						<input type="hidden" name="c_startDate" value="${clubDto.c_startDate}" />
						<input type="hidden" name="c_endDate" value="${clubDto.c_endDate}" />
						<input type="hidden" name="c_content" value="${clubDto.c_content}" />
						
						<div class="side_content">
							<input type="button" value="장소등록하기" id="btn1" onclick="location.href='placeListPage.place'" /><br/> 
							<input type="button" value="클럽수정하기" id="btn1" onclick="fn_update(this.form)" /> <br/>
							<input type="button" value="클럽해제하기" id="btn1" onclick="fn_delete(this.form)" />	<br/>
							<input type="button" value="목록으로 돌아가기" id="btn1" onclick="location.href='clubListPage.club'" /> <br/>
						</div>
					</form>
				</div>
			</div>
		</c:if>
	</div>

<%@ include file="../template/footer.jsp"%>
