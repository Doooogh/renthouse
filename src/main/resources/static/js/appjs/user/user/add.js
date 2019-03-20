$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/user/user/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

$.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && /^1[34578]\d{9}$/.test(value));
}, "手机号码格式错误!");

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true,
                maxlength: 8,

			},
            username : {
                required: true,
                remote: {
                    url: "/user/user/selectByUsername",     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "json",           //接受数据格式
                    data: {                     //要传递的数据
                        username: function () {
                             var s=$("#username").val()
                             return s;
                        }
                    },
                    error: function () {
                        layer.msg("服务器错误");
                    }
                }
            },
            password : {
                required : true,
                maxlength: 16,
                minlength: 3,
            },
            phone : {
                required : true,
                mobile: true
            },
            sex : {
                required : true
            },
            age : {
                required : true
            },
            status : {
                required : true
            },

		},
		messages : {
			name : {
                required : icon + "请输入姓名",
                maxlength: icon + "姓名最长为8位",
            },
            username : {
                required : icon + "请输入用户",
                remote: icon+"用户名已被使用"

            },
            password : {
                required : icon + "请输入密码",
                maxlength: icon+"密码最长为16位",
                minlength: icon+"密码最少为3位",
            },
            phone : {
                required : icon + "请输入手机号",
                mobile: icon+"请输入正确的手机号"
            },
            sex : {
                required : icon + "请输入性别"
            },
            age : {
                required : icon + "请输入年龄"
            },
            status : {
                required : icon + "请输入用户状态"
            },

		}
	})
}