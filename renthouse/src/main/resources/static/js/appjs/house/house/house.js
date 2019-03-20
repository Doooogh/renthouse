
var prefix = "/house/house"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
                        showRefresh : true,
                        showToggle : true,
                        iconSize : 'outline',
                        toolbar : '#exampleToolbar',
                        striped : true, // 设置为true会有隔行变色效果
                        dataType : "json", // 服务器返回的数据类型
                        pagination : true, // 设置为true会在底部显示分页条
                        // queryParamsType : "limit",
                        // //设置为limit则会发送符合RESTFull格式的参数
                        singleSelect : false, // 设置为true将禁止多选
                        // contentType : "application/x-www-form-urlencoded",
                        // //发送到服务器的数据编码类型
                        pageSize : 10, // 如果设置了分页，每页数据条数
                        pageNumber : 1, // 如果设置了分布，首页页码
                        sortable:true,
                        sortName:"id",
                        sortOrder:"asc",
                        // search : true, // 是否显示搜索框
                        showColumns : true, // 是否显示内容下拉框（选择显示的列）
                        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								sort: params.sort,      //排序列名
                                sortOrder: params.order, //排位命令（desc，asc）
					           	title:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
																{
									field : 'id', 
									title : 'id' 
								},
																{
									field : 'title', 
									title : '房屋标题' 
								},
																{
									field : 'largeAreas', 
									title : '大范围地址' 
								},
																{
									field : 'smallAreas', 
									title : '小范围地址' 
								},
																{
									field : 'squareMeter', 
									title : '房屋平米数' 
								},
																{
									field : 'orientation', 
									title : '房屋朝向' 
								},
																{
									field : 'houseType', 
									title : '房屋类型' 
								},
																{
									field : 'pubdate', 
									title : '发布时间' 
								},
																{
									field : 'price', 
									title : '价格' 
								},
																{
									field : 'description', 
									title : '房屋描述' 
								},								{
									field:'id',
									title:'房屋图片',
									width: 120,
									formatter:function (value,row,index) {
										var str='<span class="glyphicon glyphicon-picture" aria-hidden="true"/>'+'<a style="text-decoration:none" onclick="showPic(\''+value+'\')">'+'查看'+'</a>';
                                  		return str;
                                    }

								},
																{
									field : 'address', 
									title : '详细地址',
									formatter:function (value, row, index) {
                                      /* var s= '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                        + row.id
                                        + '\')"><i class="fa fa-key"></i></a> ';*/
                                      var s='<span class="glyphicon glyphicon-map-marker" aria-hidden="true"/>'+'<a style="text-decoration:none" onclick="showMap(\''+value+'\')">'+value+'</a>';
                                      return s ;
                                    }

								},
																{
									field : 'addTime', 
									title : '具体发布时间' 
								},
																{
									field : 'landlordName',
									title : '房东姓名' ,
								},{
                                field : 'tenantName',
                                title : '租客姓名' ,
                            },
																{
									field : 'elevator', 
									title : '是否有电梯',
									formatter:function (value, row, index) {
										var str="";
										if(value==1){
											str="有";
										}else {
											str="无";
										}
										return str;
                                    }
								},
																{
									field : 'tv', 
									title : '是否有电视' ,
									formatter:function (value, row, index) {
										var str="";
										if(value==1){
											str="有";
										}else {
											str="无";
										}
										return str;
									}
								},
																{
									field : 'fridge', 
									title : '是否有冰箱' ,
									formatter:function (value, row, index) {
										var str="";
										if(value==1){
											str="有";
										}else {
											str="无";
										}
										return str;
									}
								},
																{
									field : 'airConditioner', 
									title : '是否有空调' ,
									formatter:function (value, row, index) {
										var str="";
										if(value==1){
											str="有";
										}else {
											str="无";
										}
										return str;
									}
								},
																{
									field : 'broadBand', 
									title : '是否有宽带' ,
									formatter:function (value, row, index) {
										var str="";
										if(value==1){
											str="有";
										}else {
											str="无";
										}
										return str;
									}
								},
																{
									field : 'wardrobe', 
									title : '是否有衣柜' ,
									formatter:function (value, row, index) {
										var str="";
										if(value==1){
											str="有";
										}else {
											str="无";
										}
										return str;
									}
								},
																{
									field : 'status', 
									title : '房屋状态',
									formatter:function (value, row, index) {

										var str="";
										if(value==1){
											str="已出租";
										}else if(value==0) {
											str="未出租";
										}else{
											str="以预约"
										}
										return str;
									}
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									width: '100px',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
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
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}

function showMap(location) {
    layer.open({
        type : 2,
        title : '具体地点',
        maxmin : true,
        scrollbar: false,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '480px' ],
        // content : prefix + '/showMap/'+location // iframe的url
        content : prefix + '/showMap/'+location
    });
}
function showPic(id) {
    layer.open({
        type : 2,
        title : '图片管理',
        maxmin : true,
        scrollbar: false,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '480px' ],
        content : prefix + '/showPic/'+id
    });
}