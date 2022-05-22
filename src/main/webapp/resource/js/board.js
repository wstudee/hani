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
	
	
	
	if(document.inputForm.files.value == '' && $('.fileName').length == 0){
		$('.filesErr').show();
		result= false;
	} 
	
	return result;
}

function boardSetting(attNo, color, bordercolor){
	
	$('#preview').css('background-image','url(/boardFile/'+attNo+')' );
	$('#preview div').css('color',color ); 
	$('#preview div').css('-webkit-text-stroke','1px '+bordercolor);
}

function chageInput(){
	
	$('#titleShow').text($('#title').val());
	$('#titleCont').text($('#contents').val());
	
}

function chageColor(e){
	$('#preview div').css('color',e.value)
}

function chageBorderColor(e){
	$('#preview div').css('-webkit-text-stroke','1px '+e.value);
}

