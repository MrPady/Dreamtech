var IP="http://localhost:8080";
var tit = getUrlParam('title');	
$.ajax({
	url: IP+"/json/galleries",
	type: "get",
	dataType:"json",
	success:function (data) {
		// console.log(data)
		var html_tit='';
		var html='';
		var html2='';
		var arr = new Array();
		for (var i = 0; i < data.length; i++) {
			html_tit+="<li><a title='"+i+"' name='"+data[i].id+"'>"+data[i].name+"</a></li>";
		}
        for (var j = 0; j < data[tit].photos.length; j++) {
            html+="<li><img class='pimg' src='../src/pic/smallGalleries/"
                +data[tit].photos[j].id+"_middle.jpg ' id='"+data[tit].photos[j].id+"'></li>"
        }
		html_tit+="<div class='clear'></div>";
		$('.album-na').html(html_tit);
		$('#imgList').html(html);
		$('.album-na a').click(function () {
			// alert(this.title)
			window.location.href='album.html?title='+this.title+'&name='+this.name;
		})
        $(".pimg").click(function(){
            var _this = $(this);//将当前的pimg元素作为_this传入函数
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });
        $('.pic img').hover(function () {
            $(this).addClass('animated pulse');
        },function () {
            $(this).removeClass('animated pulse');
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

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

	//接收URL中的参数booksId
var id = getUrlParam('name');
console.log(id)

$.ajax({
	url: IP+"/json/gallery/"+id,
	type: "get",
	dataType:"json",
	success:function (data) {
		// console.log(data)
		var html="<span>"+data.name+" <</span>";
		$('.num-grade').html(html);

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

function imgShow(outerdiv, innerdiv, bigimg, _this){
    var id = _this.attr("id");//获取当前点击的pimg元素中的src属性
    var src = "../src/pic/galleries/"+id+".jpg";
    // alert(src);
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

	/*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
		// alert(realWidth)
		// alert(realHeight)
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if(realHeight>=windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = (imgHeight/realHeight)*realWidth;//等比例缩放宽度
            if(imgWidth>=windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = (imgWidth/realWidth)*realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        // alert(imgHeight)
		// alert(imgWidth)
        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
        $(bigimg).css("height",imgHeight);//以最终的宽度对图片缩放

        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}