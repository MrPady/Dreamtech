
var IP="http://localhost:8080";
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

//接收URL中的参数booksId
var text_id = getUrlParam('text');
    $.ajax({
        url: IP+"/json/articleDetails/"+text_id,
        type: "get",
        dataType:"json",
        success:function (data) {
            console.log(data)
            var html='';
            var _html='';
            var newDate=data.time;
            var d=new Date(newDate);
            var date2=(d.getFullYear())+"/"+(d.getMonth()+1)+"/"+(d.getDate());
            html+="<span class='tech-na-tit'>"+data.title+
                "</span><span class='tech-na-date'>"+date2+"</span> ";
            _html+="<div class='tech-turn'><a href='#'>后一篇</a><span>|</span>"+
                "<a href='#'>前一篇</a></div><div class='article'>"+data.body+
                "</div><div class='art-end'><p>- End -</p></div></div>";
            $('.tech-name').html(html);
            $('.tech-cont').html(_html);
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