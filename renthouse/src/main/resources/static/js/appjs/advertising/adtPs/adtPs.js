
//文件上传
/*layui.use('upload', function() {
    var $ = layui.jquery
        , upload = layui.upload;
    upload.render({
        elem: '#test10'
        ,url: '/upload/'
        ,multiple: true
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
            	alert(index)
				alert(file.name)
				alert(result)
                $('#useing').append('<div class="file-box"><div class="file"><a href="#"><span class="corner"></span><div class="image">' +
                    '<img src="'+ result +'" alt="'+ file.name +'" class="img-responsive"></div>' +
                    '<div class="file-name"></div>' +
                    '&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;<button' +
                    'class="btn btn-warning btn-xs copy" :url="row.url">编辑' +
                    '</button>&nbsp; &nbsp; &nbsp; &nbsp;<button class="btn btn-danger btn-xs"' +
                    '@click="remove(row.id)">删除' +
                    '</a></div></div>')
            });
        }
        ,done: function(res){
            //上传完毕
        }
    });
});*/

layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#test10', //绑定元素
        url: '/advertising/adtPs/upload', //上传接口
        size: 1000,
        accept: 'file',
        done: function (r) {
            layer.msg(r.msg);
            app.getData();
        },
        error: function (r) {
            layer.msg(r.msg);
        }
    });
});

//拖拽排序
$(function() {
    $( "#useing" ).sortable();
    $( "#useing" ).disableSelection();
});

//预览

function add() {
	alert(1)
}
