<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		reviewList();
		reviewDelete();
	});
	var page = 1;
	function reviewList(){
	var obj= {
			"page": page
		};
		
		$.ajax({
			url: 'review',
			type: 'post',
			data: JSON.stringify(obj),  // 컨트롤러로 보내는 JSON 데이터
			contentType: 'application/json',  // 컨트롤러로 보내는 데이터의 타입
			dataType: 'json',
			success: function(result) {
				
				var paging = result.paging;
				
				$('#reviewList').empty();  // 기존 목록 제거
				
				// 회원이 있으면,
				if (result.exist) {
					$('#totalRecord').html('전체 : ' + paging.totalRecord + '명');  // 전체 인원수 출력
					reviewListTable(result.list);  // 목록 출력
				}
				// 회원이 없으면,
				else {
					$('<tr>')
					.append($('<td colspan="6" style="height: 20px;">').html('데이터가 없습니다.'))
					.appendTo('#reviewList');
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
			reviewList();
		});
		$('body').on('click', '.go-page', function(){
			page = $(this).attr('data-page');
			reviewList();
		});
		$('body').on('click', '.next-block', function(){
			page = $(this).attr('data-page');
			reviewList();
		});
	}
	function reviewListTable(list) {
		$.each(list, function(idx, review){
			$('<tr>')
			.append( $('<td>').html(review.rv_no) )
			.append( $('<td>').html(review.rv_content) )
			.append( $('<input type="hidden" name="no" />').val(review.rv_no) )
			.append( $('<td>').html('<input type="button" value="삭제" id="btnDelete" />'))
			.appendTo('#reviewList');
		});
	}
	function reviewDelete() {
		$('body').on('click', '#btnDelete', function(){
	        var no = $(this).parents('tr').find('input:hidden[name="no"]').val();
	        var check = confirm(no + '번 후기를 삭제할까요?');
	        if (check) {
		        $.ajax({
		        	url: 'review/' + no,
		        	type: 'delete',
		        	dataType: 'json',
		        	success: function(responseObj) {
		        		if (responseObj.result == 1) {
							alert('댓글이 삭제되었습니다.');
							reviewList();
						} else {
							alert('댓글 삭제가 실패했습니다.');
						}
		        	},
		        	error: function(){
						alert('실패');
					}
		        });
	        }
		});
	}
</script>
<title></title>
<link rel="stylesheet" href="resources/style/common.css">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">
	body{
		background:#F8EBEE;
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
	.review{
		width: 1200px;
		height:auto;
		margin:50px 0 0 300px;
	}
	#pagingBox{
		width: 500px;
		padding-left: 500px;
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
	.no , .delete{
		width:100px;
	}
	
</style>
</head>
<body>
	<div class="review">
			<span style="font-size: 40px; font-weight: bold">리뷰 목록</span><br/><br/>
			<table>
				<thead>
					<tr>
						<th class="no">리뷰번호</th>
						<th>후기내용</th>
						<th class="delete">비고</th>
					</tr>
				</thead>
				<tbody id="reviewList"></tbody>
				<tfoot>
					<tr>
						<td id="pagingBox" colspan="3">
							<div id="paging"></div>
						</td>
					</tr>
				</tfoot>
			</table><br/>
		<input type="button" value="관리 목록페이지" style="float: left; margin:10px 0 10px 0" onclick="location.href='admin'" /><br/><br/>
		</div><br/>
<%@ include file="../template/footer.jsp" %>	
</body>
</html>