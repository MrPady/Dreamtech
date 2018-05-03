var IP="http://localhost:8080";
$(function () {
	$.ajax({
		url: IP+"/json/bills",
		type: "get",
		dataType:"json",
		success:function (data) {
			console.log(data[0].time)
			var html='';
			for (var i = 0; i < data.length; i++) {
				var newDate=data[i].time;
				var d=new Date(newDate);
				var date=(d.getFullYear())+"/"+(d.getMonth()+1)+"/"+(d.getDate());
				html+="<li><div class='li_pic'></div><a href='javascript:void(0)'>"
					+data[i].detail+"/ ￥"+data[i].money+"<span>"+date+"</span></a></li>";
			}
			$('.infoList').html(html);
			jQuery(".txtScroll-top").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:4});
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