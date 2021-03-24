function fn_facilityList(list, appendTo){
	$.each(list, function(idx, facility){
		let strHtml = '<div class="facility">'+facility+'</div>';
		$(appendTo).append(strHtml);
	});
}
function fn_remarkList(list, appendTo){
	$.each(list, function(idx, remark){
		let strHtml = '<li class="remark">'+remark+'</li>';
		$(appendTo).append(strHtml);
	});
}
function fn_moreBtn(btn){
	$(btn).click(function(event){
		if($(event.target).parent().next().is('.active')===false){
			$(event.target).parent().next().addClass('active');
			$(event.target).text('▲');
		} else {
			$(event.target).parent().next().removeClass('active');
			$(event.target).text('▼');
		}
	});
}
function fn_allChk(){
	$('#terms-all').on('click', function(){
		let chkList = $('.terms');
		if($(this).prop('checked')===true){
			$(chkList).prop('checked', true);
		} else {
			$(chkList).prop('checked', false);
		}
	});
}
function fn_chkBox(){
	const chkBox=$('.terms');
	$('.terms').on('click',function(){
		let chked = $('.terms:checked');
		if(chkBox.length === chked.length){
			$('#terms-all').prop('checked', true);
		} else {
			$('#terms-all').prop('checked', false);
		}
	});
}
$(function(){
	fn_chkBox();
	fn_allChk();
	fn_moreBtn('.more-btn');
	fn_facilityList(facilityList, '#facility-list');
	fn_remarkList(remarkList, '#remark-list');
	$("#res-update").click(function() {
		
		if(isProgress){
			return;
		}
		
		let chkBoxList = $('.terms');
		
		$.each(chkBoxList, function(idx, chkBox){
			if($(chkBox).prop('checked')===false){
				alert('서비스 동의를 체크해주세요');
				isPossible=false;
				return false;
			} else{
				isPossible=true;
			}
		});
		
		if(!isPossible){
			return;
		}
		
		isProgress=true;
		
		var IMP = window.IMP;
		IMP.init('imp97817701');
		// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
		IMP.request_pay({
			pg : 'inicis',
			/*
			'kakao':카카오페이,
			html5_inicis':이니시스(웹표준결제)
			'nice':나이스페이
			'jtnet':제이티넷
			'uplus':LG유플러스
			'danal':다날
			'payco':페이코
			'syrup':시럽페이
			'paypal':페이팔
			 */
			pay_method : 'card',
			/*
			'samsung':삼성페이,
			'card':신용카드,
			'trans':실시간계좌이체,
			'vbank':가상계좌,
			'phone':휴대폰소액결제
			 */
			merchant_uid : resNo +''+ new Date().getTime(),
			/*
			merchant_uid에 경우
			https://docs.iamport.kr/implementation/payment
			 */
			name : placeName,
			//결제창에서 보여질 이름
			amount : $('[name="res_price"]').val(),
			buyer_email : $('[name="res_email"]').val(),
			buyer_name : $('[name="res_name"]').val(),
			buyer_tel : $('[name="res_phone"]').val()
		}, function(rsp) {
			console.log(rsp);
			if (rsp.success) {
				let sendObj = {
						'res_no' : resNo,
						'res_email' : rsp.buyer_email,
						'res_price' : rsp.paid_amount,
						'res_applynum' : rsp.apply_num,
						'res_purpose' : $('[name="res_purpose"]').val(),
						'res_requirement' : $('[name="requirement"]').val(),
				};
				$.ajax({
					url:'reservationUpdate.reservation',
					type:'put',
					data:JSON.stringify(sendObj),
					contentType:'application/json; charset=utf-8',
					dataType:'json',
					success:function(responseObj){
						if(responseObj.result){
							alert('결제에 성공했습니다. 예약대기상태로 변경됩니다.');
						} else {
							alert('결제에 실패했습니다. 다시 시도해주세요');
						}
					},
					error:function(request,status,error){
						alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					},
					complete:function(){
						setTimeout(function(){
							isProgress = false;
						},1000);
						location.href='placeListPage.place';
					}
				});
			} else {
				var msg = '결제에 실패하였습니다. 리스트페이지로 이동합니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				alert(msg);
				
				location.href='placeListPage.place';
			}
		});
	});
	
})