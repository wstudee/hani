<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>
</head>
<body>
	ȸ������
<div>
	<form:form action="/signup" modelAttribute="userVO" >
		<div class="raw">
			<div>
				���̵� : <form:input path="email"/>
				<form:button type="button" onclick="alert('�ߺ�Ȯ��')">�ߺ�Ȯ��</form:button>
				<form:button type="button" onclick="alert('��������')" class="mailcheckbutton" style="display:none;">��������</form:button>
			</div>
		</div>
		<div>
			��й�ȣ : <form:input path="password"/>
		</div>
		

<!-- 		<input path="password"/> -->
<!-- 		<input path="userStatus"/> -->
<!-- 		<input path="loginFailCnt"/> -->
<!-- 		<input path="profilePicPath"/> -->
<!-- 		<input path="nickname"/> -->
<!-- 		<input path="updateUser"/> -->
<!-- 		<input path="updateDate"/> -->
<!-- 		<input path="regDate"/> -->
		<form:button type="submit">ȸ������</form:button>
	</form:form>
</div>
</body>
</html>