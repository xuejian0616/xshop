var prefix = ctx + "cms/goods"


$(document).ready(function(){
	$('body').layout({ west__size: 185 });
	querygoodsList();



});

function querygoodsList() {
	var columns = [{
		checkbox: true
        }, {
			field: 'id',
			title: '商品id',
			visible: false
		}, {
			field: 'img',
			title: '商品小图',
			formatter: function(value,row,index){
				var s = '<img style="width:auto ;height:50px;"  src="'+row.smallPic+'" />';
				return s;
			}
		}, {
			field: 'name',
			title: '商品名',
        	searchable: true
		}, {
			field: 'title',
			title: '商品标题',
        	searchable: true
		}, {
			field: 'oldPrice',
			title: '原价',
        	sortable: true
		}, {
			field: 'price',
			title: '现价',
        	sortable: true
		}, {
			field: 'lowerPrice',
			title: '最低价',
        	sortable: true
		}, {
			field: 'discount',
			title: '折扣',
        	sortable: true
		}, {
			field: 'salesNum',
			title: '销量',
        	sortable: true
		}, {
			field: 'stockNum',
			title: '库存',
        	sortable: true
		}, {
			field: 'barcode',
			title: '条码',
        	searchable: true
		}, {
			field: 'firstCid',
			title: '一级分类',
        	searchable: true
		}, {
			field: 'subCid',
			title: '二级分类',
        	searchable: true
		}, {
			field: 'thirdCid',
			title: '三级分类',
        	searchable: true
		}, {
			field: 'status',
			title: '状态',
        	searchable: true,
			align: 'center',
			formatter: function(value, row, index) {
				if (value == '0') {
					return '<span class="label label-success">待上架</span>';
				} else if (value == '1') {
					return '<span class="label label-danger">正常</span>';
				}else if (value == '2') {
                    return '<span class="label label-danger">下架</span>';
                }else if (value == '3') {
                    return '<span class="label label-danger">删除</span>';
                }
			}
		}, {
			field: 'createTime',
			title: '创建时间',
        	formatter: function(value, row, index) {
                return new Date(value).format("yyyy-MM-dd hh:mm:ss");
            },
        	sortable: true
		}, {
			field: 'createTime',
			title: '更新时间',
        	formatter: function(value, row, index) {
				return new Date(value).format("yyyy-MM-dd hh:mm:ss");
			},
        	sortable: true
		}, {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
                if(row.status == '0') {
                    var actions = [];
                    actions.push('<a class="btn btn-primary btn-sm ' + editFlag + '" href="#" title="编辑" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
                    actions.push('<a class="btn btn-warning btn-sm ' + removeFlag + '" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a> ');
                    actions.push('<a class="btn btn-success btn-sm ' + upFrameFlag + '"  href="#" title="上架" onclick="upframe(\'' + row.id + '\')"><i class="fa fa-key"></i></a>');
                    return actions.join('');
                } else if(row.status == '1') {
            		var actions = [];
                	actions.push('<a class="btn btn-primary btn-sm ' + editFlag + '" href="#" title="编辑" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
                	actions.push('<a class="btn btn-warning btn-sm ' + removeFlag + '" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a> ');
                	actions.push('<a class="btn btn-success btn-sm ' + unFrameFlag + '"  href="#" title="下架" onclick="unframe(\'' + row.id + '\')"><i class="fa fa-key"></i></a>');
                	return actions.join('');
				}else if(row.status == '2') {
                    var actions = [];
                    actions.push('<a class="btn btn-primary btn-sm ' + editFlag + '" href="#" title="编辑" onclick="edit(\'' + row.id + '\')"><i class="fa fa-edit"></i></a> ');
                    actions.push('<a class="btn btn-warning btn-sm ' + removeFlag + '" href="#" title="删除" onclick="remove(\'' + row.id + '\')"><i class="fa fa-remove"></i></a> ');
                    actions.push('<a class="btn btn-success btn-sm ' + upFrameFlag + '"  href="#" title="上架" onclick="upframe(\'' + row.id + '\')"><i class="fa fa-key"></i></a>');
                    return actions.join('');
                } else {
					return "";
				}
            }
        }];
	var url = prefix + "/list";
	$.initTableParams(columns, url, queryParams);
}

function queryParams(params) {
	return {
		// 传递参数查询参数
		pageSize:       params.limit,
		pageNum:        params.offset / params.limit + 1,
		searchValue:    params.search,
		orderByColumn:  params.sort,
		isAsc:          params.order,
	};
}


/*商品管理-删除*/
function remove(goodsId) {
	$.modalConfirm("确定要删除选中商品吗？", function() {
		_ajax(prefix + "/remove/" + goodsId, "", "post");
    })
}

/*商品管理-修改*/
function edit(goodsId) {
    var url = prefix + '/edit/' + goodsId;
    layer_showAuto("修改商品", url);
}

/*商品管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_showAuto("新增商品", url);
}

/*商品管理-重置密码*/
function resetPwd(goodsId) {
    var url = prefix + '/resetPwd/' + goodsId;
    layer_show("重置密码", url, '800', '300');
}

// 批量强退
function batchRemove() {
	var rows = $.getSelections("goodsId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function() {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post");
	});
}



function upSmallPic(){
    qiNiuUploader("smallPic","smallPicImg","pickfiles");
}