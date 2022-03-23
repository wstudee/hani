<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function modify(){
	document.inputForm.action="/notice/${notice.noticeNo}"; 
	document.inputForm._method.value="PUT";
	document.inputForm.submit();
}
</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div class="row">
			<div  class="col">
				<form name="inputForm"  method="post">
				  <div class="form-group">
				    <label for="exampleFormControlInput1">제목</label>
				    <input type="text" class="form-control" id="title" name= "title" value ='${notice.title}' >
				  </div>
				  <div class="form-group">
				    <textarea class="form-control" id="contents" rows="3" name = "contents">${notice.contents}</textarea>
				  </div>
				  <input type="hidden" name = '_method' value = "">
				</form>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="location.href='/notice'">목록으로</button>
			<button type="button" class="btn btn-primary" onclick="modify()">수정</button>
		</div>
	</div>
</body>
</html>