<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script>

$(document).ready(function(){
	boardCtrl.init();
});

var boardCtrl = {
	
	init : function(){
		this.bindData();
		this.bindEvent();
	},
	
	bindData : function(){
		goPage(0);
	},
	
	bindEvent : function(){
	
	}
}

//리스트 가져오기
function goPage(pageNum){
	var size = "10";
	
	var params = {
		 page : pageNum
		,size : size
	}
	
	util.coGetAjaxPage(
      "/main/ajax/list-view"
      , params
      , 'list-div'
      , function(result){
      }
   );
}

</script>