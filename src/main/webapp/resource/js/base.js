
function isFileImage(e,divId, afterFun){
	var file = document.getElementById('fileInput');
	var ext = file.value.match(/\.([^\.]+)$/)[1];
	switch (ext.toLowerCase()) {
	  case 'jpg':
	  case 'jpeg':
	  case 'bmp':
	  case 'png':
	  case 'tif':
		  setThumbnail(e,divId);
		  break;
	  default:
	    alert('이미지 파일만 업로드가 가능합니다.');
	  file.value = '';
	}
	
	if(afterFun!= undefined){
		afterFun();
	}
}





function setThumbnail(event, divId){
	var reader = new FileReader();
	
	reader.onload = function(event){
		$('#'+divId).css("background-image","url("+event.target.result+")");
		
	};
	
	reader.readAsDataURL(event.files[0]);
}
