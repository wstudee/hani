<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
</head>
<body>
	회원가입
<div>
	<form:form action="/signup" modelAttribute="userVO" >
		<div class="raw">
			<div>
				아이디 : <form:input path="email"/>
				<form:button type="button" onclick="alert('중복확인')">중복확인</form:button>
				<form:button type="button" onclick="alert('메일인증')" class="mailcheckbutton" style="display:none;">메일인증</form:button>
			</div>
		</div>
		<div>
			비밀번호 : <form:input path="password"/>
		</div>
		

<!-- 		<input path="password"/> -->
<!-- 		<input path="userStatus"/> -->
<!-- 		<input path="loginFailCnt"/> -->
<!-- 		<input path="profilePicPath"/> -->
<!-- 		<input path="nickname"/> -->
<!-- 		<input path="updateUser"/> -->
<!-- 		<input path="updateDate"/> -->
<!-- 		<input path="regDate"/> -->
		<form:button type="submit">회원가입</form:button>
	</form:form>
</div>
</body>
</html>