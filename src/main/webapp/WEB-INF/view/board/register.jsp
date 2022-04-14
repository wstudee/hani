<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>

.textBox{
	height: 500px;
    width: 500px;
    position: relative;
    text-align: center;
	background-image: url(/boardFile/${file.attachedFileNo});
    
}
.textBox p{
    vertical-align: middle;
    top: 50%;
    left: 50%;
    position: absolute;
}
.input-div{
    vertical-align: middle;
 }
</style>
<script type="text/javascript">
$( document ).ready(function() {
	$("#contents").on("propertychange change keyup paste input",function(){
	    $(this)[0].style.height='auto';
	    $(this).height( $(this).prop('scrollHeight'));     
	 });
	 
	 
});





function register(){

	document.inputForm.action="/board"; 
	document.inputForm.method="post";
	document.inputForm.submit();
}

function isFileImage(e){
	var file = document.getElementById('fileInput');
	var ext = file.value.match(/\.([^\.]+)$/)[1];
	switch (ext) {
	  case 'jpg':
	  case 'jpeg':
	  case 'bmp':
	  case 'png':
	  case 'tif':
		  setThumbnail(e);
		  break;
	  default:
	    alert('이미지 파일만 업로드가 가능합니다.');
	  file.value = '';
	}
}


function setThumbnail(event){
	var reader = new FileReader();
	
	reader.onload = function(event){
		$('#contents').css("background-image","url("+event.target.result+")");
		
	};
	
	reader.readAsDataURL(event.files[0]);
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
				  <div class="form-group input-div">
				  	 <div>
				    <textarea class="form-control textBox" id="contents"  name = "contents" maxlength="40" rows="15"  spellcheck="false"></textarea>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="exampleFormControlInput1">첨부파일</label>
				    <input type="file" class="form-control" name= "files"  id="fileInput"  onchange="isFileImage(this)"  accept="image/gif, image/jpeg, image/png">
				  </div>	
				  <div id='image_container'></div>	   
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