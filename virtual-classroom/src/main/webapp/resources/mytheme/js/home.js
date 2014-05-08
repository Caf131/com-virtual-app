/*
 * Brief: This is the JavaScript class for the Home View
 * Author: Chris Forehand
 * Date Started: 4/2014
 * Test Cases: 
 * Major Modifications: None
 * */

$(document).ready(function(){
	
	var ctrlLogin = $("#ctrlLogin"); 
	ctrlLogin.RegistrationControl({
		IsLoginControl: true, 
		DataManager: "http://vcr-env.elasticbeanstalk.com/home/login"
	}); 
	
	// make this so that it's included in a jsp so that we can use c:url to link to a resource
	
	
}); 
