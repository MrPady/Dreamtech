var IP="http://localhost:8080";
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
	}

	//接收URL中的参数booksId
var id = getUrlParam('ID');
var spe_id = getUrlParam('Spe');

$.ajax({
	url: IP+"/json/fieldList",
	type: "get",
	dataType:"json",
	success:function (data) {
		console.log(data)
		var html='';
		var _html='';
		var s_html='';
		var ss_html='';
		var arr = new Array();
		for (var i = 0; i < data.length; i++) {
			if (data[i].fatherId==0) {										
				html+="<a id='"+data[i].id+"'>"+data[i].name+"</a>/";
			}
			if (id==data[i].fatherId) {
				_html+="<li><a href='mo_tech.html?ID="+data[i].fatherId+"&Spe="+
				data[i].id+"'>"+data[i].name+"</a></li>";
			}
			if (spe_id==data[i].id) {
				s_html+="<p>"+data[i].description+"</p>";
				ss_html="<span>"+data[i].name+"</span>";
			}

		}	
		$('.tech_name').html(html);
		$('.mid_left ul').html(_html);
		$('.spe_cont').html(s_html);
		$('.spe_name').html(ss_html);
		$('.tech_name a').click(function () {
			// alert(this.id)
			for (var j = 0; j < data.length; j++) {
				if (data[j].fatherId==this.id) {
					arr.push(data[j].id);
				}
			}
			// console.log(arr)
			window.location.href='mo_tech.html?ID='+this.id+'&Spe='
			+Math.min.apply(null, arr);
			// alert(Math.min.apply(null, arr));
			arr=[];
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


$.ajax({
	url: IP+"/json/articleList/"+spe_id,
	type: "get",
	dataType:"json",
	success:function (data) {
		console.log(data)
		var html='';
		var _html='';		
		for (var i = 0; i < data.length; i++) {
            var newDate=data[i].time;
            var d=new Date(newDate);
            var date2=(d.getFullYear())+"/"+(d.getMonth()+1)+"/"+(d.getDate());
			html+="<a href='spe_tech.html?text="+data[i].id+"'><div class='mes_cont'><span>"+data[i].title+
			"</span><p>"+data[i].body+"</p><div class='mes_date'><span>"
			+date2+"</span></div><div class='clear'></div></div></a>"
		}
		$('.mes').html(html);
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

