var IP="http://localhost:8080";
var IsLogin=false;
$(function () {
    $.ajax({
        url: IP+"/json/maxGrade",
        type: "get",
        dataType:"json",
        success:function (data) {
            data=data.year-2000;
            var html='';
            for (var i = 0; i <= data; i++) {
                var x=i+2000;
                if (i<10) {
                    html+="<li><a href='log-maillist.html?grade="+x+"'>0"+i+"届</a></li>";
                }else{
                    html+="<li><a href='log-maillist.html?grade="+x+"'>"+i+"届</a></li>";
                }
            }
            html+="<div class='clear'></div>";
            $('.grade-num').html(html);

        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        },
        complete: function(XMLHttpRequest, textStatus) {
            this; // 调用本次AJAX请求时传递的options参数
        }
    });
    $.ajax({
        url: IP+"/json/hasLogined",
        type: "get",
        dataType:"json",
        success:function (data) {
            console.log(data);
            if (data==true) {
                isLogin=true;
            }
            else{
                isLogin=false;
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        },
        complete: function(XMLHttpRequest, textStatus) {
            this; // 调用本次AJAX请求时传递的options参数
        }
    });

	function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
   	}

   	//接收URL中的参数booksId
	var grade = getUrlParam('grade');
	console.log(grade);

	$.ajax({
		url: IP+"/json/personList/"+grade,
		type: "get",
		dataType:"json",
		success:function (data) {
			console.log(data)
			var html='';
			var html_tit='<span>'+grade+'届 <</span>';
			$('.num-grade').html(html_tit);
			if(isLogin==true){
                for (var i = 0; i < data.length; i++) {
                    // console.log(data[i].name)
                    if(data[i].hasPic==true){
                        html+="<div class='infor'><div class='infor_img'><a href='person_infor.html?person="+data[i].id+"'><img src='../src/pic/people/"+
                            data[i].id+".jpg' class='mail_pic'></a></div><div class='infor1'><span>"+
                            data[i].name+"</span><p>"+data[i].description+
                            "</p></div><div class='infor2'><div class='phone'></div><div class='wechat'></div>"+
                            "<div class='qq'></div><div class='clear'></div></div><div class='infor_phone'>"+
                            "<img src='images/phone.png'><span>"+data[i].phone+"</span></div><div class='infor_wechat'>"+
                            "<span>"+data[i].mail+"</span></div><div class='infor_qq'>"+
                            "<img src='images/qq.png'><span>"+data[i].qq+"</span></div></div>";
                    }else{
                        html+="<div class='infor'><div class='infor_img'><a href='person_infor.html?person="+data[i].id+
                            "'><img src='../src/pic/people/id.jpg' class='mail_pic'></a></div><div class='infor1'><span>"+
                            data[i].name+"</span><p>"+data[i].description+
                            "</p></div><div class='infor2'><div class='phone'></div><div class='wechat'></div>"+
                            "<div class='qq'></div><div class='clear'></div></div><div class='infor_phone'>"+
                            "<img src='images/phone.png'><span>"+data[i].phone+"</span></div><div class='infor_wechat'>"+
                            "<span>"+data[i].mail+"</span></div><div class='infor_qq'>"+
                            "<img src='images/qq.png'><span>"+data[i].qq+"</span></div></div>";
                    }

                }
                $('.wrap_infor').html(html);
                $('.infor').hover(function () {
                    $(this).find('.infor1').css('display','none');
                    $(this).find('.infor2').css('display','block');
                    $(this).find('.infor2').addClass('animated fadeInDown');
                },function () {
                    $(this).find('.infor1').css('display','block');
                    $(this).find('.infor1').addClass('animated fadeInUp');
                    $(this).find('.infor2').css('display','none');
                    $(this).find('.infor_phone').css('display','none');
                    $(this).find('.infor_wechat').css('display','none');
                    $(this).find('.infor_qq').css('display','none');
                })
            }else{
                for (var i = 0; i < data.length; i++) {
                    // console.log(data[i].name)
                    if(data[i].hasPic==true){
                        html+="<div class='infor'><div class='infor_img'><img src='../src/pic/people/"+
                            data[i].id+".jpg' class='mail_pic'></div><div class='infor1'><span>"+
                            data[i].name+"</span><p>"+data[i].description+"</p></div></div>";
                    }else{
                        html+="<div class='infor'><div class='infor_img'><img src='../src/pic/people/id.jpg' class='mail_pic'></div><div class='infor1'><span>"+
                            data[i].name+"</span><p>"+data[i].description+"</p></div></div>";
                    }

                }
                $('.wrap_infor').html(html);
            }

			$('.phone').click(function () {
				$(this).parent().css('display','none');
				$(this).parent().parent().find('.infor_phone').css('display','block');
				$(this).parent().parent().find('.infor_phone').addClass('animated fadeInUp');
			})

			$('.wechat').click(function () {
				$(this).parent().css('display','none');
				$(this).parent().parent().find('.infor_wechat').css('display','block');
				$(this).parent().parent().find('.infor_wechat').addClass('animated fadeInUp');
			})

			$('.qq').click(function () {
				$(this).parent().css('display','none');
				$(this).parent().parent().find('.infor_qq').css('display','block');
				$(this).parent().parent().find('.infor_qq').addClass('animated fadeInUp');
			})
		},
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        },
        complete: function(XMLHttpRequest, textStatus) {
            this; // 调用本次AJAX请求时传递的options参数
        }
	});



})
