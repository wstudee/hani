<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">

function takeBoard(method){
	if(method == 'DELETE'){
		document.board.action = "/board/${board.boardNo}";
	}else if(method == 'POST'){
		document.board.action = "/board/editor/${board.boardNo}";
	}
	
	document.board._method.value = method;
	document.board.submit();
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
					<td>${board.title}</td>
				</tr>
				<tr>	
					<th>내용</th>
				</tr>	
				<tr>
					<td>${board.contents}</td>
				</tr>
				<c:if test="${not empty board.fileList }" >
				<tr>	
					<th>첨부파일</th>
				</tr>
				<tr>
					<td>
				<c:forEach var="file" items="${board.fileList}" varStatus="status">
					<a href="/boardFile/${file.attachedFileNo}">${file.fileName}<br></a>
				</c:forEach>
					
					<td>
				</tr>
				</c:if>
-			</table>
		</div>
		 <div class="row">
			<button type="submit" class="btn btn-primary" onclick="takeBoard('DELETE')" >글삭제</button>
			<button type="button" class="btn btn-primary"  onclick="takeBoard('POST')" >수정</button>
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">목록으로</button>
		 </div> 
		<form name="board" method="post" action="/board/${board.boardNo}">
		    <input type="hidden" name="_method" value=""/>
		</form>
	</div>
</body>
</html>