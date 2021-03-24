<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../template/header.jsp" />
<link rel="stylesheet" href="resources/style/seller/sellerJoin2.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

<script>
	
	$(function(){
		fn_idCheck();
		fn_pwCheck();
		fn_pwCheck2();
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
		
		$('#s_id').blur(function(){
			
			var s_id = $('#s_id').val();
			
			$.ajax({
				url:'sellerSearch.seller?s_id='+s_id,
				type:'post',
				dataType:'json',
				success: function(data){
					var regExpID = /[a-zA-Z0-9]{6,16}$/;
					var result = document.getElementById('result');
					if (data.resultMap == 1) {
						result.textContent ='이미 등록되어있는 아이디 입니다.';
						result.setAttribute('class', 'check-s_id-result-not2');
						check1=false;
						return false;
					} else {
						if(regExpID.test(s_id)){
							result.textContent ='사용가능한 아이디입니다.';
							result.setAttribute('class', 'check-s_id-result-ok');
							check1=true;
							return true;
						}else{
							result.textContent = '사용이 불가능한 아이디입니다.';
							result.setAttribute('class', 'check-s_id-result-not');
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
	
		$('#s_pw').blur(function(){
			var s_pw = $('#s_pw').val();
			var regExpPW = /[a-zA-Z0-9]{6,16}$/;
			var result2 = document.getElementById('result2');
			if(regExpPW.test(s_pw)){
				result2.textContent ='사용가능한 비밀번호입니다.';
				result2.setAttribute ('class', 'check-s_pw1-result-ok');
				check2=true;
				return true;
			}else{
				result2.textContent = '사용 불가능한 비밀번호입니다.';
				result2.setAttribute ('class', 'check-s_pw1-result-not');
				check2=false;
				return false;
			}
		});
	}
	
	function fn_pwCheck2(){
		$('#s_pw2').blur(function(){
			var s_pw = $('#s_pw').val();
			var s_pw2 = $('#s_pw2').val();
			var result3 = document.getElementById('result3');
			if(s_pw == s_pw2){
				result3.textContent ='비밀번호가 일치합니다.';
				result3.setAttribute ('class', 'check-s_pw2-result-ok');
				check3=true;
				return true;
			}else{
				result3.textContent = '비밀번호가 일치하지 않습니다.';
				result3.setAttribute ('class', 'check-s_pw2-result-not');
				check3=false;
				return false;
			}
		});
	}
	
	function fn_phoneCheck(){
		$('#s_phone').blur(function(){
			var s_phone=$('#s_phone').val();
			$.ajax({
				url:'sellerPhoneSearch.seller?s_phone='+s_phone,
				type:'post',
				dataType:'json',
				success: function(data){
					var regExpPHONE = /^\d{3}-\d{3,4}-\d{4}$/;
					var result4 = document.getElementById('result4');
					if (data.result == 1) {
						result4.textContent ='이미 등록되어있는 연락처 입니다.';
						result4.setAttribute('class', 'check-s_phone-result-not2');
						check4=false;
						return false;
					}else{
						if (regExpPHONE.test(s_phone)) {
							result4.textContent ='사용 가능한 번호입니다.';
							result4.setAttribute('class', 'check-s_phone-result-not');
							check4=true;
							return false;
						}else{
							result4.textContent ='정확하게 입력하셨는지 다시한번 확인해주세요.';
							result4.setAttribute = ('class', 'check-s_phone-result-ok');
							check4=false;
							return true;
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
		
		$('#s_name').blur(function(){
			var s_name = $('#s_name').val();
			var regExpNAME = /[가-힣]{1,16}$/;
			var result5 = document.getElementById('result5');
			if(regExpNAME.test(s_name)){
				result5.textContent ='정상적인 이름입니다.';
				result5.setAttribute('class', 'check-s_name-result-ok');
				check5=true;
				return true;
			}else{
				result5.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
				result5.setAttribute('class', 'check-s_name-result-not');
				check5=false;
				return false;
			}
		});
	}
	
	function fn_birthCheck(){
		
		$('#s_birth').blur(function(){
			var s_birth = $('#s_birth').val();
			var regExpYEAR = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
			var result6 = document.getElementById('result6');
			if(regExpYEAR.test(s_birth)){
				result6.textContent = '';
				result6.setAttribute('class', 'check-s_birth-result-not');
				check6=true;
				return true;
			}else{
				result6.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
				result6.setAttribute('class', 'check-s_birth-result-not');
				check6=false;
				return false;
			}
		});
	}
	
	function fn_emailCheck(){
		$('#s_email').blur(function(){
			var s_email=$('#s_email').val();
			$.ajax({
				url:'sellerEmailSearch.seller?s_email='+s_email,
				type:'post',
				dataType:'json',
				success: function(data){
					var regExpEMAIL = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
					var result7 = document.getElementById('result7');
					if (data.result == 1) {
						result7.textContent ='이미 등록되어있는 이메일 입니다.';
						result7.setAttribute('class', 'check-s_email-result-not2');
						return false;
					}else{
						if(regExpEMAIL.test(s_email)){
							result7.textContent = '사용가능한 이메일입니다';
							result7.setAttribute('class', 'check-s_email-result-not');
							return true;
						}else{
							result7.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
							result7.setAttribute('class', 'check-s_email-result-not');
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

	var authKey=0;
	function fn_emailAuth1(){
		$('#emailAuth1').click(function(){
			var result7 = document.getElementById('result7');
			var s_email=$('#s_email').val();
			var Obj = {
					's_email':s_email
				};
			$.ajax({
				url:'emailAuth.seller',
				type:'post',
				data: JSON.stringify(Obj),
				contentType:'application/json',
				dataType:'json',
				success: function(data){
					if(authKey=data.resultMap){
						result7.textContent = '인증메일이 발송되었습니다';
						check7=true;
						return true;
					}else{
						result7.textContent = '인증메일이 발송을 실패하였습니다';
						check7=false;
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
			var s_email2 = $('#s_email2').val();
			var result8 = document.getElementById('result8');
			if(s_email2==authKey){
				result8.textContent = '인증이 완료되었습니다';
				result8.setAttribute('class', 'check-s_email2-result-not');
				check8=true;
				return true;
			}else{
				result8.textContent = '제대로 입력하셨는지 다시한번 확인해 주세요.';
				result8.setAttribute('class', 'check-s_email2-result-not');
				check8=false;
				return false;
			}
		});
	}

	function agreeCheck(){
		var agree='${chk_email}';	
		var m_agreement;
		if(agree==1){
			$('#m_agreement').val(1);
		}else{
			$('#m_agreement').val(0);
		}
	}
	
	function fn_sellerInsert(f){
		var res=true;
		if(check1==false ||
			check2==false ||
			check3==false ||
			check4==false ||
			check5==false ||
			check6==false ||
			check7==false ||
			check8==false ||
			$('#s_id').val()=='' || 
			$('#s_pw').val()=='' || 
			$('#s_pw2').val()=='' ||
			$('#s_name').val()=='' ||
			$('#s_birth').val()=='' ||
			$('#s_phone').val()=='' ||
			$('#s_email').val()=='' ||
			$('#s_email2').val()==''){
			$('#errorMsg').show();
			res=false;
		}else{
			$('#errorMsg').hide();
			f.action='sellerInsert.seller';
			f.submit();
			res=true;
		}
		return res;
	}		
	
</script>

	<br/><br/><br/>
	<div class="box">
	<form method="post">
	
		<label for="s_id">아이디</label><br/>
		<input type="text" name="s_id" id="s_id" /><br/>
		<div id="result" class="check-s_id-result" ></div>
		<br/><br/>
	
		<label for="s_pw">비밀번호</label><br/>
		<input type="password" name="s_pw" id="s_pw"/><br/>
		<div id="result2" class="check-s_pw1-result" ></div>
		<br/><br/>
	
		<label for="s_pw2">비밀번호 확인</label><br/>
		<input type="password" name="s_pw2" id="s_pw2" /><br/>
		<div id="result3" class="check-s_pw2-result" ></div>
		<br/><br/>
	
		<!-- 추가된 것 연락처, 사업자번호 -->
		<label for="s_phone">연락처</label><br/>
		<input type="text" name="s_phone" id="s_phone" /><br/>
		<div id="result4" class="check-s_phone-result" ></div>
		<br/><br/>
		
		<label for="s_name">이름</label><br/>
		<input type="text" name="s_name" id="s_name" /><br/>
		<div id="result5" class="check-s_name-result"></div>
		<br/><br/>
		
		<label for="s_birth">생년월일</label><br/>
		<input type="text" name="s_birth" id="s_birth" placeholder="예)19990720"/><br/>
		<div id="result6" class="check-s_birth-result"></div>
		<br/><br/>
		
		<label for="s_email">이메일</label><br/>
		<input type="text" name="s_email" id="s_email" />
		<input type="button" value="인증번호받기" id="emailAuth1" name="emailAuth1"/><br/>
		<div id="result7" class="check-s_email-result"></div>
		<br/>
		<input type="text" name="s_email2" id="s_email2" placeholder="인증번호를 입력하세요"/><br/><br/>
		<input type="button" value="인증완료" name="emailAuth2" id="emailAuth2"/><br/>
		<div id="result8" class="check-s_email2-result"></div>
		<br/><br/>
				
		<label for="s_companyNo">사업자번호</label><br/>
		<input type="text" name="s_companyNo" id="s_companyNo"/><br/>
		<div id="result9" class="check-s_companyNo-result"></div>
		<br/><br/>
				
		<input type="hidden" id="m_agreement" name="m_agreement"/>		
		<input type="button" id="btn" value="가입하기" onclick="fn_sellerInsert(this.form)">
		<br/>
		<div id="errorMsg"  class="result" style="display:none;">회원가입에 대한 정보를 작성해주세요</div>
		
	</form>
	</div>
	
	
	
<%@ include file="../template/footer.jsp" %>