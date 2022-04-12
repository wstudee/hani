<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function register(){

	document.inputForm.action="/board"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}

function isFileImage(){
	var file = document.getElementById('fileInput');
	var ext = file.value.match(/\.([^\.]+)$/)[1];
	switch (ext) {
	  case 'jpg':
	  case 'jpeg':
	  case 'bmp':
	  case 'png':
	  case 'tif':
	    break;
	  default:
	    alert('이미지 파일만 업로드가 가능합니다.');
	  file.value = '';
	}
}
</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		<div class="row">
			<div  class="col">
				<form name="inputForm" enctype="multipart/form-data">

				  <div class="form-group">
				    <label for="exampleFormControlInput1">제목</label>
				    <input type="text" class="form-control" id="title" name= "title">
				  </div>
				  <div class="form-group">
				    <textarea class="form-control" id="file" rows="3" name = "contents"></textarea>
				  </div>
				   <div class="form-group">
				    <label for="exampleFormControlInput1">첨부파일</label>
				    <input type="file" class="form-control" name= "files"  id="fileInput"  onchange="isFileImage()"  accept="image/gif, image/jpeg, image/png">
				  </div>		   
				</form>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">목록으로</button>
			<button type="button" class="btn btn-primary" onclick="register()">등록</button>
		</div>
	</div>
</body>
</html>