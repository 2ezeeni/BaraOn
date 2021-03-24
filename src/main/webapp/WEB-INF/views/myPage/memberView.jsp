<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../myPage/myPage.jsp" />
<link rel="stylesheet" href="resources/style/myPage/memberView.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>

	$(function(){
		fn_pwSerch();
	});

	function fn_pwOpen(){
		if($('#divPwChange').css('display')=='none'){
			$('#divPwChange').show();
		}else{
			$('#divPwChange').hide();
		}
	}
	
	function fn_NickOpen(){
		if($('#divNickChange').css('display')=='none'){
			$('#divNickChange').show();
		}else{
			$('#divNickChange').hide();
		}
	}
	
	function fn_pwSerch(){	// 현재 비밀번호를 입력했을 시 DB에 검색하여 일치하지 않으면 #text1(비밀번호가 일치하지 않습니다)를 display:block한다
		var m_id='${loginDto.m_id}';
		var m_pw=$('#pw1').val();
		var Obj={
				'm_id':m_id
		}
		$('#pw1').blur(function(){
			$.ajax({
				url:'memberPwSearch.member',
				type:'post',
				data: JSON.stringify(Obj),
				contentType:'application/json',
				dataType:'json',
				success:function(data){
					var result = document.getElementById('result');
					if(data.result != m_pw){
						result.textContent ='비밀번호가 일치합니다.';
						result.setAttribute('class', 'check-m_pw-result-ok');
						return true;
					}else{
						result.textContent = '비밀번호가 일치하지 않습니다';
						result.setAttribute('class', 'check-m_pw-result-not');
						return false;
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}
	
	function fn_pwCheck(){	// 새로운 비밀번호가 정규식에 맞는지 검사한다.
		var pw2=$('#pw2').val();
		var regExpPw = /[a-zA-Z0-9가-힣]{6,16}$/;
		var result2 = document.getElementById('result2');
		if(regExpPw.test(pw2)){
			result2.textContent ='사용가능한 비밀번호입니다.';
			result2.setAttribute('class', 'check-m_pw2-result2-ok');
			return true;
		}else{
			result2.textContent = '사용이 불가능한 비밀번호입니다. 영문 대소문자, 숫자를 혼용하셔야 합니다';
			result2.setAttribute('class', 'check-m_pw2-result2-not');
			return false;
		}
	}
	
	function fn_pwCheck2(){		// 새로운 비밀번호와 새로운 비밀번호가 맞는지 확인하여 맞는 문장을 출력한다.
		var result3 = document.getElementById('result3');
		if($('#pw2').val() == $('#pw3').val()){
			result3.textContent ='비밀번호가 일치합니다.';
			result3.setAttribute('class', 'check-m_pw3-result3-ok');
			return true;
		}else{
			result3.textContent = '새로운 비밀번호와 일치하지 않습니다. 다시확인하세요';
			result3.setAttribute('class', 'check-m_pw3-result3-not');
			return false;
		}
	}
	
	
	function fn_memberPwUpdate(){	// 비밀번호를 업데이트 한다.
		var obj={
				"m_no": '${loginDto.m_no}',
				"m_pw": $('#pw2').val()
		};
		$.ajax({
			url:'memberPwUpdate.member',
			type:'post',
			
			data:JSON.stringify(obj),
			contentType: 'application/json',
			
			dataType:'json',
			success:function(responseObj){
				if (responseObj.result == 1) {
					alert('비밀번호가 수정되었습니다.');
				} else {
					alert('비밀번호 수정이 실패했습니다.');
				}
			},
			error: function(){
				alert('실패');
			}
		});
	}
	
	
	function fn_NickSearch(){
		var m_id='${loginDto.m_id}';
		var m_nick=$('#nick2').val();
		$.ajax({
			url:'memberNickSearch.member?m_nick='+m_nick,
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.result == 0){
					alert('사용가능합니다');
				}else{
					alert('이미 있는 닉네임입니다');
				}
			},
			error: function(){
				alert('실패');
			}
		});
	}
	
	function fn_memberNickUpdate(){
		var obj={
				"m_no":'${loginDto.m_no}',
				"m_nick":$('#nick2').val()
		};
		$.ajax({
			url:'memberNickUpdate.member',
			type:'post',
			dataType:'json',
			data:JSON.stringify(obj),
			contentType: 'application/json',
			success:function(responseObj){
				if (responseObj.result == 1) {
					alert('닉네임이 수정되었습니다.');
				} else {
					alert('닉네임 수정이 실패했습니다.');
				}
			},
			error: function(){
				alert('실패');
			}
		});
	}
	
	function fn_memberUpdate(f){
		f.action='pwCheck.myPage';
		f.submit();
	}
	
	
</script>	
	<div class="mypage-contents">
		<h3>마이페이지</h3>
		<br/>		
		<div class="content-wrap">
		
	<form id="member">
		<table>
			<tbody>
				<tr>
					<th>아이디</th>
					<td>${loginDto.m_id}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${loginDto.m_name}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="button" id="btn1" value="비밀번호 변경" onclick="fn_pwOpen()"/></td>
				</tr>
				<!--	비밀번호 변경  시작 -->
				<tr style="display:none"; id="divPwChange">
					<th></th>
					<td>
						<table>
							<tbody>
								<tr>
									<td>현재 비밀번호</td>
									<td><input type="password" id="pw1" name="pw1"/></td>
									<td>
										<div id="result" class="check-m_pw-result"></div>
									</td>
								</tr>
								<tr>
									<td>새 비밀번호</td>
									<td><input type="password" id="pw2" name="pw2" onblur="fn_pwCheck()"/></td>
									<td>
										<div id="result2" class="check-m_pw2-result2"></div>
									</td>
								</tr>
								<tr>
									<td>새 비밀번호 확인</td>
									<td><input type="password" id="pw3" name="pw3" onblur="fn_pwCheck2()"/></td>
									<td>
										<div id="result3" class="check-m_pw3-result3"></div>
									</td>
								</tr>
								<tr>
									<td><input type="button" value="수정하기" onclick="fn_memberPwUpdate()"/></td>
									<td>*비밀번호 변경시 수정하기 버튼을 누르셔야 합니다</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				
				<tr>
					<th>닉네임</th>
					<td> ${loginDto.m_nick} <input type="button" value="닉네임 변경" onclick="fn_NickOpen()"/></td>
				</tr>
				
				<!--	닉네임 변경  시작 -->
				<tr style="display:none"; id="divNickChange">
					<th></th>
					<td>
						<table>
							<tbody>
								<tr>
									<td>새 닉네임</td>
									<td><input type="text" id="nick2" name="nick2"/></td>
								</tr>
								<tr>
									<td><input type="button" value="중복검사하기" onclick="fn_NickSearch()"/></td>
								</tr>
								<tr>
									<td><input type="button" value="수정하기" onclick="fn_memberNickUpdate()"/></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				
				<tr>
					<th>생년월일</th>
					<td>${loginDto.m_birth}</td>
				</tr>
				<tr>
					<th>휴대전화</th>
					<td><input type="text" placeholder="${loginDto.m_phone}" id="m_phone" name="m_phone" value="${loginDto.m_phone}"/></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" placeholder="${loginDto.m_email}"id="m_email" name="m_email" value="${loginDto.m_email}"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="확인" onclick="fn_memberUpdate(this.form)"/>
					</td>
				</tr>
				
			</tbody>
		</table>
	</form>
</div></div>


<%@ include file="../template/footer.jsp" %>