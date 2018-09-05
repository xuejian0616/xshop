$("#form-goods-edit").validate({
    submitHandler:function(form){
        var content = $('#summernote').summernote('code');
        console.log("---content-----"+content);
        console.log("---content..toString()-----"+content.toString());
        $('#goodsDesc').val(content);
        add();
    }
});

function add() {
    $.ajax({
        cache : true,
        type : "POST",
        url : ctx + "cms/goods/save",
        data : $("#form-goods-edit").serialize(),
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