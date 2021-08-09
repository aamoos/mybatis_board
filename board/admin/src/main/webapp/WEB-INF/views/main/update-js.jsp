<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script>
	
	function writeSubmit(){
		
		var params = {
			 boardTitle : $.trim($("#boardTitle").val())
			,boardContent : $.trim($("#boardContent").val())
			,userId : "${sessUserInfo.userId}"
			,boardIdx : $("#boardIdx").val()
		}
		
		console.log(params);
		
		if(params.boardTitle == ""){
			alert("제목을 입력해주세요.");
			return false;
		}
		
		else if(params.boardContent == ""){
			alert("내용을 입력해주세요.");
			return false;
		} 
		
		$.ajax({
	         type : 'POST'
	        ,url : '/board/update'
	        ,dataType : 'json'
	        ,data : JSON.stringify(params) 
	        ,contentType: 'application/json'
	        ,success : function(result) {
				alert("해당글이 정상적으로 수정되었습니다.");
				location.href="/";
	        },
	        error: function(request, status, error) {
	          
	        }
	    }) 
	
	}
	
</script>