<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script>

	//로그인 submit
	function join(){
		
		var params = {
			  'userId' : $("#userId").val()
			 ,'password' : $("#password").val()
			 ,'userName' : $("#userName").val()
			 ,'email' : $("#email").val()
			 ,'handPhoneNo' : $("#handPhoneNo").val() 
			 
		}
		
		console.log(params);
		
		$.ajax({
	         type : 'POST'
	        ,url : '/auth/insertUser'
	        ,dataType : 'json'
	        ,data : params 
	        ,success : function(result) {
	        	alert(result.resultMsg);
	        	if(result.resultCode == '00'){
	        		location.href="/auth/login";
	        	}
	        },
	        error: function(request, status, error) {
	          
	        }
	    }) 
	}
</script>