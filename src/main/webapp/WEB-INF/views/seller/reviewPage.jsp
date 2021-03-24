<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../myPage/myPage.jsp" />
<link rel="stylesheet" href="resources/style/seller/reviewPage.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>	
<script type="text/javascript">
	$(document).ready(function(){
		placeList();
	});
	
	var page=1;
	
	function placeList(){
		
		var obj={
				's_no':'${loginDto.s_no}',
				'page':page
		};
		$.ajax({
			url:'placeList.seller',
			type:'post',
			data:JSON.stringify(obj),
			contentType:'application/json',
			dataType:'json',
			success:function(result){
				
				console.log(result.list);
				console.log(result.exist);
				
				var paging = result.paging;
				
				$('#placeList').empty();
				
				if(result.exist){
					placeListTable(result.list);
				}else{
					$('<tr>')
					.append($('<td colspan="5" style="height: 20px;">').html('데이터가 없습니다.'))
					.appendTo('#placeList');
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
						$('#paging').append('<div class="go-page" data-page="' + p + '"><a>' + p + '</a></div>');
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
			placeList();
		});
		$('body').on('click', '.go-page', function(){
			page = $(this).attr('data-page');
			placeList();
		});
		$('body').on('click', '.next-block', function(){
			page = $(this).attr('data-page');
			placeList();
		});
	}
	
	
	function placeListTable(list){
		
		$.each(list, function(idx, place){
			let strHtml = '<tr><td>'+place.p_title+'</td>';
			if ((place.rv_content).length >=15){
				strHtml += '<td>'+place.rv_content.substring(0,15)+'...</td>';
			} else {
				strHtml += '<td>'+place.rv_content+'</td>';
			}
			strHtml += '<td>'+place.m_id+'</td>';
			switch(place.rv_star){
			case 1: strHtml += '<td>'+'☆☆☆☆★'+'</td>'; break;
			case 2: strHtml += '<td>'+'☆☆☆★★'+'</td>'; break;
			case 3: strHtml += '<td>'+'☆☆★★★'+'</td>'; break;
			case 4: strHtml += '<td>'+'☆★★★★'+'</td>'; break;
			default: strHtml += '<td>'+'★★★★★'+'</td>';
			}
			strHtml += '</tr>';
			$('#placeList').append(strHtml);
		});
	}
</script>
<div class="mypage-contents">
	<h3>이용 후기 관리</h3>
	<br/>
		<div class="content-wrap">
	<div class="right">
	<table>
		<thead>
			<tr>
				<th>공간명</th>
				<th>후기</th>
				<th>작성자</th>
				<th>별점</th>
			</tr>
		</thead>
		<tbody id="placeList">
		
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
	</div>




<%@ include file="../template/footer.jsp" %>