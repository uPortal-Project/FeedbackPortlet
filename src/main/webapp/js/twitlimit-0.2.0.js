/***********************************************************************************************************
/	TwitLimit v0.2.0
/	by Mike Helmick - http://michaelhelmick.com
/	Last Modification: 7.14.2010
/	Documentation: http://blog.michaelhelmick.com/2010/07/15/twitlimit-jquery-character-limiter/
/	Examples: http://code.michalehelmick.com/twitlimit/


           ,--.       ,--.                            ,--.                ,--.  ,--.                      
,--. ,--.,-|  | ,---. |  |,-.  ,---. ,--.--. ,---.  ,-|  |,--.,--. ,---.,-'  '-.`--' ,---. ,--,--,  ,---. 
 \  '  /' .-. || .-. :|     / | .-. ||  .--'| .-. |' .-. ||  ||  || .--''-.  .-',--.| .-. ||      \(  .-' 
  \   ' \ `-' |\   --.|  \  \ | '-' '|  |   ' '-' '\ `-' |'  ''  '\ `--.  |  |  |  |' '-' '|  ||  |.-'  `)
.-'  /   `---'  `----'`--'`--'|  |-' `--'    `---'  `---'  `----'  `---'  `--'  `--' `---' `--''--'`----' 
`---'                         `--'                                                                        


/	Licensed under both MIT and GPL licenses:
/	http://www.opensource.org/licenses/mit-license.php
/	http://www.gnu.org/licenses/gpl.html

/	Thanks jQuery for making javascript simple! :)
/	Copyright 2010 Mike Helmick
**********************************************************************************************************/

(function($) {
	
	$.fn.twitLimit = function(options) {
		
		$.fn.twitLimit.defaults = {
			limit: 140,								// Default limit, if no limit set upon selector. (In honor of Twitter <3)
			message: '%1 Characters Remaining.',	// Keep %1 intact when defining custom message. (%1 is replaced by number of characters left.)
			counterElem: '#limit', 					// ID or Class of element where you want the message to display.
			allowNegative: false,					// Whether to allow the count to go negative or not.
			dangerMode: true,						// Will change the number of characters left "%1" with the colors defined in dangerColors.
			dangerBold: true,						// Will make the number of characters left "%1" bold.
			dangerColors: {dark:'550505',			// Colors for dangerMode.
						   medium:'980808',			// Colors for dangerMode.
						   bright:'e90909'},		// Colors for dangerMode.
			onNegative: function(){},				// Called when character limit is less than zero.
			onPositive: function(){}				// Called for when character limit is greater than or equal to zero.
		};
			
		var o = $.extend({}, $.fn.twitLimit.defaults, options);
		
		o.limit = Math.abs(o.limit);
		
		function danger(count) {
			var $temp = $('<p />');
			
			if(count <= 20 && count >= 10)		var $count = $('<span />').attr('class', 'twitLimit_count').text(count).css({'color':'#'+o.dangerColors.dark});
			else if(count < 10 && count > 0)	var $count = $('<span />').attr('class', 'twitLimit_count').text(count).css({'color':'#'+o.dangerColors.medium});
			else if(count <= 0)					var $count = $('<span />').attr('class', 'twitLimit_count').text(count).css({'color':'#'+o.dangerColors.bright});
			else								return count
				
			if(o.dangerBold == true)	$count.css({'font-weight':'bold'});
			if(count <= 20)				$temp.append($count); $count = $temp.html();
			
			return $count;
		}
		
		function count(elem, counterElem) {
			elem = $(elem);
			counterElem = $(counterElem);
			
			if(o.allowNegative === false)	if(elem.val().length > o.limit)		elem.val(elem.val().substr(0, o.limit));
			
			if(o.dangerMode == true)		counterElem.html(o.message.replace(/%1/, danger((o.limit - elem.val().length))));
			else							counterElem.html(o.message.replace(/%1/, (o.limit - elem.val().length)));
			
			if(o.limit-elem.val().length < 0)		o.onNegative(elem, counterElem);
			if(o.limit-elem.val().length >= 0)		o.onPositive(elem, counterElem);
		}
	
		return this.each(function(e) {
			var toCheck = $(this);
			
			// Check limit upon each of the following events.
			toCheck
				.bind("keydown keypress keyup change focus mouseout mouseover paste", function(e) {
					count(toCheck, o.counterElem); 
				});
			
			if (toCheck.addEventListener) toCheck.addEventListener('input', function () { count(toCheck, o.counterElem); }, false);	
			count(toCheck, o.counterElem);
		});
		
	}
	
})(jQuery);