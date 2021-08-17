<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/template/constants.jsp"%>
<script>

$(document).ready(function(){
	boardCtrl.init();
});

var boardCtrl = {
	
	init : function(){
		this.bindData();
	},
	
	bindData : function(){
		goPage(0);
	}
}

//리스트 가져오기
function goPage(pageNum){
	var size = "10";
	
	var params = {
		 page : pageNum
		,size : size
		,searchVal : $.trim($("#searchVal").val())
	}
	
	util.coGetAjaxPage(
      "${ct:url('MAIN.MAIN_LIST_AJAX')}"
      , params
      , 'list-div'
      , function(result){
      }
   );
}

//체크박스 전체 선택 클릭 이벤트
function allChecked(target){
	
	if($(target).is(":checked")){
		//체크박스 전체 체크
		$(".chk").prop("checked", true);
	}
	
	else{
		//체크박스 전체 해제
		$(".chk").prop("checked", false);
	}
}

//자식 체크박스 클릭 이벤트
function cchkClicked(){
	
	//체크박스 전체개수
	var allCount = $("input:checkbox[name=cchk]").length;
	
	//체크된 체크박스 전체개수
	var checkedCount = $("input:checkbox[name=cchk]:checked").length;
	
	//체크박스 전체개수와 체크된 체크박스 전체개수가 같으면 체크박스 전체 체크
	if(allCount == checkedCount){
		$(".chk").prop("checked", true);
	}
	
	//같지않으면 전체 체크박스 해제
	else{
		$("#allCheckBox").prop("checked", false);
	}
}

//게시판 삭제하기
function boardDelete(){
	
	var boardIdxArray = [];
	
	$("input:checkbox[name=cchk]:checked").each(function(){
		boardIdxArray.push($(this).val());
	});
	
	console.log(boardIdxArray);
	
	if(boardIdxArray == ""){
		alert("삭제할 항목을 선택해주세요.");
		return false;
	}
	
	var confirmAlert = confirm('정말로 삭제하시겠습니까?');

	if(confirmAlert){
		
		$.ajax({
	        type : 'POST'
	       ,url : "${ct:url('MAIN.MAIN_DELETE')}"
	       ,dataType : 'json'
	       ,data : JSON.stringify(boardIdxArray)
	       ,contentType: 'application/json'
	       ,success : function(result) {
				alert("해당글이 정상적으로 삭제되었습니다.");
				goPage(0);
	       },
	       error: function(request, status, error) {
	         
	       }
	   })	
	}
}

</script>