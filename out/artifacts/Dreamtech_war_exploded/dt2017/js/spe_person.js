/**
 * Created by Administrator on 2017/11/12 0012.
 */
var IP="http://localhost:8080";

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

//接收URL中的参数booksId
var person_id = getUrlParam('person');
console.log(grade);


$.ajax({
    url: IP+"/json/personDetail"+person_id,
    type: "get",
    dataType:"json",
    success:function (data) {
        var html='';
        html+="<div class='per_pic'><img src='images/"+data[person_id].id+
            ".jpg'></div><div class='per_name'>"+data[person_id].name+"</div><div class='per_cont'>"
                +data[person_id].description+"</div>";
        $('.spe_infor').html(html);
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