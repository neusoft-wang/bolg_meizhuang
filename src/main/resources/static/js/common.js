define('shop_web/common', function(require, exports, module){
	// var $ = require('shop_web/lib/jquery');
	var page = {};
	var gap = 0.5 * ($('body').width() - 1200);
	page.initEvent = function(flag){
		if(flag === 'lateScroll'){
			gap = gap - 7.5;
		}
		leftREDerCss();
		backTopCss();
		backTopEvent();
	};
	page.remainHours = function(compare_time){
		var result;
    	//时间差的毫秒数
    	var milliseconds = (new Date(compare_time)).getTime()-(new Date()).getTime();
    	if(milliseconds>=0){
	    	var days = milliseconds/(24*3600*1000);  
			if(days<1){
				var hours = days * 24;
				if(hours<1){
					result = '<span>' + Math.ceil(hours * 60) + ' </span>' + ' 分钟';
				}
				else{
					result = '<span>' + Math.floor(hours) + ' </span>' + ' 小时 ';
				}
			}
			else{
				result = '<span>' + Math.floor(days) + '</span>' + ' 天 ' + '<span>' + Math.ceil((days - Math.floor(days)) * 24) + '</span>' + ' 小时';
			}
    	}
    	return result; 
	};
	page.loadPicture = function(a,b){
		var $img = $(a);
		var img_url = $img.attr("data-url");
		var newImg = new Image();
		newImg.src = img_url;
		newImg.onload = function(){
			$img.attr("src", img_url);
			if(b){
				$img.removeClass(b);
			}
		};
	};
	page.subFloat = function(a){
		if(a){
			return a.substring(0,a.indexOf('.'));
		}
		return "";
	};
	function leftREDerCss(){
		var img = {
			width: 110,
			height: 190
		};
		var w = gap-20-110;
		var $leftREDer = $("#leftREDer");
		if(w<0){
			$leftREDer.css("left", "-80px").show();
			$leftREDer.hover(function(){
				$leftREDer.animate({
					"left": "0px"
				},100);
			},function(){
				$leftREDer.animate({
					"left": "-80px"
				},100);
			});
		}
		else{
			$leftREDer.css("left", gap-20-110 + "px").show();
		}
		
	}
	function backTopCss(){
		var img = {
			width: 36,
			height: 40
		};
		if(gap>=66){
			$("#backTop").css("right", gap-66 + "px");
		}else if(gap>=46){
			$("#backTop").css("right", gap-46 + "px");
		}
	}
	function backTopEvent(){
		var $backTop = $("#backTop");
		//只要窗口滚动,就触发下面代码 
		$(window).scroll(function(){
			//获取滚动后的高度 
			var scrollt = document.documentElement.scrollTop + document.body.scrollTop; 
			//判断滚动后高度超过500px,就显示  
			if(scrollt>500){ 
				$backTop.fadeIn(400);      
			}
			else{
				//如果返回或者没有超过,就淡入.必须加上stop()停止之前动画,否则会出现闪动
				$backTop.stop().fadeOut(400);
			}
		});

		//当点击标签的时候,使用animate在200毫秒的时间内,滚到顶部
		$backTop.click(function(){ 
			$("html,body").animate({
				scrollTop: "0px"
			},200);
		});
	};
	return page;
});