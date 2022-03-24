<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

function takeNotice(method){
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
					<th>제목</th>
				</tr>
				<tr>
					<td>${notice.title}</td>
				</tr>
				<tr>	
					<th>내용</th>
				</tr>	
				<tr>
					<td>${notice.contents}</td>
				</tr>
				<tr>	
					<th>첨부파일</th>
				</tr>
				<tr>
					<td>
				<c:forEach var="file" items="${notice.fileList}" varStatus="status">
					${file.fileName}<br>
				</c:forEach>
					<td>
				</tr>

			</table>
		</div>
		 <div class="row">
			<button type="submit" class="btn btn-primary" onclick="takeNotice('DELETE')" >글삭제</button>
			<button type="button" class="btn btn-primary"  onclick="takeNotice('POST')" >수정</button>
		 </div> 
		<form name="notice" method="post" action="/notice/${notice.noticeNo}">
		    <input type="hidden" name="_method" value=""/>
		</form>
	</div>
</body>
</html>