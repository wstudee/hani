<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" type="text/css" href="/resource/css/user.css">
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
<main class="form-signin">
  <form id="loginForm" action="${pageContext.request.contextPath}/user/login" method="post">
    <input type="hidden" 	name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
    <h2>${logout}</h2>
    <div class="form-floating">
      <label for="floatingInput">Email address</label>
      <input type="email"  name="username"  class="form-control" id="floatingInput" placeholder="name@example.com">
    </div>
    <div class="form-floating">
      <label for="floatingPassword">Password</label>
      <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
    </div>

    <c:if test="${param.error eq ''}">
    <div class="alert alert-danger" role="alert">로그인 실패</div> 
    </c:if>
<!-- 
    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div> -->
    <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
  </form>
  <button type="button" onclick="location.href='${pageContext.request.contextPath}/user/memberJoin'"class="w-100 btn btn-lg btn-primary" >회원가입</button>
</main>

</body>
</html>