<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/style/myPage/infoPopUp.css">	
<title>멤버 정보</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
	var cl_no;
	//var year = new Date().getFullYear();
	
	$(function() {
		$(window).on('unload', function(){
			return;
		});
		var formList = $('form[name="infoForm"]', opener.document);
		$.each(formList, function(idx, f) {
			var card = $(f).find("#card").val();
			var nick = $(f).find('#nick').val();
			//var birth = $(f).find("#name").val();
			//var age = year - (Number)birth.subString(3) + 1;
			
			var addIcon = '';
			for(var i = 0; i < card; i ++) {
				addIcon += '<i class="far fa-frown"></i>' ; 
			}
			
			if(nick == '${nick}') {
				cl_no = $(f).find("#cl_no").val();
				$('#nameAndAge').html($(f).find("#name").val()); 
				$('#nick').html($(f).find("#nick").val()); 
				$('#phone').html($(f).find("#phone").val()); 
				$('#email').html($(f).find("#email").val()); 
				$('#card-icon').html(addIcon);
				$('#card').val($(f).find("#card").val()); 
				$('#cl_no').val($(f).find("#cl_no").val());  
			}			
		});
	
	});
	
	function fn_addCard() {
		$.ajax({
			url: 'cardPlus/' + cl_no,
			type: 'get',
			dataType: 'json',
			success: function(responseObj){
				if(responseObj.cardCount == 0) {
					location.href='history.back()';
				} else {
					$('#card-icon').empty();
					for(var i = 0; i < responseObj.cardCount; i++) {
						$('#card-icon').append('<i class="far fa-frown"></i>');
					}
					alert('옐로카드가 추가되었습니다.');
				}
			},
			error: function() {
				alert('알 수 없는 문제가 발생했습니다. 다시 시도하세요.');
			}
			
		});
	}
</script>
	<h2>회원정보</h2>
	<hr>
	<div class="body">
		<div class="popup-top">
			<h3 id="nameAndAge"></h3>
				<form method="post">
					<!-- hidden -->
					<input type="hidden" id="card" name="card" />
					<input type="hidden" id="cl_no" name="cl_no" />
					<input type="button" value="옐로카드" onclick="fn_addCard()" />
				</form>
		</div>
		<div class="popup-body">
			<table>
				<tbody>
					<tr>
						<td>닉네임</td>
						<td id="nick"></td>
					</tr>
					<tr>
						<td>연락처</td>
						<td id="phone"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td id="email"></td>
					</tr>
				</tbody>
			</table>
			<div id="card-icon"></div>
		</div>
	</div>