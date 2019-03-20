$().ready(function() {
	validateRule();
    $('#productId').val($('#businessId').attr('value')).trigger('change');
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

$.ajax({
    cache : true,
    type : "POST",
    url : "/featured/rcdPs/productList",
    async : false,
    success : function(data) {
        if(data.length>0){
            for(var i=0;i<data.length;i++){
                $('#productId').append("<option value="+data[i].id+">"+data[i].name+"</option>")
            }
        }
    }
});

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/featured/rcdPs/update",
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
				required : icon + "请输入名字"
			}
		}
	})
}