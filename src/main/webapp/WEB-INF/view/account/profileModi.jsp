<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="/resource/js/account.js"></script>

<script type="text/javascript">


$(function(){ 
	
	var attNo = '${user.profilePicPath.attachedFileNo}' ==='' ? '': '${user.profilePicPath.attachedFileNo}';
	$('#preview').css('background-image','url(/profileFile/'+attNo+')' );
	onload(); 
});




function onload() {
  document.querySelectorAll('input').forEach(input => {
	
	input.addEventListener('invalid', () => {
	  
	  showError(input)
	})

  })
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
	
	
	
	if($('#passwordChcek').val()=='false'){
		$('#showError').text("비밀번호 확인이 필요합니다.");
		return;
	}
	
	document.getElementById('submitBtn').click();
}


</script>
<body>

<main class="form-signin">
	<form id="loginForm" name ='loginForm'  method="post" action="/account/profileInfo"  class= "form-floating" enctype="multipart/form-data">

<div class="row mb-3">
<div class="col-md-4 themed-grid-col">
	 <div class="rounded-circle profile"  id="preview"></div>
</div>
<div class="col-md-8 themed-grid-col">
	<input type="hidden" 	name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
	<input type="hidden" 	name="profilePicPath" 	value="${user.profilePicPath.attachedFileNo}"/>
	<input type="submit" id="submitBtn" style="display: none;" > 
	
	
	  <div class="form-floating">
		<input type="email"  name = "email" class="form-control" id="email"  required="required" value='${user.email}' readonly>
		<label for="exampleInputEmail1" class="form-label">Email address</label>
		<div id="emailHelp" class="form-text alert" style="display: none;" ></div>
	  </div>
	  
	  
	<div class="form-floating">
		<input type="text" name="nickname" class="form-control" id="nickname" required="required"  minlength="2" maxlength="8"  value='${user.nickname}' >
		<label for="nickname" class="form-label">nickname</label>
	  </div>

	    <%-- <div class="form-floating">
		<input type="password" name="password" class="form-control" id="password"  required="required"   >
		<label for="exampleInputPassword1" class="form-label" >Password</label>
	  </div>
 		
  	  <div class="form-floating">
		<input type="password" name="passwordChcek" class="form-control" id="passwordCheckInput" onkeyup="checkPassword()"  required="required" >
		<label for="exampleInputPassword1" class="form-label">Password Check</label>
		<input type="hidden"  id = "passwordChcek" value="false">
		<div id="passwordlHelp" class="form-text"></div>
	  </div>
	  --%>
	<div class="form">
		<label> 프로필 사진</label>
	 	<input type="file" class="form-control" placeholder="한줄요약" aria-label="Username" name= "files"  id="fileInput"  onchange="isFileImage(this,'preview')"  accept="image/gif, image/jpeg, image/png">
	</div>
	  
	
	  
  	  <div class="form-floating">
		<div class="alert alert-danger" role="alert" id="showError" style="display: none;"></div>
	  </div>


   	  <button type="button" onclick="memberJoin()" class="btn btn-primary">수정하기</button>
</div>
</div>
   	  
	</form>

	
	
</main>
</body>
</html>