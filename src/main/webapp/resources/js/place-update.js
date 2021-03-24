$(function(){
	$('#addr-search-btn').click(fn_searchPopUp);
	$('#place-addr').click(fn_searchPopUp);
	fn_addInput('p_info', '#add-info-list-btn', '#place-info-list');
	fn_addInput('p_remark', '#add-remark-list-btn', '#place-remark-list');
	fn_removeInput('#remove-info-list-btn', '#place-info-list');
	fn_removeInput('#remove-remark-list-btn', '#place-remark-list');
	fn_addFacility($('.add-facility-btn').last());
	fn_addOption();
	fn_previewThumbnail('#upload-btn');
	fn_previewThumbnail('.option-thumbnail');
	fn_removeOption();
	fn_deleteOption();
	fn_appendList(infoList, '#place-info-list', 'p_info');
	fn_appendList(remarkList, '#place-remark-list', 'p_remark');
	fn_uploadedImg();
	let selectedCategory = $('input[value="${placeCategoryDto.pc_no}"]');
	$(selectedCategory).prev('a').css('background', 'rgba(95, 158, 160, 1)');
	fn_optionFacility('.facility-list', poFacilities);
	fn_countChar('.place-input');
})
/* 글자수 count */
function fn_countChar(countTag){
	$(countTag).on('keyup', function(event){
		let count = $(event.target).val().length;
		$(event.target).next().text(count);
	});
}

function fn_optionFacility(tagTo, list){
	$.each(list, function(idx,facilityList){
		$.each(facilityList, function(i, facility){
			$(tagTo).eq(idx).append('<li>'+facility+'<input type="hidden" name="po_facility" value="'+facility+'" /><span class="remove" onclick="fn_removeFacility(event)">&times;</span></li>');
		});
		$(tagTo).next().val(facilityList.length);
	});
}
function fn_appendList(list, appendToTag, name){
	$.each(list,function(idx, item){
		$(appendToTag).append(`<li><input class="place-input" type="text" name="`+name+`" value="`+item+`" placeholder="추가하려면 추가 버튼을 눌러주세요" required /></li>`);
	});
}
function fn_uploadedImg(){
	$.each(thumbnailList, function(idx, img){
		let strHtml =`
		<div class="uploaded-img-box">
			<img alt="이미지" src="resources/images/PlaceImages/`+img+`" />
			<input type="hidden" value="`+img+`" />
			<button type="button" onclick="fn_deleteThumbnail(event)" >&times;</button>
		</div>
		`;
		$('#uploaded-thumb-list').append(strHtml);
	});
}
function fn_deleteThumbnail(e){
	let strHtml = '<input type="hidden" name="deleted_img" value="'+$(e.target).prev().val()+'"/>'
	$('#uploaded-thumb-list').append(strHtml);
	$(e.target).parent().remove();
}
function fn_optionUpdate(f){
	let sendObj = new FormData(f);
	$.ajax({
		url:'placeOptionUpdate.place',
		type:'post',
		enctype:'multipart/form-data',
		data: sendObj,
		processData: false,
		contentType: false,
		dataType:'json',
		success: function(responseObj){
			if(responseObj.result==1){
				alert('수정되었습니다.');
			} else if(responseObj.result==-1) {
				alert('파일형식이 맞지않습니다. 파일 변경 후 다시 시도해주세요');
			} else {
				alert('수정에 실패했습니다. 다시 시도해 주세요');	
			}
		},
		error: function(request, status, error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}
function fn_optionInsert(f){
	let sendObj = new FormData(f);
	$.ajax({
		url:'placeOptionInsert.place',
		type:'post',
		enctype:'multipart/form-data',
		data: sendObj,
		processData: false,
		contentType: false,
		dataType:'json',
		success:function(responseObj){
			if(responseObj.result==1){
				alert('옵션이 추가되었습니다.');
			} else if(responseObj.result==-1) {
				alert('파일형식이 맞지않습니다. 파일 변경 후 다시 시도해주세요');
			} else {
				alert('옵션 추가에 실패했습니다. 다시 시도해 주세요');	
			}
		},
		error: function(request, status, error){
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
		
	});
}

/* 등록되어 있던 옵션 삭제 */
function fn_deleteOption(){
	$('.select-remove-btn').click(function(event){
		if(confirm('복구가 불가능합니다 삭제하시겠습니까?')){
			$.ajax({
				url:'placeOptionDelete.place/'+$(event.target).prev().prev().val(),
				type:'delete',
				dataType:'json',
				success:function(responseObj){
					if(responseObj.result > 0){
						alert('삭제되었습니다.');
					} else {
						alert('삭제에 실패했습니다. 다시 시도해주세요');
					}
				},
				error:function(request, status, error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					return;
				}
			});
			$(event.target).closest('form').remove();
		}
	});
}


/* 주소 찾기 */
function fn_searchPopUp(){
	new daum.Postcode({
		oncomplete : function(data){
			let addr = '';
			let extraAddr = '';
			
			if(data.userSelectedType ==='R'){
				addr = data.roadAddress;
				extraAddr = data.bname;
			} else {
				addr = data.jibunAddress;
				extraAddr = data.bname;
			}
			$('#place-addr').val(addr);
			$('#place-bname').val(extraAddr);
			$('#place-addr-detail').focus();
		}
	}).open();
}

/* update submit 전 카테고리 선택 검사 */
function fn_placeUpdate(f){
	if(f.pc_no.value==''){
		alert('카테고리를 선택해 주세요');
		return;
	}
	$.each($('input:required'), function(idx, requiredInput){
		if($(requiredInput).val()== null || $(requiredInput).val()==''){
			alert('누락된 필수입력사항이 있습니다. 확인해주세요');
			$(requiredInput).focus();
			return;
		}
	});
	if($('.uploaded-img-box img').length==0 && $('#upload-btn').val()==''){
		alert('적어도 하나의 이미지는 첨부해야 합니다.');
		return;
		
	}
	if(f.p_content.value==''){
		alert('누락된 필수입력사항이 있습니다. 확인해주세요');
		f.p_content.focus();
		return;
	}
	f.action = 'placeUpdate.place';
	f.submit();
}

/* list를 추가하기 위한 */
function fn_addInput(inputName, btn, tag){
	$(btn).click(function(){
		if($(tag).find('li').length>9){
			alert('최대 10개까지입니다.');
			return;
		}
		let addInput = '<li><input class="place-input" type="text" name="'+inputName+'" placeholder="추가하려면 추가 버튼을 눌러주세요" required /></li>';
		$(tag).append(addInput);
		$(tag).find('input').last().focus();
	})
}
function fn_removeInput(btn, tag){
	$(btn).click(function(event){
		if($(tag).find('li').length<2){
			alert('최소 1개는 필수입니다.');
			return;
		}
		$(tag).find('li').last().remove();
	});
}

function fn_addFacility(btn){
	$(btn).click(function(event){
		let facilityInput = $(event.target).prev();
		let value = $(facilityInput).val();
		let ul = $(event.target).next();
		let countInput = $(event.target).next().next();
		if(value==''){
			alert('편의시설을 입력해주세요');
			$(facilityInput).focus();
			return;
		}
		if($(ul).find('li').length>7){
			alert('8개이상 추가할 수 없습니다.');
			return;
		}
		let addList = '<li>'+value+'<input type="hidden" name="po_facility" value="'+value+'" /><span class="remove" onclick="fn_removeFacility(event)">&times;</span></li>';
		$(ul).append(addList);
		$(facilityInput).val('');
		$(countInput).val(parseInt($(countInput).val())+1);
		$(facilityInput).focus();
	});
}
function fn_removeFacility(e){
	let facilityCount = $(e.target).closest('#facility-list').next();
	$(e.target).parent().remove();
	$(facilityCount).val(parseInt($(facilityCount).val())-1);
}
function fn_addOption(){
	$('#add-option-btn').click(function(event){
		let strHtml =`
			<form class="option-form" method="post" enctype="multipart/form-data">
				<section class="option">
				<article class="option-box">
					<div class="option-subtitle">
						공간이름<span class="required-data">필수 사항</span>
					</div>
					<div class="option-content">
						<input class="option-input" type="text" name="po_name" />
						<span id="character-length" ></span>
					</div>
				</article>
				<article class="option-box">
					<div class="option-subtitle">
						가격<span class="required-data">필수 사항</span>
					</div>
					<div class="option-content">
						평일 :
						<br/>
						 <input type="number" name="po_dayPrice" style="width:80px;"  required />원<br/>
						주말(공휴일) :
						<br/>
						<input type="number" name="po_holiday" style="width:80px;"  required />원
					</div>
				</article>
				<article class="option-box">
					<div class="option-subtitle">
						인원<span class="required-data">필수 사항</span>
					</div>
					<div class="option-content">
						최소 : <input type="number" name="po_min" style="width:80px;"  required />명<br/>
						최대 : <input type="number" name="po_max" style="width:80px;"  required />명
					</div>
				</article>
				<article class="option-box">
					<div class="option-subtitle">
						편의시설
					</div>
					<div class="option-content">
						<input type="text" />
						<button type="button" class="add-facility-btn" >편의시설 추가</button>
						<ul id="facility-list">
							
						</ul>
						<input type="hidden" name="facility-count" value="0" />
					</div>
				</article>
				<article class="option-box">
				<div class="option-subtitle">
					썸네일<span class="required-data">필수 사항</span>
				</div>
				<div class="option-content">
					<label>사진 고르기
						<input class="option-thumbnail" id="option-thumbnail" type="file" name="po_img" required />
					</label>
					<div id="option-img-box">
					
					</div>
				</div>
				</article>
				<input type="hidden" name="p_no" value="${placeNo}" />
				<button type="button" class="select-insert-btn" onclick="fn_optionInsert(this.form)" >옵션 추가</button>
				<button type="button" class="select-remove-option-btn" >해당옵션 삭제</button>
				</section>
			</form>
		`;
		if($('form.option-form').length == 0){
			$('div.option-list').append(strHtml);
		} else {
			$('form.option-form').last().after(strHtml);
		}
		fn_addFacility($('.add-facility-btn').last());
		fn_previewThumbnail($('.option-thumbnail').last());
		fn_removeOption();
	});
}
function fn_removeOption(){
	$('.remove-option-btn').click(function(event){
		$(event.target).closest('form').remove();
	})
}

/* 첨부이미지 미리보기 */
function fn_previewThumbnail(btn){
	$(btn).on('change', function(event){
		let appendTarget = $(event.target).parent().next();
		$(appendTarget).empty();
		for (let image of event.target.files) { 
			
			let reader = new FileReader();
		
			reader.onload = function(event) {
				$(appendTarget).append($('<img>').prop('src', event.target.result));
			};
		reader.readAsDataURL(image);
		}
		$(appendTarget).parent().next().val(1);
	});
}