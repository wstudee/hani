<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	

function emailDuplicateCheck(){
	
    var formData = $("#loginForm").serialize();

    var resultText = '';
    
    $.ajax({
        cache : false,
        url : "${pageContext.request.contextPath}/account/emailDuplicateCheck", 
        type : 'POST', 
        data : formData, 
        success : function(data) {
        	if(data.result == "success"){
        		resultText = "이미 등록된 아이디입니다.";
        		$('#emailChcek').val(false);
        	}else{
        		resultText =  "사용 가능한 아이디입니다.";
        		$('#emailChcek').val(true);
        	}
        	
        }, // success 
        error : function(xhr, status) {
            alert(xhr + " : " + status);
        },
        complete : function() {
        	$('#emailHelp').text('');
       	    $('#emailHelp').text(resultText);
       	    $('#emailHelp').show();
        }

    });
}

function checkPassword(){
	$('#passwordlHelp').text('');
	if($('#password').val() != $('#passwordCheckInput').val()){
   	    $('#passwordlHelp').text("비밀번호가 일치하지 않습니다.");
   	    $('#passwordlHelp').show();	
   	 	
	}else{
		
	}$('#passwordChcek').val("true")
	
}

function memberJoin(){
	
	if($('#emailChcek').val()=='false' || $('#emailChcek').val() ==''){
		alert("아이디 중복체크 필요")
		return;
	}
	if($('#passwordChcek').val()=='false'){
		alert("비밀번호 확인 필요")
		return;
	}
	
	document.loginForm.submit();
	
}

</script>
<body>
<div class="row justify-content-md-center">
	<form id="loginForm" name ='loginForm'  method="post" action="/account/register">
	<input type="hidden" 	name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
	  <div class="mb-3">
	    <label for="exampleInputEmail1" class="form-label">Email address</label>
	    <input type="email"  name = "email" class="form-control" id="email" >
	    <input type="hidden"  id = "emailChcek">
	     <div id="emailHelp" class="form-text"></div>
	     <button type="button" onclick="emailDuplicateCheck();" class="btn btn-primary">이메일 중복 확인</button>
	     
	  </div>
	  <div class="mb-3">
	    <label for="exampleInputPassword1" class="form-label">Password</label>
	    <input type="password" name="password" class="form-control" id="password">
	  </div>
  	  <div class="mb-3">
	    <label for="exampleInputPassword1" class="form-label">Password Check</label>
	    <input type="password" name="passwordChcek" class="form-control" id="passwordCheckInput" onkeyup="checkPassword()">
	    <input type="hidden"  id = "passwordChcek" value="false">
	    <div id="passwordlHelp" class="form-text"></div>
	  </div>
	  <button type="submit"  class="btn btn-primary">회원가입</button>
	  <!-- <button type="button" onclick="memberJoin()" class="btn btn-primary">회원가입</button> -->
	</form>
</div>
</body>
</html>