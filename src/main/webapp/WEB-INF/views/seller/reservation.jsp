<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../myPage/myPage.jsp" />
<link rel="stylesheet" href="resources/style/seller/reservation.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>	
<script>

	$(document).ready(function(){
		reservationList();
		stateChange();
		stateCancle();
	});
	
	var page=1;
	
	function reservationList() {
		
		var obj = {
				'page': page,
				's_no': '${loginDto.s_no}'
		};
		
		$.ajax({
			url:'reservationList.seller',
			type:'post',
			data:JSON.stringify(obj),
			contentType:'application/json',
			dataType:'json',
			success:function(result){
				
				console.log(result.list);	// 지울것
				console.log(result.exist);	// 지울것
				
				var paging = result.paging;
				
				$('#reservationList').empty();
				
				if(result.exist){
					reservationListTable(result.list);
				}else{
					$('<tr>')
					.append($('<td colspan="6" style="height: 20px; text-align:center">').html('데이터가 없습니다.'))
					.appendTo('#reservationList');
				}
				
				$('#paging').empty();
				
				if(paging.beginPage <= paging.pagePerBlock){
					$('#paging').append('<div class="disable"><a>◀</a></div>');
				}else{
					$('#paging').append('<div class="prev-block go-page" data-page="' + (paging.beginPage - 1) + '"><a>◀</a></div>');
				}
				
				for(let p = paging.beginPage; p <= paging.endPage; p++){
					if(paging.page==p){
						$('#paging').append('<div class="now-page"style="cursor:pointer;"><a>' + p + '</a></div>')
					}else{
						$('#paging').append('<div class="go-page" data-page="' + p + '" style="cursor:pointer;"><a>' + p + '</a></div>');
					}
				}
				
				if(paging.endPage >= paging.totalPage){
					$('#paging').append('<div class="disable"><a>▶</a></div>');
				}else{
					$('#paging').append('<div class="next-block go-page" data-page="' + (paging.endPage + 1) + '"><a>▶</a></div>');
				}
			},
			error: function(){
				alert('실패');
			}
		});
	
		$('body').on('click', '.prev-block', function(){
			page = $(this).attr('data-page');
			reservationList();
		});
		$('body').on('click', '.go-page', function(){
			page = $(this).attr('data-page');
			reservationList();
		});
		$('body').on('click', '.next-block', function(){
			page = $(this).attr('data-page');
			reservationList();
		});
	}
	
	function reservationListTable(list){
		$.each(list, function(idx, reservation){
			$('<tr>')
			.append( $('<td>').html(reservation.m_no) )
			.append( $('<td>').html(reservation.res_people) )
			.append( $('<td>').html(reservation.res_price) )
			.append( $('<td>').html(reservation.res_state) )
			.append( $('<td>').html('<input type="hidden" name="res_no" value="' + reservation.res_no + '" />' ) )
			.append( $('<td>').html('<input type="button" value="예약확인" id="btn1" />'))
			.append( $('<td>').html('<input type="button" value="예약취소" id="btn2" />'))
			.appendTo('#reservationList');
		});
	}
	
	function stateChange() {
		
		$('body').on('click', '#btn1', function(){
			var obj = {
				'res_no': $(this).parents('tr').find('input:hidden[name="res_no"]').val()
			};
			$.ajax({
				url : 'stateChange.seller',
				type: 'post',
				data: JSON.stringify(obj),
				contentType: 'application/json',
				dataType: 'json',
				success:function(data){
					if (data.result) {
						alert('예약완료되었습니다');
						reservationList();
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}
	
	function stateCancle() {
		
		$('body').on('click', '#btn2', function(){
			var obj = {
				'res_no': $(this).parents('tr').find('input:hidden[name="res_no"]').val()
			};
			$.ajax({
				url : 'stateCancle.seller',
				type: 'post',
				data: JSON.stringify(obj),
				contentType: 'application/json',
				dataType: 'json',
				success:function(data){
					if (data.result) {
						alert('예약취소되었습니다');
						reservationList();
					}
				},
				error: function(){
					alert('실패');
				}
			});
		});
	}

</script>
<div class="mypage-contents">
<h1>예약 리스트</h1>
<h3>-현재 예약 대기 목록</h3>
<div class="content-wrap">
<table>
	<thead>
		<tr>
			<th>예약자</th>
			<th>예약인원</th>
			<th>예약금</th>
			<th>예약상태</th>
		</tr>
	</thead>
	<tbody id="reservationList">
	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="paging"></div>			
			</td>
		</tr>
	</tfoot>
</table>
</div>
</div>





<%@ include file="../template/footer.jsp" %>