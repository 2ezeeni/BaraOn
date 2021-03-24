<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<jsp:include page="../template/header.jsp">
	<jsp:param value="새 클럽 생성" name="title" />
</jsp:include>
<link rel="stylesheet" href="resources/style/common.css" />    
<link rel="stylesheet" href="resources/style/club/clubInsert.css" />    
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
    

	<form method="post" enctype="multipart/form-data">
		<div class="insert-wrap">
			<hr class="line">
			<div id="main_image">
				<div class="title">메인 화면 썸네일</div>
				<input id="main-img" type="file" name="c_mainImg" />
			</div>
		
			<br/><br/>
		
			<div class="inputs">
				<div class="title">클럽명</div>
					<input type="text" name="c_title" id="input" placeholder="  제목을 입력해주세요" /><br/><br/>
					
				<div class="title">한 줄 설명</div>
					<input type="text" name="c_desc" id="input" placeholder="  한 줄 설명을 입력해주세요" /><br/><br/>
					
				<div class="title">최소인원</div>
					<input type="text" name="c_min" id="input" placeholder="  최소인원을 입력해주세요" /><br/><br/>
					
				<div class="title">최대인원</div>
					<input type="text" name="c_max" id="input" placeholder="  최대인원을 입력해주세요" /><br/><br/>
					
				<div class="title">활동날짜</div>
					<input type="text" name="c_startDate" id="input" placeholder="  ex) 20xx.xx.xx" /><br/><br/>
				
				<input type="hidden" name="c_endDate" />
					
				<div class="title">클럽내용</div>
					<textarea rows="20" cols="100" id="c_content" name="c_content" placeholder=" 2000자 이내로 작성해주세요" ></textarea>
				<br/><br/>
			</div>
		</div>
		<%-- hidden --%>
		<input type="hidden" name="m_no" value="${loginDto.m_no}" />
		
		<div class="btn_wrap">
			<input type="button" name="btn" id="btn" value="완료하기" onclick="fn_finish(this.form)" />
			<input type="button" name="btn" id="btn" value="돌아가기" onclick="location.href='instanceClubListPage.club'" />
		</div>
	</form>
	
	<script type="text/javascript">
	
		function fn_finish(f) {
			let isPossible=false;
			if (confirm('클럽을 등록하겠습니까?')) {
				let chkInputList = $('input:not([type="hidden"])');	// hidden타입을 제외한 input 배열 생성
				
				$.each(chkInputList, function(idx, input){			// input들을 순회하면서 validate
					if($(input).val()==null||$(input).val()==''){
						alert($(input).prev().text()+'을[를] 입력해주세요');
						$(input).css('outline','red').css('box-shadow', '1px 1px 0px 0px red, -1px -1px 0px 0px red');
						$(input).focus();							// 입력되지 않은 input으로 focus
						isPossible=false;
						return false;
					} else {
						$(input).css('outline', 'blue').css('box-shadow', '1px 1px 0px 0px blue, -1px -1px 0px 0px blue');
						isPossible=true;
					}
				});
			}
			if (!isPossible){
				return;
			} else {
				f.action='instanceClubInsert.club';
				f.submit();
			}
		}
	
		// 확장자 체크 & 크기 체크
		(()=>{
			$('#main-img').on('change', function(event){
				if($(event.target) != null) {
					if(!fn_fileChk($(event.target))||!fn_sizeChk($(event.target))) {
						$(event.target).val('');
						return;
					}
				}
			});
		})();
		
		// 확장자 체크
		function fn_fileChk(obj, ext){
			let chk = false;
			let extName=obj.val().substring(obj.val().lastIndexOf(".") + 1).toUpperCase();
			let str = 'PNG,JPG,JPEG'.split(","); // 허용 확장자 배열
			$.each(str, function(idx, ext){
				if(ext.trim()===extName){
					chk = true;
					return false;
				} else {
					chk = false;
				}
			});
			if(!chk){
				alert('이미지파일만 가능합니다.(JPG, JPEG, PNG)');
				obj.focus();
			}
			
			return chk;
		}
		
		// 사이즈 체크
		function fn_sizeChk(obj) { 
			let chk = false;
			let sizeinbytes = obj[0].files[0].size;
			let fSExt = new Array('Bytes', 'KB', 'MB', 'GB');
			let i = 0;
			const checkSize = 10*1024*1024;		// 사이즈는 10MB
			while (checkSize > 900) { 
				checkSize /= 1024; i++; 
			} 
			checkSize = (Math.round(checkSize * 100) / 100) + ' ' + fSExt[i];
			var fSize = sizeinbytes;
			if (fSize > size) {
				alert("첨부파일은 " + checkSize + " 이하로 첨부 바랍니다.");
				obj.fucus();
				chk = false; 
			} else {
				chk = true; 
			} 
			return chk; 
		}
		
	</script>

<%@ include file="../template/footer.jsp"%>