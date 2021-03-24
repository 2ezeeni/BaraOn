<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	if(${canDelete == 0}) { //진행중인 모임이 있으면
		alert('진행중인 모임이 있습니다. 확인하시고 다시 시도해주세요.');
		location.href = 'infoDeletePage.myPage?grade=${grade}';
	} else if(${canDelete == 1}) { //예약한 장소가 있으면
		alert('예약한 장소가 있습니다. 확인하시고 다시 시도해주세요.');
		location.href = 'infoDeletePage.myPage?grade=${grade}';
	} else if(${canDelete == 2}) { //예약된 장소가 있으면
		alert('예약된 장소가 있습니다. 확인하시고 다시 시도해주세요.');
		location.href = 'infoDeletePage.myPage?grade=${grade}';
	} 
	
	if(${result == 1}) {
		alert('탈퇴처리 되었습니다.');
		location.href = 'index';
	}
	
</script>