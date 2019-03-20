var  perIds;
$().ready(function() {
    getMenuTreeData();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
        getAllSelectNodes();
		update();
	}
});

function getMenuTreeData() {
    var roleId = $('#role_id').val();
    $.ajax({
        type : "GET",
        url : "/permission/permission/tree/" + roleId,
        success : function(data) {
            loadMenuTree(data);
        },
		error:function () {
        	layer.msg("服务器错误");
        }
    });
}
function loadMenuTree(perTree) {
    $('#perTree').jstree({
        "plugins" : [ "wholerow", "checkbox" ],
        'core' : {
            'data' : perTree
        },
        "checkbox" : {
            //"keep_selected_style" : false,
            //"undetermined" : true
            //"three_state" : false,
            //"cascade" : ' up'
        }
    });
    $('#perTree').jstree('open_all');
}
function getAllSelectNodes() {
    var ref = $('#perTree').jstree(true); // 获得整个树
    perIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
    $("#perTree").find(".jstree-undetermined").each(function(i, element) {
        perIds.push($(element).closest('.jstree-node').attr("id"));
    });
    console.log(perIds);
}
function update() {
	$("#perIds").val(perIds);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/role/role/update",
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