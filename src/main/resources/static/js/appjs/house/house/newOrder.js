var prefix = "/order/order";
var myDate=new Date();
var year = myDate.getFullYear();//获取当前年
var month = myDate.getMonth()+2;//获取当前月
var time=year+"-"+month+"-01";


$(function () {
dataUtils();
verify();
dateTool();

});
window.onload=function () {
    blur();
    // tenancyBlur();
};

$.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && /^1[34578]\d{9}$/.test(value));
}, "手机号码格式错误!");

function dataUtils() {

    console.log("开始测试");
    var houseId=$("#houseId").val();
    console.log(houseId);
    if(!houseId==""){
        $.ajax({
            type:'get',
            url: prefix+'/gain',
            data:{
                id:houseId,
            },
            dataType:'json',
            success:function (data) {
                console.log(data);
                $("#title").val(data.title);
                $("#title").attr("readonly","readonly");
                $("#price").val(data.price);
                $("#price").attr("readonly","readonly");
                $("#landlordName").val(data.landlordName);
                $("#landlordName").attr("readonly","readonly");
                $("#landlordId").val(data.landlordId);

            },
            error:function () {
                layer.msg("服务器错误");
            }
        });
    }
}
function verify() {
    validateRule();
}

function blur() {
    var attr = $("#title").attr("readonly");
    $("#title").blur(function () {
       if(typeof attr == typeof undefined){
           $.ajax({
               type:"GET",
               url:prefix+"/gainByTitle",
               data:{
                   title:$("#title").val()
               },
               dataType:"json",
               success:function (data) {
                   if(data.title==null){
                       layer.msg("请输入正确的房子标题或房子已经出租");
                       $("#price").val("");
                       $("#price").attr("placeholder","价格");
                       $("#price").removeAttr("readonly");
                       $("#landlordName").val("");
                       $("#landlordName").attr("placeholder","房东姓名");
                       $("#landlordName").removeAttr("readonly");
                       $("#landlordId").val();
                       $("#houseId").val();
                   }else{
                       $("#price").val(data.price);
                       $("#price").attr("readonly","readonly");
                       $("#landlordName").val(data.landlordName);
                       $("#landlordName").attr("readonly","readonly");
                       $("#landlordId").val(data.landlordId);
                       $("#houseId").val(data.houseId);
                   }

               },
               error:function (data) {
                   layer.msg("服务器错误");
               }
           });
           // alert("需要验证");
       }else{
           // alert("不需要后台验证");
       }
    });



}
    

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            landlordName : {
                required : true
            },
            title:{
                required : true,
            },
            tenancy:{
                required : true,

            },
            price:{
                required : true,

            },
            tenantName:{
                required : true,

            },
            phone:{
                required : true,
                mobile: true

            }
        },
        messages : {
            landlordName : {
                required : icon + "请输入房东姓名",
            },
            title:{
                required : icon + "请输入标题",
            },
            tenancy: {
                required : icon + "请输入租期",
            },
            tenantName:{
                required : icon + "请输入租客姓名",

            },
            price:{
                required : icon + "请输入价钱",

            },

            phone : {
                required : icon + "请输入手机号",
                mobile: icon+"请输入正确的手机号"
            }
        }
    })
}
function save() {
    $("#ordered").click(function () {
        var money=far($("#tenancy").val())*$("#price").val();
        alert("123123");
    });
    $("#submit").click(function () {
        var money=far($("#tenancy").val())*$("#price").val();
        alert("890089");
    });
}
function dateTool() {
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        //年选择器
        laydate.render({
            elem: '#tenancy'
            ,type: 'month'
            ,min:time
        });
    });
    }
function tenancyBlur() {
    $("#tenancy").blur(function () {
        if($("#price").val().length!==0){
            calRent();
        }
    });

}
function calRent() {
    var start=new Date().getFullYear()*12+new Date().getMonth()+1;
    var endMonth=$("#tenancy").val().substring(0,4)*12+$("#tenancy").val().substring(5)*1;
    var money=(endMonth-start)*($("#price").val());
    $(".allmoney").text(money+"元");
}
function to_order() {
    var isTrue=$("#signupForm").valid();
    if(isTrue) {
        calRent();
        layer.confirm('确定要预约？', {
            btn : [ '确定', '取消' ]
        }, function() {
            var start=new Date().getFullYear()*12+new Date().getMonth()+1;
            var endMonth=$("#tenancy").val().substring(0,4)*12+$("#tenancy").val().substring(5)*1;
            var tenancy=(endMonth-start);
            $.ajax({
                url : prefix+"/ordered",
                type : "post",
                data : {
                   landlordId:$("#landlordId").val(),
                   houseId:$("#houseId").val(),
                    tenancyTerm:tenancy,
                    price:$("#price").val(),
                    tenantName:$("#tenantName").val(),
                    sex:$('.sex:radio:checked').val(),
                    phone:$("#phone").val()
                },
                success : function(r) {
                    if (r.code==0) {
                        layer.msg(r.msg,{
                        time: 2000
                        },function () {
                            location.reload();
                        });
                    }else{
                        layer.msg(r.msg,{
                            time: 2000
                        },function () {
                            location.reload();
                        });


                    }
                },
                error:function (r) {
                    layer.msg(r.msg,{
                        time: 2000

                    },function () {
                        location.reload();
                    });
                }
            });
        })
    }else{
        alert("错误");
    }

}
function to_submit() {
    var isTrue=$("#signupForm").valid();
    if(isTrue) {
        calRent();
        layer.confirm('确定要订房？', {
            btn : [ '确定', '取消' ]
        }, function() {
            var start=new Date().getFullYear()*12+new Date().getMonth()+1;
            var endMonth=$("#tenancy").val().substring(0,4)*12+$("#tenancy").val().substring(5)*1;
            var tenancy=(endMonth-start);
            $.ajax({
                url : prefix+"/decide",
                type : "post",
                data : {
                    landlordId:$("#landlordId").val(),
                    houseId:$("#houseId").val(),
                    tenancyTerm:tenancy,
                    price:$("#price").val(),
                    tenantName:$("#tenantName").val(),
                    sex:$('.sex:radio:checked').val(),
                    phone:$("#phone").val()
                },
                success : function(r) {
                    if (r.code==0) {
                        layer.msg(r.msg,{
                            time: 2000
                        },function () {
                            location.reload();
                        });
                    }else{
                        layer.msg(r.msg,{
                            time: 2000
                        },function () {
                            location.reload();
                        });


                    }
                },
                error:function (r) {
                    layer.msg(r.msg,{
                        time: 2000

                    },function () {
                        location.reload();
                    });
                }
            });
        })
    }else{
        alert("错误");
    }

}