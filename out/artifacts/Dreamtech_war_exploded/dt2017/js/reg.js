$(document).ready(function() {
    // Generate a simple captcha
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

    $('#defaultForm').formValidation({
        message: 'This value is not valid',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        message: '姓名不能少于两个字'
                    }
                }
            },
            username: {
                message: '专业班级不能为空',
                validators: {
                    notEmpty: {
                        message: '专业班级不能为空'
                    }
                }
            },
            stunum: {
                message: '学号不能为空',
                validators: {
                    notEmpty: {
                        message: '学号不能为空'
                    }
                }
            },
            phone: {
                message: '手机号不能为空',
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    }
                }
            },
            qq: {
                message: 'qq号不能为空',
                validators: {
                    notEmpty: {
                        message: 'qq号不能为空'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '电子邮件不能为空'
                    },
                    emailAddress: {
                        message: '请输入有效的电子邮件地址'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能和用户名相同'
                    }
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: '必须选择一个性别'
                    }
                }
            },
            direct: {
                validators: {
                    notEmpty: {
                        message: '必须选择一个方向'
                    }
                }
            },
            captcha: {
                validators: {
                    callback: {
                        message: 'Wrong answer',
                        callback: function(value, validator, $field) {
                            var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
                            return value == sum;
                        }
                    }
                }
            },
            agree: {
                validators: {
                    notEmpty: {
                        message: '确认提交报名吗？'
                    }
                }
            }
        }
    });
});
$(function () {
    var IP="http://localhost:8080";
	$("#confirm").click(function () {
		var name=$('.input-name').val();
        var gender=$("input[name='gender']:checked").val();
        var iclass=$('.input-class').val();
        var stunum=$('.input-stu').val();
        var phone=$('.input-phone').val();
        var qq=$('.input-qq').val();
        var direct=$("input[name='direct']:checked").val();
        var text=$('.form-textarea').val();
        var flag=true;
        if (gender=="female") {
            flag=true;
        }
        else if(gender=="male"){
            flag=false;
        }
        console.log(stunum)
        console.log(gender)
    	$.ajax({
    		url: IP+"/addNewStudent",
    		type: "post",
    		dataType:"json",
            contentType:"application/json",
    		data: JSON.stringify({
                "id":stunum,
			    "name":name,
                "qq":qq,
                "phone":phone,
                "nClass":iclass,
                "time":null,
                "description":text,
                "read":null,
                "direction":direct,
                "isAGirl":flag,
                "readString":null
    		}),
    		success:function (res) {
    			console.log(res)
                alert("报名成功！")
                window.location.href='index.html';
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
})