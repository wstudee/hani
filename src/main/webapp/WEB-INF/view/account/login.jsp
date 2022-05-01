<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript">
	

function login(){
	
    var formData = $("#loginForm").serialize();

    $.ajax({
        cache : false,
        url : "${pageContext.request.contextPath}/account/login", 
        type : 'POST', 
        data : formData, 
        dataType : 'json',
        success : function(data) {
        	if(data.result == "success"){
        		window.location.href = '${pageContext.request.contextPath}/board/list';
        	}else{
        		alert("�α��� ����")
        	}
        	
        }, // success 
        error : function(xhr, status) {
            alert(xhr + " : " + status);
        }
    });
	
}

</script>
<body>
<sec:authorize access="isAnonymous()">
�α���������
</sec:authorize>
<sec:authorize access="isAuthenticated()">
�α��� ����
</sec:authorize>

<main class="form-signin">
  <form id="loginForm" action="${pageContext.request.contextPath}/account/login" method="post">
    <input type="hidden" 	name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
    
    <div class="form-floating">
      <input type="email"  name="username"  class="form-control" id="floatingInput" placeholder="name@example.com">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">Password</label>
    </div>

    <c:if test="${param.error eq ''}">
    <div class="alert alert-danger" role="alert">�α��� ����</div> 
    </c:if>
    <c:if test="${param.logout eq ''}">
    <div class="alert" role="alert">�α��� ����</div> 
    </c:if>
<!-- 
    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div> -->
    <button class="w-100 btn btn-lg btn-primary" type="submit">�α���</button>
  </form>
  <button type="button" onclick="location.href='${pageContext.request.contextPath}/account/register'"class="w-100 btn btn-lg btn-primary" >ȸ������</button>
</main>

</body>
</html>