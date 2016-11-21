// Wait for the DOM to be ready
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='addComputerRegistration']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      computerName: {
    	  required: true,
    	  minlength: 2
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
