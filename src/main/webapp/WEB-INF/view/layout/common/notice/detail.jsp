<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

function takeNotice(method){
	if(method == 'DELETE'){
		document.notice.action = "/notice/${notice.noticeNo}";
	}else if(method == 'POST'){
		document.notice.action = "/notice/editor/${notice.noticeNo}";
	}
	
	document.notice._method.value = method;
	document.notice.submit();
}


</script>
<body>
	<div class="container">
		<h3>VIEW</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th>����</th>
				</tr>
				<tr>
					<td>${notice.title}</td>
				</tr>
				<tr>	
					<th>����</th>
				</tr>	
				<tr>
					<td>${notice.contents}</td>
				</tr>
				<c:if test="${not empty notice.fileList }" >
				<tr>	
					<th>÷������</th>
				</tr>
				<tr>
					<td>
				<c:forEach var="file" items="${notice.fileList}" varStatus="status">
					<a href="/noticeFile/${file.attachedFileNo}">${file.fileName}<br></a>
				</c:forEach>
					
					<td>
				</tr>
				</c:if>
-			</table>
		</div>
		 <div class="row">
			<button type="submit" class="btn btn-primary" onclick="takeNotice('DELETE')" >�ۻ���</button>
			<button type="button" class="btn btn-primary"  onclick="takeNotice('POST')" >����</button>
			<button type="button" class="btn btn-primary" onclick="location.href='/notice/list'">�������</button>
		 </div> 
		<form name="notice" method="post" action="/notice/${notice.noticeNo}">
		    <input type="hidden" name="_method" value=""/>
		</form>
	</div>
</body>
</html>