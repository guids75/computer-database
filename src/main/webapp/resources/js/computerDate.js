jQuery(document).ready(function($) {
	$("#introduced").datepicker({ 
		dateFormat: 'yy-mm-dd',
		minDate : new Date(1970,1,-1,-1)
	});
});

jQuery(document).ready(function($) {
	$("#discontinued").datepicker({ 
		dateFormat: 'yy-mm-dd',
		minDate : new Date(1970,1,-1,-1)
	});
});