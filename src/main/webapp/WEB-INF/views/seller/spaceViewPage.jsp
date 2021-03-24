<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../myPage/myPage.jsp" />
<link rel="stylesheet" href="resources/style/seller/spaceView.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
$(function(){
	fn_placeSearch();	
});



function fn_placeSearch(){
	var pc_no = $('select[name="category1"]').val();
	var obj1={
		's_no':'${loginDto.s_no}'
	};
	var obj2={
		'pc_no':pc_no,	
		's_no':'${loginDto.s_no}'
	};
	if(pc_no==0){
		$.ajax({
			url:'allPlaceSearch.seller',
			type:'post',
			data: JSON.stringify(obj1),
			contentType:'application/json',
			dataType: 'json',
			success: function(data){
				$('#place-wrap').empty();
				let imgList = JSON.parse(data.imgList);
				$(function(){
					$.each(imgList, function(idx, img){
						let divTag = $('<div>').addClass('placeList').prop('id', 'placeList');
						$('.place-wrap').append(divTag);
						$.each(img, function(i, placeImg){
							let imgTag = '<img class="list-thumbnail" alt="'+placeImg+'" src="resources/images/PlaceImages/'+placeImg+'">';
							$('.placeList').eq(idx).append(imgTag);
						}) 
					});
				})
			},
			error: function(){
				alert('실패');
			}
		});
	} else {
		$.ajax({
			url:'placeCaSearch.seller',
			type:'post',
			data: JSON.stringify(obj2),	
			contentType:'application/json',
			dataType: 'json',
			success: function(data){
				$('#place-wrap').empty();
				let imgList = JSON.parse(data.imgList);
				$(function(){
					console.log(data.list);
					$.each(imgList, function(idx, img){
						let divTag = $('<div>').addClass('placeList').prop('id', 'placeList');
						$('.place-wrap').append(divTag);
						$.each(img, function(i, placeImg){
							let imgTag = '<img class="list-thumbnail" alt="'+placeImg+'" src="resources/images/PlaceImages/'+placeImg+'">';
							$('.placeList').eq(idx).append(imgTag);
						}) 
					});
				})
			},
			error: function(){
				alert('실패');
			}
		});
	}
}

function fn_placeSearch2(){
	var pc_no = $('select[name="category2"]').val();
	var obj1={
		's_no':'${loginDto.s_no}'
	};
	var obj2={
		'pc_no':pc_no,	
		's_no':'${loginDto.s_no}'
	};
	if(pc_no==0){
		$.ajax({
			url:'allPlaceSearch2.seller',
			type:'post',
			data: JSON.stringify(obj1),
			contentType:'application/json',
			dataType: 'json',
			success: function(data){
				let imgList = JSON.parse(data.imgList);
				$(function(){
					$.each(imgList, function(idx, img){
						let divTag = $('<div>').addClass('placeList').prop('id', 'placeList');
						$('.place-wrap').append(divTag);
						$.each(img, function(i, placeImg){
							let imgTag = '<img class="list-thumbnail" alt="'+placeImg+'" src="resources/images/PlaceImages/'+placeImg+'">';
							$('.placeList').eq(idx).append(imgTag);
						}) 
					});
				})
			},
			error: function(){
				alert('실패');
			}
		});
	} else {
		$.ajax({
			url:'placeCaSearch2.seller',
			type:'post',
			data: JSON.stringify(obj2),	
			contentType:'application/json',
			dataType: 'json',
			success: function(data){
				$('#placeList').empty();
				let imgList = JSON.parse(data.imgList);
				$(function(){
					$.each(imgList, function(idx, img){
						let divTag = $('<div>').addClass('placeList').prop('id', 'placeList');
						$('.place-wrap').append(divTag);
						$.each(img, function(i, placeImg){
							let imgTag = '<img class="list-thumbnail" alt="'+placeImg+'" src="resources/images/PlaceImages/'+placeImg+'">';
							$('.placeList').eq(idx).append(imgTag);
						}) 
					});
				})
			},
			error: function(){
				alert('실패');
			}
		});
	}
}


	
</script>
<div class="mypage-contents">
<h3>마이페이지</h3>
<h3>공간정보관리</h3>
<h3>OPEN</h3>
<br/>
<div class="content-wrap">
	<form method="get">
		<select name="category1" id="category1" onchange="fn_placeSearch()">
			<option value="0" selected>전체</option>
				<c:forEach var="PlaceCategoryDto" items="${categoryList}">
					<option value="${PlaceCategoryDto.pc_no}">
						${PlaceCategoryDto.pc_name}
					</option>
				</c:forEach>
		</select>
	</form>

<br/>
<div class="place-wrap" id="place-wrap">

</div>


<br/><br/>
<h3>CLOSED</h3>
<br/>

<form method="get">
	<select name="category2" id="category2" onchange="fn_placeSearch2()">
		<option value="">전체</option>
			<c:forEach var="PlaceCategoryDto" items="${categoryList}">
				<option value="${PlaceCategoryDto.pc_no}">
					${PlaceCategoryDto.pc_no}
				</option>
			</c:forEach>
	</select>
</form>

<div class="place-wrap2">
	<c:forEach var="placeDto" items="${list}" >
			<div class="placeList2" id="placeList2">
			</div>
	</c:forEach>
</div>

	</div>
	</div>	

<%@ include file="../template/footer.jsp" %>