var prefix = "/userstatistics/flowPool";

//创建补0函数
function p(s) {
    return s < 10 ? '0' + s: s;
}


var date = new Date();
var y = date.getFullYear();
var m = date.getMonth()+1;
var d = date.getDate();
var time = y+'-'+ p(m)+'-'+p(d);
var firstMonth = y+'-'+ p(m);

date.setMonth(date.getMonth()-1);
y = date.getFullYear();
m = date.getMonth()+1;
d = date.getDate();
var beforeTime = y+'-'+ p(m)+'-'+p(d);

date.setMonth(date.getMonth()-2);
y = date.getFullYear();
m = date.getMonth()+1;
d = date.getDate();
var lastMonth = y+'-'+ p(m);

laydate.render({
    elem: '#end', //指定元素
    value: time
});
laydate.render({
    elem: '#begin', //指定元素
    value: beforeTime
});
laydate.render({
    elem: '#monthEnd', //指定元素
    type: 'month',
    value: firstMonth
});
laydate.render({
    elem: '#monthBegin', //指定元素
    type: 'month',
    value: lastMonth
});
laydate.render({
    elem: '#weekMonth', //指定元素
    type: 'month',
    value: firstMonth
});

$(function () {
    $('#begin').val(beforeTime);
    $('#end').val(time);
    $("#weekType").hide();
    $("#monthType").hide();
    try {
        load();
    } catch (e) {

    }

});


function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/userReport", // 服务器数据的加载地址
                showRefresh: true,
                showToggle: true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                sortable: true,
                showExport: true,
                exportDataType: "all",
                showFooter: true,
                //search : true, // 是否显示搜索框
                showColumns: true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                responseHandler: function (data) {
                    var s=data['type'].length;
                    if(s==0){
                        $('#s1').hide();
                    }else {
                        $('#s1').show();
                        try {
                            chart(data,'s1');
                        }catch (e) {
                        }
                    }
                    return data.rows;
                },
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        sort: params.sort,      //排序列名
                        sortOrder: params.order, //排位命令（desc，asc）
                        begin: $('#begin').val(),
                        end: $('#end').val(),
                        monthBegin: $('#monthBegin').val(),
                        monthEnd: $('#monthEnd').val(),
                        weekMonth: $('#weekMonth').val(),
                        type: $('#type').val(),
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        field: 'time',
                        title: '日期',
                        sortable: true
                    },
                    {
                        field: 'num',
                        title: '新增数',
                        sortable: true,
                        footerFormatter: function (value) { //底部合计
                            var count = 0;
                            for (var i in value) {
                                count += parseInt(value[i].num);
                            }
                            return '总计:'+  count;
                        }
                    }

                    ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}
function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });

}

function chart(data,id) {
    var  option = {
        title: {
            text: '新增用户统计'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data: ['邮件营销', '联盟广告', '视频广告', "", '直接访问', '搜索引擎']
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                axisLabel:{
                    show: true,
                    interval: 'auto',
                    rotate: 0,
                    margin: 8,
                    // formatter: null,
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: '#333'
                    }
                },

                type: 'category',
                boundaryGap: false,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '邮件营销',
                type: 'bar',
                stack: '总量',
                data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
                name: '联盟广告',
                type: 'bar',
                stack: '总量',
                data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
                name: '视频广告',
                type: 'bar',
                stack: '总量',
                data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
                name: '直接访问',
                type: 'bar',
                stack: '总量',
                data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
                name: '搜索引擎',
                type: 'bar',
                stack: '总量',
                data: [820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };

    if(data['type'].length>0&&data['x'].length>0){

        option['legend']['data']=data['type']
        option['xAxis'][0]['data']=data['x'];
        option['series']=[{}];
        for (var i = 0; i < data['type'].length; i++) {
            var t=data['type'][i];
            var s={}
            s['data']=data['y'][t];
            s['type']='line';
            s['stack']='总量' + i;
            s['name']=t;
            s['itemStyle']={normal: {areaStyle: {type: 'default'}}};

            option['series'][i]=s;
        }
        myChart = echarts.init(document.getElementById(id));
        var tm=[];
        var tmi;
        if(option['legend']['data'].length>0){
            for (var i=0;i<option['legend']['data'].length;i++){
                tmi=i;
                if (i==3){
                    tm[tmi]='';
                    tmi=i+1;
                }
                tm[tmi]=option['legend']['data'][i];
            }
        }
        option['legend']['data']=tm;
        myChart.setOption(option);
        window.onresize = myChart.resize;
    }

}


// 点击日周月
function chooseType(flag) {
    if(flag == 0){
        $("#day").removeClass("btn-default").addClass("btn-success");
        $("#week").removeClass("btn-success").addClass("btn-default");
        $("#month").removeClass("btn-success").addClass("btn-default");
        $('#begin').val(beforeTime);
        $('#end').val(time);
        $("#dayType").show();
        $("#weekType").show();
        $("#monthType").hide();
        $("#type").val(0);
        reLoad();
    }else if(flag == 1){
        $("#week").removeClass("btn-default").addClass("btn-success");
        $("#day").removeClass("btn-success").addClass("btn-default");
        $("#month").removeClass("btn-success").addClass("btn-default");
        $('#weekMonth').val(firstMonth);
        $("#dayType").hide();
        $("#weekType").show();
        $("#monthType").hide();
        $("#type").val(1);
        reLoad();
    }else if(flag == 2){
        $("#month").removeClass("btn-default").addClass("btn-success");
        $("#week").removeClass("btn-success").addClass("btn-default");
        $("#day").removeClass("btn-success").addClass("btn-default");
        $('#monthBegin').val(lastMonth);
        $('#monthEnd').val(firstMonth);
        $("#dayType").hide();
        $("#weekType").hide();
        $("#monthType").show();
        $("#type").val(2);
        reLoad();
    }
}
