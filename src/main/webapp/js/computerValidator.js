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

$.validator.addMethod(
		"validDate",
		function(value, element) {
			return value.match(/^(((19[7-9]\d)|([2-9]\d{3}))\-\d{2}\-\d{2})?$/);
		},
		"Please enter a date in the format yy-mm-dd."
);

//Wait for the DOM to be ready
$(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form.computerValidatorForm").validate({
		// Specify validation rules
		rules: {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			computerName: {
				required: true,
				minlength: 2
			},
			introduced: {
				required: false,
				validDate: true
			},
			discontinued: {
				required: false,
				validDate: true
			},
			company: "required",

		},
		// Specify validation error messages
		messages: {
			computerName: "Please enter a valid name for the computer (at least 2 letters)",
			company: "Please choose the company which created the computer",
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler: function(form) {
			if ($(form).valid()) 
				form.submit(); 
			return false; // prevent normal form posting
		}
	});
});
