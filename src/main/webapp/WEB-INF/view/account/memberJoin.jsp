<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="/resource/js/account.js"></script>

<script type="text/javascript">


$(function(){ 
	onload(); 
});




function onload() {
  document.querySelectorAll('input').forEach(input => {
	
	input.addEventListener('invalid', () => {
	  
	  showError(input)
	})

  })
}


function emailDuplicateCheck(){
	
	var formData = $("#loginForm").serialize();

	var resultText = '';
	var className = '';
	$.ajax({
		cache : false,
		url : "${pageContext.request.contextPath}/account/emailDuplicateCheck", 
		type : 'POST', 
		data : $('#email').val() , 
		dataType : 'json',
		contentType:"application/json", 
		success : function(data) {
			if(data.result == "fail"){
				resultText = "이미 등록된 아이디입니다.";
				className = "alert-danger";
				$('#emailChcek').val(false);
			}else{
				resultText =  "사용 가능한 아이디입니다.";
				className = "alert-success";
				$('#emailChcek').val(true);
			}
			
		}, // success 
		error : function(xhr, status) {
			alert(xhr + " : " + status);
		},
		complete : function() {
			$('#emailHelp').text('');
	   		$('#emailHelp').text(resultText);
	   	 	$('#emailHelp').removeClass()
	   	 	$('#emailHelp').addClass("form-text alert "+className);
			
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
	
	$('#showError').show();
	
	if($('#emailChcek').val()=='false' || $('#emailChcek').val() ==''){
		$('#showError').text("아이디 중복체크 필요합니다.");
		return;
	}
	
	
	if($('#passwordChcek').val()=='false'){
		$('#showError').text("비밀번호 확인이 필요합니다.");
		return;
	}
	
	document.getElementById('submitBtn').click();
}


</script>
<body>


<main class="form-signin">
	<form id="loginForm" name ='loginForm'  method="post" action="/account/register"  class= "form-floating">
	<input type="hidden" 	name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
	<input type="hidden"  id = "emailChcek">
	<input type="submit" id="submitBtn" style="display: none;" > 
	
	
	  <div class="form-floating">
		<input type="email"  name = "email" class="form-control" id="email"  required="required">
		<label for="exampleInputEmail1" class="form-label">Email address</label>
		<button type="button" onclick="emailDuplicateCheck();" class="btn btn-primary">이메일 중복 확인</button>
		<div id="emailHelp" class="form-text alert" style="display: none;" ></div>
	  </div>
	  
	  
	  <div class="form-floating">
		<input type="text" name="nickname" class="form-control" id="nickname" required="required"  minlength="4" maxlength="8" >
		<label for="nickname" class="form-label">nickname</label>
	  </div>

	  <div class="form-floating">
		<input type="password" name="password" class="form-control" id="password"  required="required" >
		<label for="exampleInputPassword1" class="form-label" >Password</label>
	  </div>

  	  <div class="form-floating">
		<input type="password" name="passwordChcek" class="form-control" id="passwordCheckInput" onkeyup="checkPassword()"  required="required" >
		<label for="exampleInputPassword1" class="form-label">Password Check</label>
		<input type="hidden"  id = "passwordChcek" value="false">
		<div id="passwordlHelp" class="form-text"></div>
	  </div>
  	  <div class="form-floating">
		<div class="alert alert-danger" role="alert" id="showError" style="display: none;"></div>
	  </div>

   	  <button type="button" onclick="memberJoin()" class="btn btn-primary">회원가입</button>
	</form>

	
	
</main>
</body>
</html>