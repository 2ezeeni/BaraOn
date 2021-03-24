$(function(){
	$.each(placeImgList, function(idx, placeImg){
			$('.place-img').eq(idx).prop('src', 'resources/images/PlaceImages/'+placeImg[0]);
		})
		$('.event-slider').slick({
			autoplay:true,
			autoplaySpeed:2000,
			nextArrow:$('.next'),
			prevArrow:$('.prev'),
			dots:true
		});
		$('.club-slide').slick({
			slidesToShow:3,
			slidesToScroll:1,
			autoplay:true,
			autoplaySpeed:2000,
			centerMode: true,
			centerPadding: '60px'
		});
		$('.place-slide').slick({
			slidesToShow:3,
			slidesToScroll:1,
			autoplay:true,
			autoplaySpeed:2000,
			centerMode: true,
			centerPadding: '60px'
		});
})