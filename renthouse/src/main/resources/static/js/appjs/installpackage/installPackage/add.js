$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
        $.ajax({
            cache : true,
            type : "POST",
            url : "/installpackage/installPackage/validateVersion",
            data : {"plat":$('#plat').val(),"version":$('#version').val()},
            async : false,
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    save();
                } else {
                    layer.alert(data.msg)
                }

            }
        });
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/installpackage/installPackage/save",
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
            plat : {
				required : true
			},
            version : {
                required : true
            },
            details : {
                required : true
            }
		},
		messages : {
            plat : {
				required : icon + "请选择平台"
			},
            version : {
                required : icon + "请输入版本号"
            },
			details : {
                required : icon + "请输入更新详情"
            }
		}
	})
}