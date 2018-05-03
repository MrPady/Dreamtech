$(function () {

	$('.small_pic li').hover(function () {
		$(this).addClass('animated pulse');
	},function () {
		$(this).removeClass('animated pulse');
	})

	$('.pre_intro:odd').css('float','right');

	$('.pic img').hover(function () {
		$(this).addClass('animated pulse');
	},function () {
		$(this).removeClass('animated pulse');
	})

	
	$('.txt_bd li').hover(function() {
		$(this).find('.li_pic').css('background', 'url(images/li_hover.png)');
	}, function() {
		$(this).find('.li_pic').css('background', 'url(images/li.png)');
	});

	$(document).ready(function() {
    	$(".pimg").click(function(){  
            var _this = $(this);//将当前的pimg元素作为_this传入函数  
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
        });
    });
	function imgShow(outerdiv, innerdiv, bigimg, _this){  
	    var id = _this.attr("id");//获取当前点击的pimg元素中的src属性  
	    var src = "../src/pic/galleries/"+id+".jpg";
	    alert(src);
	    $('#bigimg').attr("src", src);//设置#bigimg元素的src属性
	  
	        /*获取当前点击图片的真实大小，并显示弹出层及大图*/  
	    $("<img/>").attr("src", src).load(function(){  
	        var windowW = $(window).width();//获取当前窗口宽度  
	        var windowH = $(window).height();//获取当前窗口高度  
	        var realWidth = this.width;//获取图片真实宽度  
	        var realHeight = this.height;//获取图片真实高度  
	        var imgWidth, imgHeight;  
	        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
	          
	        if(realHeight>windowH*scale) {//判断图片高度  
	            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
	            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
	            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
	                imgWidth = windowW*scale;//再对宽度进行缩放  
	            }  
	        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
	            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
	                        imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
	        } else {//如果图片真实高度和宽度都符合要求，高宽不变  
	            imgWidth = realWidth;  
	            imgHeight = realHeight;  
	        }  
	                $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
	          
	        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
	        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
	        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
	        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
	    });  
	      
	    $(outerdiv).click(function(){//再次点击淡出消失弹出层  
	        $(this).fadeOut("fast");  
    	});  
	} 

	$("#login").click(function () {
	    $(".hide-center").fadeIn("slow");
	    $(".overCurtain").fadeIn("slow");
	})
	$("#close").click(function () {
	    $(".hide-center").fadeOut("slow");
	    $(".overCurtain").fadeOut("slow");
	    // $('#input-topright-loginInput').val('');
	    $('#input-bottomright-loginInput').val('');
	})
})


var IP="http://localhost:8080";

// $(function () {
//     $.ajax({
//         url: IP+"/json/hasLogined",
//         type: "get",
//         dataType:"json",
//         success:function (data) {
//         	console.log(data);
//             if (data==true) {
//                 $('#login').css('display','none');
//                 $('#change-mes').css('display','block');
//                 $('#change-mes').click(function () {
//                     window.location.href='/editPerson';
//                 })
//             }
//         },
//         error: function(XMLHttpRequest, textStatus, errorThrown) {
//             alert(XMLHttpRequest.status);
//             alert(XMLHttpRequest.readyState);
//             alert(textStatus);
//         },
//         complete: function(XMLHttpRequest, textStatus) {
//             this; // 调用本次AJAX请求时传递的options参数
//         }
//     });


//     $('#BSignIn').click(function () {
//         var name=$('#input-topright-loginInput').val();
//         var password=$('#input-bottomright-loginInput').val();
//         if (name==''||password=='') {
//             alert("请输入正确的信息！")
//         }else{
//             $.ajax({
//                 url: IP+"/login",
//                 type: "post",
//                 dataType:"json",
//                 contentType:"application/json",
//                 data: JSON.stringify({
//                     "name":name,
//                     "password":password,
//                 }),
//                 success:function (res) {
//                     console.log(res);
//                     setCookie(name,password,1);
//                     if (res===1) {
//                         // IsLogin=true;
//                         alert("登录成功！");
//                         // window.location.href="index.html";
//                         $(".hide-center").fadeOut("slow");
//                         $(".overCurtain").fadeOut("slow");
//                         // $('#input-topright-loginInput').val('');
//                         $('#input-bottomright-loginInput').val('');
//                         $('#login').css('display','none');
//                         $('#change-mes').css('display','block');
// 						// var user_name=$('#input-topright-loginInput').val();
//                         // alert(user_name)
// 						$('#change-mes').click(function () {
// 							window.location.href='/editPerson';
//                         })
//                     }else{
//                         alert("登录失败！请重新登录。")
//                     }
//                 },
//                 error: function(XMLHttpRequest, textStatus, errorThrown) {
//                     alert(XMLHttpRequest.status);
//                     alert(XMLHttpRequest.readyState);
//                     alert(textStatus);
//                 },
//                 complete: function(XMLHttpRequest, textStatus) {
//                     this; // 调用本次AJAX请求时传递的options参数
//                 }
//             });
//         }
//     })
//     $.ajax({
// 		url: IP+"/json/recruit",
// 		type: "get",
// 		dataType:"json",
// 		success:function (data) {
// 			if (data==true) {
// 				$('.sign_up').css('display','block');
// 			}
// 		},
//         error: function(XMLHttpRequest, textStatus, errorThrown) {
//             alert(XMLHttpRequest.status);
//             alert(XMLHttpRequest.readyState);
//             alert(textStatus);
//         },
//         complete: function(XMLHttpRequest, textStatus) {
//             this; // 调用本次AJAX请求时传递的options参数
//         }
// 	});

//     $.ajax({
//         url: IP+"/json/goodStudents",
//         type: "get",
//         dataType:"json",
//         success:function (data) {
//             console.log(data)
//             var html='';
//             for(var i=0;i<data.length;i++){
//                 if(data[i].hasPic==true){
//                     html+="<li><div class='pic'><a href='javascript:void(0)'><img src='/src/pic/people/"+
//                         data[i].id+".jpg'><div class='clear'></div></a></div><div class='title'><a href='javascript:void(0)'><p class='per_na'>"+
//                         data[i].name+"</p><p class='per_intro'>"+data[i].description+"</p></a><div class='clear'></div></div></li>";
//                 }else{
//                     html+="<li><div class='pic'><a href='javascript:void(0)'><img src='/src/pic/people/id.jpg'><div class='clear'></div></a></div><div class='title'><a href='javascript:void(0)'><p class='per_na'>"+
//                         data[i].name+"</p><p class='per_intro'>"+data[i].description+"</p></a><div class='clear'></div></div></li>";
//                 }

//             }
//             $('.picList').html(html);
//             jQuery(".picScroll-left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:3});
//             $('.picList li:first-child').addClass('wow bounceInLeft');
//             $('.picList li:first-child+li').addClass('wow bounceInDown');
//             $('.picList li:first-child+li+li').addClass('wow bounceInRight');
//         },
//         error: function(XMLHttpRequest, textStatus, errorThrown) {
//             alert(XMLHttpRequest.status);
//             alert(XMLHttpRequest.readyState);
//             alert(textStatus);
//         },
//         complete: function(XMLHttpRequest, textStatus) {
//             this; // 调用本次AJAX请求时传递的options参数
//         }
//     });
// })

