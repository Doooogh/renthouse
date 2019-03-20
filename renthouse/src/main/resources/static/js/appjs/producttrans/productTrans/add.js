$().ready(function() {
	validateRule();

});

//创建补0函数
function p(s) {
    return s < 10 ? '0' + s : s;
}
var date = new Date();
var y = date.getFullYear();
var m = date.getMonth() + 1;
var d = date.getDate();
var time = y + '-' + p(m) + '-' + p(d);
laydate.render({
    elem: '#dt', //指定元素
    value: time
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
		url : "/producttrans/productTrans/save",
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
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}