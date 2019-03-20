var prefix = "/producttrans/productTrans"

//创建补0函数
function p(s) {
    return s < 10 ? '0' + s : s;
}


var date = new Date();
var y = date.getFullYear();
var m = date.getMonth() + 1;
var d = date.getDate();
var time = y + '-' + p(m) + '-' + p(d);

date.setMonth(date.getMonth() - 1);
y = date.getFullYear();
m = date.getMonth() + 1;
d = date.getDate();
var beforeTime = y + '-' + p(m) + '-' + p(d);


laydate.render({
    elem: '#end', //指定元素
    value: time
});
laydate.render({
    elem: '#begin', //指定元素
    value: beforeTime
});

$(function () {
    $('#begin').val(beforeTime);
    $('#end').val(time);
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
                url: prefix + "/list", // 服务器数据的加载地址
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
                    var s = data['type'].length;
                    if (s == 0) {
                        $('#s1').hide();
                    } else {
                        $('#s1').show();
                        try {
                            chart(data, 's1');
                        } catch (e) {
                        }
                    }
                    var list = data.rows;
                    var a = [];
                    for (var i = 0; i < list.length; i++) {
                        if (list[i].productId != null) {
                            if (list[i].id == null) {
                                list[i].transNum = null;
                            }
                            a.push(data.rows[i]);
                        }
                    }
                    return a;
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
                        productName: $('#productName').val(),
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
                        field: 'productName',
                        title: '产品名称',
                        sortable: true
                    },
                    {
                        field: 'showTime',
                        title: '日期',
                        sortable: true
                    },
                    {
                        field: 'pv',
                        title: 'pv',
                        sortable: true
                    },
                    {
                        field: 'uv',
                        title: 'uv',
                        sortable: true
                    },
                    {
                        field: 'transNum',
                        title: '转化数',
                        sortable: true
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var id = row.id;
                            if (id == null) {
                                id = -1;
                            }
                            var e = '<a class="btn btn-success btn-sm" href="#" mce_href="#" title="编辑" onclick="checkInfo(\''
                                + id + ',' + row.showTime + ',' + row.productId
                                + '\')">编辑</button> ';
                            return e;
                        }
                    }],

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
        area: ['400px', '460px'],
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


function chart(data, id) {
    var option = {
        title: {
            text: '转化统计'
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
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            top:'50%',
            containLabel: true
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
        xAxis: [
            {
                axisLabel: {
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

    if (data['type'].length > 0 && data['x'].length > 0) {

        option['legend']['data'] = data['type']
        option['xAxis'][0]['data'] = data['x'];
        option['series'] = [{}];
        for (var i = 0; i < data['type'].length; i++) {
            var t = data['type'][i];
            var s = {}
            s['data'] = data['y'][t];
            s['type'] = 'line';
            s['stack'] = '总量' + i;
            s['name'] = t;


            option['series'][i] = s;
        }
        myChart = echarts.init(document.getElementById(id));
        var tm = [];
        var tmi = 0;
        if (option['legend']['data'].length > 0) {
            for (var i = 0; i < option['legend']['data'].length; i++) {
                if (i == 5) {
                    tm[tmi] = '';
                    tmi++;
                }
                if ((i - 5) % 8 == 0 && i != 0 && i != 5) {
                    tm[tmi] = '';
                    tmi++;
                }
                tm[tmi] = option['legend']['data'][i];
                tmi++;
            }
        }
        option['legend']['data'] = tm;
        myChart.setOption(option);
        window.onresize = myChart.resize;
    }

}


// 查询详情
function checkInfo(id) {
    console.log(id);
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['400px', '300px'],
        content: prefix + '/edit/' + id// iframe的url
    });
}
