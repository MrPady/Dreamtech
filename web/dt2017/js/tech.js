

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
	}

	//接收URL中的参数booksId
var id = getUrlParam('ID');

$.ajax({
	url: IP+"/json/fieldList",
	type: "get",
	dataType:"json",
	success:function (data) {
		console.log(data)	
		var html='';
		var _html="";
		for (var n = 0; n < data.length; n++) {
			var father=data[n].fatherId;
			if (father==0) {									
				html+="<li><a href='tech.html?ID="+data[n].id+"'>"+data[n].name+"</a><ul>";
				for (var j = 0; j < data.length; j++) {
					if (data[n].id==data[j].fatherId) {
						html+="<li><a href='javascript:void(0)'>"+data[j].name+"</a></li>";
					}
				}
				html+="</ul></li>";
			}
		}			
		for (var i = 0; i < data.length; i++) {			
			if(id==data[i].id){
				$('.front_tit').html(data[i].name);
			}
		}
		for (var m = 0; m < data.length; m++) {
			var father=data[m].fatherId;
			if (id==data[m].fatherId) {
				_html+="<div class='template'><span>【"+data[m].name+
				"】</span><div class='tem_img'><img src='../src/pic/"+data[m].id
				+".jpg'></div><p>"+data[m].description+"</p><input onclick='window.location.href=\"mo_tech.html?ID="
				+data[m].fatherId+"&Spe="+data[m].id+"\"' type='button' value='更多>'></div>";
				
			}
			$('.template p').each(function(){
				var maxwidth=35;
				if($(this).text().length>maxwidth){
					$(this).text($(this).text().substring(0,maxwidth));
					$(this).html($(this).html()+'…');
				}
			});
		}
		html+="<div class='clear'></div>";
		_html+="<div class='clear'></div>";
		$('.field-ul').html(html);
		$('.front_cont').html(_html);
		$('.tem_img').hover(function () {
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

