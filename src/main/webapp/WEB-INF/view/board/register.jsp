<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
$( document ).ready(function() {
	
	var dt = new Date();
	var month = '';
	if(dt.getMonth()+1 < 10){month = "0"+(dt.getMonth()+1);}
	var str = dt.getFullYear()+'-'+month+'-'+dt.getDate();
	$('#date').text(str);
	
});


function register(){

	if(!checkVal()){
		return;
	}
	
	document.inputForm.action="/board"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}




</script>
<body>
	<div class="container">
		<h3>LIST</h3>
		
		<div class="row">
			<div  class="col">
				<div  class="justify-content-md-center">
					<div  id="preview" class="justify-content-md-center">
						<div>
							<p id='date'></p>
							<p id="titleShow"></p>
							<p id="titleCont"></p>
						</div>
					</div>
				</div>
		
			</div>
			<div  class="col">
				<form name="inputForm" enctype="multipart/form-data">
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">오늘의 제목</span>
				  <input type="text" class="form-control" placeholder="제목"  aria-describedby="basic-addon1" id="title" name= "title" maxlength="5" onchange="chageInput()">
				  <div class="invalid-feedback titleErr"> 제목를 입력해주세요. </div>
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">오늘의 한줄</span>
				  <input type="text" class="form-control" placeholder="한줄요약" aria-label="Username" aria-describedby="basic-addon1"  id="contents" maxlength="15" name = "contents" onchange="chageInput()"> 
					<div class="invalid-feedback contentsErr"> 내용를 입력해주세요. </div>
				</div>
				<div class="input-group mb-3">
					 <span class="input-group-text" id="basic-addon1">글자&nbsp&nbsp&nbsp&nbsp색상</span>
					<input type="color" name="color" class="form-control form-control-color" id="exampleColorInput" value="#000000" title="Choose your color" onchange="chageColor(this)">
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">오늘의 사진</span>
				  <input type="file" class="form-control" placeholder="한줄요약" aria-label="Username" aria-describedby="basic-addon1"   name= "files"  id="fileInput"  onchange="isFileImage(this)"  accept="image/gif, image/jpeg, image/png"> 
					<div class="invalid-feedback filesErr"> 파일을 등록해주세요. </div>
				</div>
				</form>
				<div id='submitErr'></div>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">목록으로</button>
			<button type="button" class="btn btn-primary" onclick="register()">등록</button>
		</div>
	</div>
</body>
</html>