$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/product/product/update",
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
                required : true
            },
            logo : {
                required : true
            },
            introduce : {
                required : true
            },
            tag : {
                required : true
            },
            ca : {
                required : true,
                url : true
            },
            uptime : {
                required : true
            }

        },
        messages : {
            name : {
                required : icon + "请输入客户名称"
            },
            logo : {
                required : icon + "请选择图标"
            },
            introduce : {
                required : icon + "简介不能为空"
            },
            tag : {
                required : icon + "标签不能为空"
            },
            ca : {
                required : icon + "请输入地址",
                url : icon + "请输入正确格式的地址"
            },
            uptime : {
                required : icon + "请输入开始时间"
            }
        }
    })
}