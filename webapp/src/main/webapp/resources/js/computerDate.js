jQuery(document).ready(function($) {
	$("#introduced").datepicker({ 
		dateFormat: messages['label.datePattern'],
		changeMonth : true,
		changeYear : true,
		minDate : new Date(1970,1,-1,-1)
	});
});

jQuery(document).ready(function($) {
	$("#discontinued").datepicker({ 
		dateFormat: messages['label.datePattern'],
		changeMonth : true,
		changeYear : true,
		minDate : new Date(1970,1,-1,-1)
	});
});