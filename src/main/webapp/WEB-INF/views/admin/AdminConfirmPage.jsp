<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">
	.wrap {
		display: flex;
	}
	.confirmbox {
		margin: 100px 300px 0 0;
	}
	.listbox {
		width: 500px;
		height:auto;
		margin:50px auto;
	}
	table {
		border-collapse: collapse;
	}
	td, th {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		padding: 8px 8px;
		text-align: center;
	}
	th {
		background: #3ED0C8;
	}
	body{
		background: #F8EBEE;
	}
	#pagingBox{
		width: 500px;
		padding-left: 200px;
	}
	#paging {
		display: flex;
		text-align: center;
	}
	#paging div {
		width: 40px;
		height: 20px;
	}
	#paging .go-page:hover {
		cursor: pointer;
	}
	#paging .now-page {
		color: red;
	}
	#paging .disable {
		color: black;
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		confirmList();
		confirmView();
		confirmUpdate();
	});
	var page = 1; 
	
	function confirmList() {
		var obj= {
				"page": page
			};
			
			$.ajax({
				url: 'confirm',
				type: 'post',
				data: JSON.stringify(obj),  // 컨트롤러로 보내는 JSON 데이터
				contentType: 'application/json',  // 컨트롤러로 보내는 데이터의 타입
				dataType: 'json',
				success: function(result) {
					
					var paging = result.paging;
					
					$('#confirmList').empty();  // 기존 목록 제거
					
					// 회원이 있으면,
					if (result.exist) {
						$('#totalRecord').html('전체 : ' + paging.totalRecord + '명');  // 전체 인원수 출력
						confirmListTable(result.list);  // 목록 출력
					}
					// 회원이 없으면,
					else {
						$('<tr>')
						.append($('<td colspan="6" style="height: 20px;">').html('데이터가 없습니다.'))
						.appendTo('#confirmList');
					}
					$('#paging').empty();  // 기존 페이징 초기화
					
					if (paging.beginPage <= paging.pagePerBlock) {
						$('#paging').append('<div class="disable"><a>◀</a></div>');
					} else {
						$('#paging').append('<div class="prev-block go-page" data-page="' + (paging.beginPage - 1) + '"><a>◀</a></div>');
					}
					
					for (let p = paging.beginPage; p <= paging.endPage; p++) {
						if (paging.page == p) {  // 현재페이지는 링크가 안 됩니다.
							$('#paging').append('<div class="now-page"><a>' + p + '</a></div>')
						} else {
							$('#paging').append('<div class="go-page" data-page="' + p + '"><a>' + p + '</a></div>');
						}
					}
					
					// ▶
					if (paging.endPage >= paging.totalPage) {
						$('#paging').append('<div class="disable"><a>▶</a></div>');
					} else {
						$('#paging').append('<div class="next-block go-page" data-page="' + (paging.endPage + 1) + '"><a>▶</a></div>');
					}
				},
				error: function(){
					alert('실패');
				}
				
			});
			
			// 링크가 걸릴 때 이동할 페이지 번호를 알아내서 다시 목록을 뿌리는 함수들
			$('body').on('click', '.prev-block', function(){
				page = $(this).attr('data-page');
				confirmList();
			});
			$('body').on('click', '.go-page', function(){
				page = $(this).attr('data-page');
				confirmList();
			});
			$('body').on('click', '.next-block', function(){
				page = $(this).attr('data-page');
				confirmList();
			});
		
		}
	function confirmListTable(list) {
		$.each(list, function(idx, confirm){
			$('<tr>')
			.append( $('<td>').html(confirm.s_no) )
			.append( $('<td>').html(confirm.p_name) )
			.append( $('<input type="hidden" name="no" />').val(confirm.s_no) )
			.append( $('<td>').html('<input type="button" value="조회" id="btnView" />'))
			.appendTo('#confirmList');
		});
	}
	function confirmView() {
		$('body').on('click', '#btnView', function(){
	        var no = $(this).parents('tr').find('input:hidden[name="no"]').val();
	        $.ajax({
	        	url: 'confirm/' + no,  
	        	type: 'get',
	        	dataType: 'json',
	        	success: function(responseObj) {
	        		if (responseObj.result == true) {
	        			$('input:text[name="no" ]').val(responseObj.adminConfirmDto.s_no);
	        			$('input:text[name="name" ]').val(responseObj.adminConfirmDto.p_name);
	        			$('input:text[name="addr" ]').val(responseObj.adminConfirmDto.p_addr);
	        			$('input:text[name="addrdetail"]').val(responseObj.adminConfirmDto.p_addrdetail);
	        			$('input:text[name="confirm"]').val(responseObj.adminConfirmDto.p_confirm);
	        			$('.confirmbox input:hidden[name="no"]').remove();  
	        			$('.confirmbox').append($('<input type="hidden" name="no" />').val(no));  
	        		}
	        	},
	        	error: function(){
					alert('실패');
				}
	        });
		});
	}
	function confirmUpdate() {
		$('#btnUpdate').click(function(){
			var no = $(this).parents('div.confirmbox').find('input:hidden[name="no"]').val();  // 수정할 회원의 회원번호
			var confirm = $('input:text[name="confirm"]').val();
			var addr = $('input:text[name="addr"]').val();
			var addrdetail = $('input:text[name="addrdetail"]').val();
			var sendObj = {
					"s_no": no,
					"p_confirm" : confirm,
					"p_addr":addr,
					"p_addrdetail":addrdetail,
					
				};
			$.ajax({
				url: 'confirm',
				type: 'put',
				data: JSON.stringify(sendObj),
				contentType: 'application/json',
				dataType: 'json',
				success: function(responseObj) {
					if (responseObj.result == 1) {
						alert('승인이 완료되었습니다.');
						confirmList();
					} else {
						alert('승인이 실패했습니다.');
					}
				},
				error: function(){
					alert('실패');
				}
			});
			
		});
	}
</script>
</head>
<body>
	<div class="wrap">
	<div class="listbox">
			<span style="font-size: 40px; font-weight: bold">공간 승인목록</span>
			<div id=""></div><br/>
			<table>
				<thead>
					<tr>
						<th>판매자번호</th>
						<th>업체명</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody id="confirmList"></tbody>
				<tfoot>
					<tr>
						<td id="pagingBox" colspan="3">
							<div id="paging"></div>
						</td>
					</tr>
				</tfoot>
			</table>
		<input type="button" value="관리 목록페이지" style="float: left; margin:10px 0 10px 0" onclick="location.href='admin'" />
		</div>
		<div class="confirmbox">
			<h3>공간 조회/승인</h3>
			판매자번호<br/>
			<input type="text" name="no" readonly /><br/><br/>
			업체명<br/>
			<input type="text" name="name" readonly  /><br/><br/>
			위치<br/>
			<input type="text" name="addr" /><br/><br/>
			상세주소<br/>
			<input type="text" name="addrdetail" /><br/><br/>
			승인여부<br/>
			<input type="text" name="confirm" /><br/><br/>
			<input type="button" value="승인" id="btnUpdate" />
		</div>
	</div>
<%@ include file="../template/footer.jsp" %>		
</body>
</html>