<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" />
<link rel="stylesheet" href="resources/style/member/memberJoin3.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script>
	
	$(function(){
		fn_idCheck();
		fn_pwCheck();
		fn_pwCheck2();
		fn_nickCheck();
		fn_nameCheck();
		fn_birthCheck();
		fn_phoneCheck();
		fn_emailCheck();
		fn_emailAuth1();
		fn_emailAuth2();
		agreeCheck();
	});
	
	var check1;
	var check2;
	var check3;
	var check4;
	var check5;
	var check6;
	var check7;
	var check8;
	var check9;
	function fn_idCheck(){
		
		$('#m_id').blur(function(){
			
			var m_id = $('#m_id').val();
			
			$.ajax({
				url:'memberSearch.member?m_id='+m_id,
				type:'post',
				dataType:'json',
				success: function(data){
					var regExpID = /[a-zA-Z0-9]{6,16}$/;
					var result = document.getElementById('result');
					if (data.resultMap == 1) {
						result.textContent ='이미 등록되어있는 아이디 입니다.';
						result.setAttribute('class', 'check-m_id-result-not2');
						check1=false;
						return false;
					} else {
						if(regExpID.test(m_id)){
							result.textContent ='사용가능한 아이디입니다.';
							result.setAttribute('class', 'check-m_id-result-ok');
							check1=true;
							return true;
						}else{
							result.textContent = '사용이 불가능한 아이디입니다.';
							result.setAttribute('class', 'check-m_id-result-not');
							check1=false;
							return false;
						}
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}
	
	function fn_pwCheck(){
	
		$('#m_pw').blur(function(){
			var m_pw = $('#m_pw').val();
			var regExpPW = /[a-zA-Z0-9]{6,16}$/;
			var result2 = document.getElementById('result2');
			if(regExpPW.test(m_pw)){
				result2.textContent ='사용가능한 비밀번호입니다.';
				result2.setAttribute ('class', 'check-m_pw1-result-ok');
				check2=true;
				return true;
			}else{
				result2.textContent = '사용 불가능한 비밀번호입니다.';
				result2.setAttribute ('class', 'check-m_pw1-result-not');
				check2=false;
				return false;
			}
		});
	}
	
	function fn_pwCheck2(){
		$('#m_pw2').blur(function(){
			var m_pw = $('#m_pw').val();
			var m_pw2 = $('#m_pw2').val();
			var result3 = document.getElementById('result3');
			if(m_pw == m_pw2){
				result3.textContent ='비밀번호가 일치합니다.';
				result3.setAttribute ('class', 'check-m_pw2-result-ok');
				check3=true;
				return true;
			}else{
				result3.textContent = '비밀번호가 일치하지 않습니다.';
				result3.setAttribute ('class', 'check-m_pw2-result-not');
				check3=false;
				return false;
			}
		});
	}
	
	function fn_nickCheck(){
		$('#m_nick').blur(function(){
			var m_nick=$('#m_nick').val();
			$.ajax({
				url:'memberNickSearch.member?m_nick='+m_nick,
				type:'post',
				dataType:'json',
				success: function(data){
					var regExpNick = /[a-zA-Z0-9가-힣]{1,14}$/;
					var result4 = document.getElementById('result4');
					if (data.result == 1) {
						result4.textContent ='이미 등록되어있는 닉네임 입니다.';
						result4.setAttribute('class', 'check-m_nick-result-not2');
						check4=false;
						return false;
					}else{
						if(regExpNick.test(m_nick)){
						result4.textContent ='사용가능한 닉네임입니다.';
						result4.setAttribute ('class', 'check-m_nick-result-ok');
						check4=true;
						return true;
						}else{
						result4.textContent = '사용 불가능한 닉네임입니다.';
						result4.setAttribute ('class', 'check-m_nick-result-not');
						check4=false;
						return false;
						}
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}
	
	function fn_nameCheck(){
		
		$('#m_name').blur(function(){
			var m_name = $('#m_name').val();
			var regExpNAME = /[가-힣]{1,16}$/;
			var result5 = document.getElementById('result5');
			if(regExpNAME.test(m_name)){
				result5.textContent ='정상적인 이름입니다.';
				result5.setAttribute ('class', 'check-m_name-result-ok');
				check5=true;
				return true;
			}else{
				result5.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
				result5.setAttribute ('class', 'check-m_name-result-not');
				check5=false;
				return false;
			}
		});
	}
	
	function fn_birthCheck(){
		
		$('#m_birth').blur(function(){
			var m_birth = $('#m_birth').val();
			var regExpYEAR = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
			var result6 = document.getElementById('result6');
			if(regExpYEAR.test(m_birth)){
				result6.textContent = '가능한 날짜입니다';
				result6.setAttribute ('class', 'check-m_birth-result-not');
				check6=true;
				return true;
			}else{
				result6.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
				result6.setAttribute ('class', 'check-m_birth-result-not');
				check6=false;
				return false;
			}
		});
	}
	
	function fn_phoneCheck(){
		$('#m_phone').blur(function(){
			var m_phone=$('#m_phone').val();
			$.ajax({
				url:'memberPhoneSearch.member?m_phone='+m_phone,
				type:'post',
				dataType:'json',
				success: function(data){
					var regExpPHONE = /^\d{3}-\d{3,4}-\d{4}$/;
					var result7 = document.getElementById('result7');
					if (data.result == 1) {
						result7.textContent ='이미 등록되어있는 연락처 입니다.';
						result7.setAttribute('class', 'check-m_phone-result-not2');
						check7=false;
						return false;
					}else{
						if (regExpPHONE.test(m_phone)) {
							result7.textContent ='사용 가능한 번호입니다.';
							result7.setAttribute('class', 'check-m_phone-result-not');
							check7=true;
							return true;
						}else{
							result7.textContent ='정확하게 입력하셨는지 다시한번 확인해주세요.';
							result7.setAttribute ('class', 'check-m_phone-result-ok');
							check7=false;
							return false;
						}
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}
	
	function fn_emailCheck(){
		$('#m_email').blur(function(){
			var m_email=$('#m_email').val();
			$.ajax({
				url:'memberEmailSearch.member?m_email='+m_email,
				type:'post',
				dataType:'json',
				success: function(data){
					var regExpEMAIL = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
					var result8 = document.getElementById('result8');
					if (data.result == 1) {
						result8.textContent ='이미 등록되어있는 이메일 입니다.';
						result8.setAttribute('class', 'check-m_email-result-not2');
						return false;
					}else{
						if(regExpEMAIL.test(m_email)){
							result8.textContent = '사용가능한 이메일입니다';
							result8.setAttribute ('class', 'check-m_email-result-not');
							return true;
						}else{
							result8.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
							result8.setAttribute ('class', 'check-m_email-result-not');
							return false;
						}
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}
			
	var authKey;
	function fn_emailAuth1(){
		$('#emailAuth1').click(function(){
			var result8 = document.getElementById('result8');
			var m_email=$('#m_email').val();
			var Obj = {
					'm_email':m_email
				};
			$.ajax({
				url:'emailAuth.member',
				type:'post',
				data: JSON.stringify(Obj),
				contentType:'application/json',
				dataType:'json',
				success: function(data){
					if(authKey=data.resultMap){
						result8.textContent = '인증메일이 발송되었습니다';
						check8=true;
						return true;
					}else{
						result8.textContent = '발송에 실패하였습니다';
						check8=false;
						return false;
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}
	
	function fn_emailAuth2(){
		$('#emailAuth2').click(function(){
			var m_email2 = $('#m_email2').val();
			var result9 = document.getElementById('result9');
			if(m_email2==authKey){
				result9.textContent = '인증이 완료되었습니다';
				result9.setAttribute ('class', 'check-m_email2-result-not');
				check9=true;
				return true;
			}else{
				result9.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
				result9.setAttribute('class', 'check-m_email2-result-not');
				check9=false;
				return false;
			}
		});
	}
	
	function agreeCheck(){
		console.log('${chk_email}');
		var agree='${chk_email}';	
		var m_agreement;
		if(agree==1){
			$('#m_agreement').val(1);
		}else{
			$('#m_agreement').val(0);
		}
	}
	
	function fn_memberInsert(f){
		if(check1==false ||
			check2==false ||
			check3==false ||
			check4==false ||
			check5==false ||
			check6==false ||
			check7==false ||
			check8==false ||
			check9==false ||
			$('#m_id').val()=='' || 
			$('#m_pw').val()=='' || 
			$('#m_pw2').val()=='' ||
			$('#m_nick').val()=='' ||
			$('#m_name').val()=='' ||
			$('#m_birth').val()=='' ||
			$('#m_phone').val()=='' ||
			$('#m_email').val()=='' ||
			$('#m_email2').val()==''){
				$('#errorMsg').show();
		}else{
			$('#errorMsg').hide();
			f.action='memberInsert.member';
			f.submit();
		}
	}	
	
</script>
	<br/><br/><br/>
	<div class="box">
		<form method="post">
			<label for="m_id">아이디</label><br/>
			
			<c:if test="${kakaoId ne null}">
				<input type="text" name="m_id" id="m_id" value="${kakaoId}" readonly /><br/>
				<br/>
			</c:if>
			<c:if test="${kakaoId eq null}">
				<input type="text" name="m_id" id="m_id" /><br/>
			</c:if>
			
			<div id="result" class="check-m_id-result" ></div>
			<br/>
		
			<label for="m_pw">비밀번호</label><br/>
			<input type="password" name="m_pw" id="m_pw"/><br/>
			<div id="result2" class="check-m_pw1-result" ></div>
			<br/>
		
			<label for="m_pw2">비밀번호 확인</label><br/>
			<input type="password" name="m_pw2" id="m_pw2" /><br/>
			<div id="result3" class="check-m_pw2-result" ></div>
			<br/>
		
			<label for="m_nick">닉네임</label><br/>
			<input type="text" name="m_nick" id="m_nick" /><br/>
			<div id="result4" class="check-m_nick-result" ></div>
			<br/>
			
			<label for="m_name">이름</label><br/>
			<input type="text" name="m_name" id="m_name" /><br/>
			<div id="result5" class="check-m_name-result"></div>
			<br/>
			
			<label for="m_birth">생년월일</label><br/>
			<input type="text" name="m_birth" id="m_birth" placeholder="예)19990720"/><br/>
			<div id="result6" class="check-m_birth-result"></div>
			<br/>
			
			<label for="m_phone">연락처</label><br/>
			<input type="text" name="m_phone" id="m_phone" placeholder="예)010-1111-2222"/><br/>
			<div id="result7" class="check-m_phone-result"></div>
			<br/>
			
			<label for="m_email">이메일</label><br/>
			
			<c:if test="${kakaoEmail ne null}">
				<input type="text" name="m_email" id="m_email" value="${kakaoEmail}" readonly/>
			</c:if>
			<c:if test="${kakaoEmail eq null}">
				<input type="text" name="m_email" id="m_email" />
			</c:if>
			
			<input type="button" value="인증번호받기" id="emailAuth1" name="emailAuth1"/><br/>
			<div id="result8" class="check-m_email-result"></div>
			<br/>
			<input type="text" name="m_email2" id="m_email2" placeholder="인증번호를 입력하세요"/><br/><br/>
			<input type="button" value="인증완료" name="emailAuth2" id="emailAuth2"/><br/>
			<div id="result9" class="check-m_email2-result"></div>
			<br/>
			
			<input type="hidden" id="m_agreement"/>		
			<input type="button" id="btn" value="가입하기" onclick="fn_memberInsert(this.form)">
			<br/>
			<div id="errorMsg"  class="result" style="display:none;">회원가입에 대한 정보를 작성해주세요</div>
		
		</form>
	</div>
	
	
	
<%@ include file="../template/footer.jsp" %>