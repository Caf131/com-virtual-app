/*
 * Brief: This is the JavaScript class for the Instructor Portal View
 * Author: Chris Forehand
 * Date Started: 4/2014
 * Test Cases: 
 * Major Modifications: None
 * */

$(document).ready(function () {
	
	var btnGetCourseView = $("#getCourseView"); 
	var btnGetUserView = $("#getUserView"); 
	
	var btnGetCourseView_click = function ()
	{
		window.location.href = "http://vcr-env.elasticbeanstalk.com/course/"; 
	}; 
	
	var btnGetUserView_click = function ()
	{
		window.location.href = "http://vcr-env.elasticbeanstalk.com/user"; 
	}; 
	
	btnGetCourseView.click(btnGetCourseView_click); 
	btnGetUserView.click(btnGetUserView_click); 
}); 