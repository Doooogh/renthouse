var imgNumber=0;
$(function () {
    fileLoad();
    listen();

});
function fileLoad() {
    layui.use('upload', function(){
        var $ = layui.jquery,
            upload = layui.upload;
            upload.render({
                elem: '#fileLoad', //绑定元素
                url: '/img/img/upLoad', //上传接口
                data:{
                    id: $("#houseId").val()
                },
                accept:'images',
                acceptMime: 'image/jpg, image/png,image/jpeg',
                auto: false,  //auto 参数必须设置为false
                bindAction: 'imgLoad',
                size: "2048",
                exts: 'jpg|png|jpeg'
                ,choose: function(obj){  //上传前选择回调方法
                    var flag = true;
                    obj.preview(function(index, file, result){
                       console.log("文件的索引为"+index);
                        var img = new Image();
                        img.src = result;
                        img.onload = function () { //初始化夹在完成后获取上传图片宽高，判断限制上传图片的大小。
                            if(!($(".img_content").length>=6)) {
                                if (img.width > 0 && img.height < 10000) {
                                    obj.upload(index, file); //满足条件调用上传方法
                                    layer.msg("满足条件");
                                } else {
                                    flag = false;
                                    layer.msg("您上传的小图大小必须是750*277尺寸！");
                                    return false;
                                }
                            }else{
                                flag = false;
                                layer.msg("轮播图最大数量为6!");
                                return false;
                            }
                        }
                        return flag;
                    });
                },
                done: function (r) {
                   layer.msg(r.msg);
                    location.reload();
                },
                error: function (r) {
                    layer.msg("错误");
                    layer.msg(r.msg);
                }
            });


    });
}
function listen() {
    $(".btn_delete").click(function () {
        var id=$(this).attr("img_id");
        deleteById(id);
    });
}
function deleteById(id) {

    layer.confirm('确定要删除选中的图片？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : "/img/img/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code==0) {
                    layer.msg(r.msg);
                    location.reload();
                }else{
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function getData() {
    $.ajax({
        type: "GET",
        url: "/img/img/list",
        data: {
            houseId:$("#houseId").val(),
            limit:10,
            offset:0
        },
        dataType: "json",
        success: function(data) {
            setData(data);
        },
        error:function () {
            layer.msg("数据获取错误");
        }
    });
}
function setData(data) {
    $(".imgs_content").html();
    var imgs=data.rows;
    /*$.each(imgs,function (i,img) {
        $(".imgs_content").append("<li class=\"img_content\"  th:each=\"img:${imgs}\">\n" +
            "                            <img th:src=\"${'/img/'+img.src}\" alt=\"\" >\n" +
            "                            <div class=\"img_select\" style=\"height: 20%\">\n" +
            "                                <button class=\"btn btn-warning btn-xs copy btn_edit \" th:attr=\"img_id=${img.id}\">编辑</button>\n" +
            "                                <button class=\"btn btn-danger btn-xs btn_delete\"  th:attr=\"img_id=${img.id}\">删除</button>\n" +
            "                            </div>\n" +
            "                        </li>");
    })*/
}