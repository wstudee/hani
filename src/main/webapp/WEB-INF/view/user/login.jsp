<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	

function login(){
	
    var formData = $("#loginForm").serialize();

    $.ajax({
        cache : false,
        url : "${pageContext.request.contextPath}/user/login", 
        type : 'POST', 
        data : formData, 
        dataType : 'json',
        success : function(data) {
        	if(data.result == "success"){
        		window.location.href = '${pageContext.request.contextPath}/board/list';
        	}else{
        		alert("로그인 실패")
        	}
        	
        }, // success 
        error : function(xhr, status) {
            alert(xhr + " : " + status);
        }
    });
	
}

</script>
<body>
<div class="row justify-content-md-center">
	<form id="loginForm">
	  <div class="mb-3">
	    <label for="exampleInputEmail1" class="form-label">Email address</label>
	    <input type="email"  name = "email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
	    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
	  </div>
	  <div class="mb-3">
	    <label for="exampleInputPassword1" class="form-label">Password</label>
	    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
	  </div>
	  <div class="mb-3 form-check">
	    <input type="checkbox" class="form-check-input" id="exampleCheck1">
	    <label class="form-check-label" for="exampleCheck1">Check me out</label>
	  </div>
	  <button type="button" onclick="login();" class="btn btn-primary">로그인</button>
	  <button type="button" onclick="location.href='${pageContext.request.contextPath}/user/memberJoin'" class="btn btn-primary">회원가입</button>
	</form>
</div>
</body>
</html>