$("#form-goods-add").validate({
	submitHandler:function(form){
        var content = $('#summernote').summernote('code');
        console.log("---content-----"+content);
        console.log("---content..toString()-----"+content.toString());
        $('#goodsDesc').val(content);
		add();
	}
});

function add() {
	/*var userId = $("input[name='userId']").val();
	var deptId = $("input[name='deptId']").val();
	var loginName = $("input[name='loginName']").val();
	var userName = $("input[name='userName']").val();
	var password = $("input[name='password']").val();
	var email = $("input[name='email']").val();
	var phonenumber = $("input[name='phonenumber']").val();
	var sex = $("input[name='sex']:checked").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var roleIds = $.getCheckeds("role");
	var postIds = $.getSelects("post");*/
	/*{
			"userId": userId,
			"deptId": deptId,
			"loginName": loginName,
			"userName": userName,
			"password": password,
			"email": email,
			"phonenumber": phonenumber,
			"sex": sex,
			"status": status,
			"roleIds": roleIds,
			"postIds": postIds
		}*/
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx + "cms/goods/save",
		data : $("#form-goods-add").serialize(),
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", modal_status.FAIL);
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("新增成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					$.parentReload();
				});
			} else {
				$.modalAlert(data.msg, modal_status.FAIL);
			}

		}
	});
}

/*商品管理-新增-选择分类树*/
function categoryTree() {
	var treeId = $("#categoryId").val();
	var categoryId = treeId == null || treeId == "" ? "0" : treeId;
	var url = ctx + "cms/goodsCategory/categoryTree/" + categoryId;
    layer_show("选择分类", url, '380', '380');
}