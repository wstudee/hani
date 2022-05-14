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

function boardSetting(attNo, color){
	
	$('#preview').css('background-image','url(/boardFile/'+attNo+')' );
	$('#preview div').css('color',color ); 
	
}

function chageInput(){
	
	$('#titleShow').text($('#title').val());
	$('#titleCont').text($('#contents').val());
	
}

function chageColor(e){
	$('#preview div').css('color',e.value)
	
}

