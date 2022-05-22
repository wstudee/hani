<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript">
$(function(){ 
	boardSetting('${file.attachedFileNo}', '${board.textcolor}', '${board.bordercolor}');
});



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
			<div  class="col">
				<div  class="justify-content-md-center">
					<div  id="preview" class="justify-content-md-center">
						<div>
							<p id='date'>
								<fmt:parseDate value="${ board.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<fmt:formatDate pattern="yyyy-MM-dd" value="${ parsedDateTime }" />
							</p>
							<p id="titleShow">${board.title}</p>
							<p id="titleCont">${board.contents}</p>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			<sec:authorize access="isAuthenticated()">
			   <sec:authentication property="principal" var="principal" />
			   <c:if test="${principal.username eq board.regUser.email}" >
				<button type="submit" class="btn btn-primary" onclick="takeBoard('DELETE')" >글삭제</button>
				<button type="button" class="btn btn-primary"  onclick="takeBoard('POST')" >수정</button>
				</c:if>
			</sec:authorize>
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">목록으로</button>
			</div>
		</div>
		<form name="board" method="post" action="/board/${board.boardNo}">
			<input type="hidden" name="_method" value="" />
		</form>
</body>
</html>