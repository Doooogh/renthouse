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
		url : "/tenant/tenant/save",
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
                maxlength: 11
            },
            phone:{
                required : true,
                mobile: true
            },
            sex:{
                required : true,

            }
        },
        messages : {
            name : {
                required : icon + "请输入姓名",
                maxlength: icon+"长度为11位"
            },
            phone : {
                required : icon + "请输入手机号",
                mobile: icon+"请输入正确的手机号"
            },
            sex : {
                required : icon + "请输入性别"
            }
        }
    })
}