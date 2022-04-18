<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$(function(){ 
	boardSetting()
});


function boardSetting(){
	
	$('.textBox').css('background-image','url(/boardFile/${file.attachedFileNo})' )
	$('.textBox div').css('color','${board.color}' )
	
}


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
		<div class="row ">
			<div class='textBox'>
				<div>
					<p id="regDate">
						<fmt:parseDate value="${ board.regDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
						<fmt:formatDate pattern="yyyy-MM-dd" value="${ parsedDateTime }" />
					</p>
					<p id="title">${board.title}</p>
					<p id="contents">${board.contents}</p>
				</div>
			</div>
		</div>
		<div class="row">
			<button type="submit" class="btn btn-primary" onclick="takeBoard('DELETE')" >글삭제</button>
			<button type="button" class="btn btn-primary"  onclick="takeBoard('POST')" >수정</button>
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">목록으로</button>
		</div>
		<form name="board" method="post" action="/board/${board.boardNo}">
			<input type="hidden" name="_method" value="" />
		</form>
	</div>
</body>
</html>