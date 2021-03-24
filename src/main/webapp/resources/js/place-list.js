$(function(){
	fn_search();
	$.each(imgList, function(idx, img){
		$.each(img, function(i, placeImg){
			let imgTag = '<img class="list-thumbnail" alt="'+placeImg+'" src="resources/images/PlaceImages/'+placeImg+'">';
			if (i==0){
				$('.thumbnail-box').eq(idx).append($(imgTag).css('display', 'block'));
			} else {
				$('.thumbnail-box').eq(idx).append($(imgTag));
			}
		}); 
	});
	$('.thumbnail-box').slick({
		dots: true,
		fade: true,
		autoplay: true,
		autoplaySpeed: 2000
	});
	if(categoryNo != ''){
		$('div.category').eq(categoryNo-1).css('background','rgba(95, 158, 160, 1)');
	}
})
function fn_search(){
	$('#search-btn').click(function(){
		let regExp = /.{2}/;
		if(!regExp.test($('[name=query]').val())){
			alert('검색어는 최소 2글자이상 기입해야합니다.');
			return;
		}
		$('#search-form')[0].action='searchPlace.place';
		$('#search-form')[0].submit();
	});
}