function onLinkedInLoad() {
		IN.Event.on(IN, "auth", onLinkedInAuth);
	}
	
	
	
	
	function onLinkedInAuth() {
		IN.API.Profile("me")
		.fields(["firstName", 
		         "lastName",
		         "specialties", 
		         "positions:(title,company)", 
		         "pictureUrl", 
		         "publicProfileUrl", 
		         "emailAddress", 
		         "summary",
		         "location"
		         ])
		.result(function (result) {
		displayProfileData(result);
		});
	}
	
	
	function displayProfileData(profile) {
		var profile = profile.values[0]; 
		
		$('#firstName').attr('value', profile.firstName);
		$('#lastName').attr('value', profile.lastName);
		$('#title').attr('value', profile.positions.values[0].title); 
		$('#pictureUrl').attr('value', profile.pictureUrl); 
	}