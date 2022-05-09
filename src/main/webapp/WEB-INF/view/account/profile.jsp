<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="/resource/js/account.js"></script>

<script type="text/javascript">


$(function(){
	$('#preview').css('background-image','url(/profileFile/'+${user.profilePicPath.attachedFileNo}+')' );
});



</script>
<body>

<main class="form-signin">
	<form id="loginForm" name ='loginForm'  method="post" action="/account/register"  class= "form-floating" enctype="multipart/form-data">

<div class="row mb-3">
<div class="col-md-4 themed-grid-col">
	 <div class="rounded-circle profile"  id="preview"></div>
</div>
<div class="col-md-8 themed-grid-col">
	<input type="hidden"  id = "emailChcek">
	<input type="submit" id="submitBtn" style="display: none;" > 
	
	
	  <div class="form-floating">
		<input type="email"  name = "email" class="form-control" id="email"  required="required" value='${user.email}' readonly>
		<label for="exampleInputEmail1" class="form-label">Email address</label>
	  </div>
	  
	  
	  <div class="form-floating">
		<input type="text" name="nickname" class="form-control" id="nickname" required="required"  minlength="2" maxlength="8"  value='${user.nickname}' readonly>
		<label for="nickname" class="form-label">nickname</label>
	  </div>


   	  <button type="button" onclick="location.href='/account/profileInfo'" class="btn btn-primary">정보수정</button>
   	  <button type="button" onclick="location.href='/account/profileInfo'" class="btn btn-primary">비밀번호 수정</button>
</div>
</div>
   	  
	</form>

	
	
</main>
</body>
</html>