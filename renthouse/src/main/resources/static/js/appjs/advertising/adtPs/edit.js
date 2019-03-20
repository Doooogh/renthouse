$().ready(function() {
	validateRule();
    $('#productId').val($('#productId').attr('value')).trigger('change');
    var s=$("#urlInfo").val();
    if (s!=undefined&&s!="undefined"&&s!=''){
        $("#urlInfo").attr("disabled","disabled");
    }
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

$.ajax({
    type : "get",
    url : "/advertising/adtPs/productList",
    async : false,
    success : function(data) {
        $('#productId').append("<option value=''>选择产品</option>")
        if(data.length>0){
            for(var i=0;i<data.length;i++){
                $('#productId').append("<option value="+data[i].id+">"+data[i].name+"</option>")
            }
        }
    }
});

$('#productId').change(function () {
	var productId=$('#productId').val();
	if(''!=productId){
        $.ajax({
            type : "post",
            url : "/advertising/adtPs/productOne",
            data:{id:productId},
            async : false,
            success : function(data) {
                $("#urlInfo").val(data.urlInfo)
            }
        });
	}
	if(''==productId){
        $("#urlInfo").val('')
        var s=$("#urlInfo").attr("disabled");
        if (s!=undefined&&s!="undefined"){
            $("#urlInfo").removeAttr("disabled")
		}
	}else {
        $("#urlInfo").attr("disabled","disabled");
	}
})

function update() {
    var s=$("#urlInfo").attr("disabled");
    if (s!=undefined&&s!="undefined"){
        $("#urlInfo").removeAttr("disabled")
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : "/advertising/adtPs/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
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

	})
}