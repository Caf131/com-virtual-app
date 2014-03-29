/**
 * 
 */
$(document).ready(function(){
	
	var ctrlLogin = $("#ctrlLogin"); 
	ctrlLogin.RegistrationControl({
		IsLoginControl: true
	}); 
	
	var subMenu = $("#sub-menu").hide(); 
	var myAccountNavItem = $("#myAccount"); 	
	
	var myAccountNavItem_enter = function ()
	{
		subMenu.show(); 
	}; 
	
	var myAccountNavItem_leave = function()
	{
		clearTimeout(); 
		setTimeout(function (){
			
			if(!subMenu.hasClass("visible"))
			{
				subMenu.hide(); 
			}		
			
		}, 2000); 
	}; 
	
	var subMenu_enter = function ()
	{
		subMenu.addClass("visible"); 
		subMenu.show(); 
	}; 
	
	var subMenu_leave = function()
	{
		subMenu.removeClass("visible"); 
		subMenu.hide(); 
	}; 
	
	
	myAccountNavItem.mouseenter(myAccountNavItem_enter); 
	myAccountNavItem.mouseleave(myAccountNavItem_leave); 
	subMenu.mouseenter(subMenu_enter); 
	subMenu.mouseleave(subMenu_leave);
	
}); 
