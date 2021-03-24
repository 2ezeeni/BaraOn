<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp">
	<jsp:param value="BaraON::FAQ" name="title"/>
</jsp:include>
<link rel="stylesheet" href="resources/style/common.css">
<style type="text/css">
	#bottom-head div:nth-of-type(4){
		border-bottom: 2px solid rgba(95, 158, 160, 1);
	}
	#bottom-head div:nth-of-type(4)>a{
		color: rgba(95, 158, 160, 1);
	}
	.categoryList >	a:link { color: black; text-decoration: none;}
 	.categoryList > a:visited { color: black; text-decoration: none;}
	.categoryList >	a:hover { color: blue; text-decoration: underline;}

	.list > a:link { color: black; text-decoration: none;}
	.list >  a:visited { color: black; text-decoration: none;}
	.list >  a:hover { color: blue; text-decoration: underline;}
	
	 #wrap{
	 	height: 300px;
	 	margin-bottom: 20px;
	 }
	 
	 body{
	 	background: #F8EBEE;
	 }
	 .disable{
	 	margin:0 10px 0 10px;
	 }
	 #paging > a{
	 	margin: 0 10px 0 10px;
	 }
	 .categoryList > a{
	 	margin:20px;
	 	font-weight: bold;
	 	font-size: 20px;
	 }
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script type="text/javascript">
/* 
	$(document).ready(function(){
		fn_allList();	
		fn_payList();
		fn_accountList();
		fn_usingList();
	});
function fn_allList(){
	$('#allBtn').click(function(){
		location.href = 'faqListPage.faq';
	});
}
function fn_payList() {
	$('#payBtn').click(function(){
		location.href = 'faqListPage.faq?f_category=2' ;  // location은 get 방식의 요청이고, 별도 파라미터를 가지고 가지 않기 때문에 ?로 직접 파라미터를 추가해서 보내줘야 합니다. 컨트롤러는 request에 f_category가 저장되어 있다고 보면 됩니다. 
	});
}
function fn_accountList(){
	$('#accountBtn').click(function(){
		location.href = 'faqListPage.faq?f_category=3'; 
	});
}
function fn_usingList(){
	$('#usingBtn').click(function(){
		location.href = 'faqListPage.faq?f_category=4'; 
	});
}
*/
</script>
			<div id="wrap" style="width :1020px; height: 500px; margin: 0 auto;">
				<h1>자주묻는질문(FAQ)</h1><br/>
					<div class="categoryList" style="width: 1020px; text-align: center; margin:20px;">
						<a href="faqListPage.faq?">전체</a>
						<a href="faqListPage.faq?f_category=2">결제 및 환불</a>
						<a href="faqListPage.faq?f_category=3">계정</a>
						<a href="faqListPage.faq?f_category=4">이용안내</a>
					</div><br/>			
				<!--  
				<form style="text-align: center">
					<input type="button" id="allBtn" value="전체">
					<input type="button" id="payBtn" value="결제 및 환불">
					<input type="button" id="accountBtn" value="계정">
					<input type="button" id="usingBtn" value="이용안내">
				</form><br/>
				-->
				<section>
					<div>
						<div style="width:620px; margin: 0 auto; height:50px; line-height:50px;
						float: left; text-align: center; 
						font-size: 24px; background:cadetblue; border-top: 1px solid black; border-bottom: 1px solid black">
								제목
						</div>
						<div style="width:400px; margin: 0 auto; height:50px; line-height:50px;
						float: left; text-align: center;  
						font-size: 24px; background:cadetblue; border-top: 1px solid black; border-bottom: 1px solid black">
								작성일
						</div>
					</div>
				</section>
				<section class="content">
				<c:if test="${empty list}"></c:if>
				<c:if test="${not empty list}">
					<c:forEach var="faqDto" items="${list}" varStatus="k">
							<div class="list" style="width:620px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px">
								<a href="faqViewPage.faq?no=${faqDto.f_no}&page=${page}">${faqDto.f_title}</a>
							</div>
							<div style="width:400px; margin: 0 auto; float: left; text-align: center; border-bottom: 1px solid black; height:40px; line-height:40px;">
								${faqDto.f_regDate}
							</div>
					</c:forEach>
				</c:if>
					<div id="paging" style="width:1020px; margin: 50px auto; float: left; text-align: center; font-weight: 800px; font-size: 20px">
						${paging}
					</div>
				</section>		
			</div>
<%@ include file="../template/footer.jsp" %>