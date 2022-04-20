<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(function(){ 
	boardSetting('${file.attachedFileNo}')
});



function modify(){
	
	if(!checkVal()){
		return;
	}
	
	
	document.inputForm.action="/board/${board.boardNo}"; 
	document.inputForm._method.value="PUT";
	document.inputForm.submit();
}

function fileDelete(attNo){
	
	if(confirm("정말 삭제하시겠습니까?")){
		$.ajax({
		    url: "/board/deleteFile",
		    type: "POST",
		    dataType: "text",
		    data:JSON.stringify ({attachedFileNo : attNo}),
		    contentType:'application/json',
		    success: function(data){
		 		if(data==='SUCCEESS'){
		 			alert("삭제 완료");
		 			$('#file'+attNo).remove();
		 			$('#preview').css('background-image','none' )
		 		}else{
		 			alert("삭제 실패");
		 		}
		    },
		    error: function (request, status, error){        
		    	alert("삭제 실패");
		    }
		});
	}
	
	

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
			<div  class="col">
				<form name="inputForm"  method="post"  enctype="multipart/form-data">
				 <input type="hidden" name = '_method' value = "">
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">오늘의 제목</span>
				  <input type="text" class="form-control" placeholder="제목"  aria-describedby="basic-addon1" id="title" name= "title" maxlength="5" onchange="chageInput()" value ='${board.title}' >
				  <div class="invalid-feedback titleErr"> 제목를 입력해주세요. </div>
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">오늘의 한줄</span>
				  <input type="text" class="form-control" placeholder="한줄요약" aria-label="Username" aria-describedby="basic-addon1"  id="contents" maxlength="15" name = "contents" onchange="chageInput()" value='${board.contents}' > 
					<div class="invalid-feedback contentsErr"> 내용를 입력해주세요. </div>
				</div>
				<div class="input-group mb-3">
					 <span class="input-group-text" id="basic-addon1">글자&nbsp&nbsp&nbsp&nbsp색상</span>
					<input type="color" name="color" class="form-control form-control-color" id="exampleColorInput" value="${board.color}" title="Choose your color" onchange="chageColor(this)">
				</div>
			    <div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">오늘의 사진</span>
				  <input type="file" class="form-control" placeholder="한줄요약" aria-label="Username" aria-describedby="basic-addon1"   name= "files"  id="fileInput"  onchange="isFileImage(this)"  accept="image/gif, image/jpeg, image/png"> 
					<div class="invalid-feedback filesErr"> 파일을 등록해주세요. </div>
				</div>
				<c:if test="${not empty file.attachedFileNo}">
				<div id="file${file.attachedFileNo}">
							<a class = "file" href="/boardFile/${file.attachedFileNo}">${file.fileName}<br></a><button type="button" onclick="fileDelete(${file.attachedFileNo})">X</button>
				</div>	
				</c:if>
				</form>
				<div id='submitErr'></div>
			</div>
		</div>
		
		<div class="row">
			<button type="button" class="btn btn-primary" onclick="location.href='/board/list'">목록으로</button>
			<button type="button" class="btn btn-primary" onclick="modify()">수정</button>
		</div>
	</div>
</body>
</html>