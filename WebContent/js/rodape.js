function sticky_footer() { 
	var mFoo = $("footer"); 
	if ((($(document.body).height() + mFoo.outerHeight()) < $(window).height() && mFoo.css("position") == "fixed") || ($(document.body).height() < $(window).height() && mFoo.css("position") != "fixed")) { 
		mFoo.css({ position: "fixed", bottom: "0px" , background: "#138AB9", width: "100%", color: "white" }); 
	} else { 
		mFoo.css({ position: "fixed", bottom: "0px" , background: "#138AB9", width: "100%", color: "white" }); 
	} 
} 

jQuery(document).ready(function($){ 
	sticky_footer(); 
	$(window).scroll(sticky_footer); 
	$(window).resize(sticky_footer); 
	$(window).load(sticky_footer); 
});