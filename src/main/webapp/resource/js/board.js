/**
 * 
 */


function checkVal(){
	
	var result =true; 
	$('.invalid-feedback').hide();
	if(document.inputForm.title.value == ''){
		$('.titleErr').show();
		result= false;
	} 
	
	if(document.inputForm.contents.value == ''){
		$('.contentsErr').show();
		result= false;
	} 
	
	
	
	if(document.inputForm.files.value == '' && $('.file').length == 0){
		$('.filesErr').show();
		result= false;
	} 
	
	return result;
}

function boardSetting(attNo){
	
	$('#preview').css('background-image','url(/boardFile/'+attNo+')' )
	$('#preview div').css('color','${board.color}' )
	
}

function chageInput(){
	
	$('#titleShow').text($('#title').val());
	$('#titleCont').text($('#contents').val());
	
}

function chageColor(e){
	$('#preview div').css('color',e.value)
	
}

function isFileImage(e){
	var file = document.getElementById('fileInput');
	var ext = file.value.match(/\.([^\.]+)$/)[1];
	switch (ext.toLowerCase()) {
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
		$('#preview').css("background-image","url("+event.target.result+")");
		
	};
	
	reader.readAsDataURL(event.files[0]);
}

