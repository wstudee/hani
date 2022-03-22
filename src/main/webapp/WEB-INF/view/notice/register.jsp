<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function register(){
	document.inputForm.action="/notice/register"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}
</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div class="row">
			<div  class="col">
				<form name="inputForm" >
				  <div class="form-group">
				    <label for="exampleFormControlInput1">제목</label>
				    <input type="text" class="form-control" id="title" name= "title">
				  </div>
				  <div class="form-group">
				    <textarea class="form-control" id="file" rows="3" name = "contents"></textarea>
				  </div>
				   <div class="form-group">
				    <label for="exampleFormControlInput1">첨부파일</label>
				    <input type="file" class="form-control" id="file" name= "fileList" multiple>
				  </div>		  
				  
				  
				</form>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="location.href='/notice'">목록으로</button>
			<button type="button" class="btn btn-primary" onclick="register()">등록</button>
		</div>
	</div>
</body>
</html>