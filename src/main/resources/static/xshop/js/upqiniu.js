// 七牛上传图片
var tokenStr = "";
//七牛图片地址
var qiniuUrl = "XXX";
//获取上传七牛的token
$.ajax({
    url:"/qiniu/getToken",
    success: function(res){
        tokenStr = res.uptoken
    }
})
//七牛上传的File
$(".qiniuUp").on('change',function(){
    qiniuUpImg(this);
})


//七牛上传图片
function qiniuUpImg(obj){
    let file = obj.files[0];
    let inputId = $(obj).attr("inputId");
    let imgId = $(obj).attr("imgId");
    let token = tokenStr;
    let key = file.name;

    let config = {
        useCdnDomain: true, //表示是否使用 cdn 加速域名，为布尔值，true 表示使用，默认为 false
        region: qiniu.region.z1, //选择上传域名区域；当为 null 或 undefined 时，自动分析上传域名区域
        disableStatisticsReport:false, //是否禁用日志报告，为布尔值，默认为false。
        retryCount:3, //上传自动重试次数,默认 3 次
        concurrentRequestLimit:3,//分片上传的并发请求量,默认为3次
        checkByMD5:false //是否开启 MD5 校验,默认为 false，不开启。
    }
    let putExtra = {
        fname: "", //文件原文件名
        params: {}, //用来放置自定义变量
        mimeType: null //用来限制上传文件类型，为 null 时表示不对文件类型限制；限制类型放到数组里： ["image/png", "image/jpeg", "image/gif"]
    }

    let observer = {
        next: (res) => { // 主要用来展示进度
            let total = res.total;
            console.log('上传图片中！')
            // console.log("进度：" + parseInt(total.percent) + "% ")
        },
        error: (err) => { // 失败报错信息
            console.log(err)
            console.log('上传错误！')
        },
        complete: (res) => { // 接收成功后返回的信息
            console.log(res.hash)
            console.log(qiniuUrl+res.hash)
            var imgurl = qiniuUrl+res.hash;
            $("#"+ imgId).attr("src",imgurl);
            $("#"+inputId).attr("value",imgurl);
        }
    }

    let subscription;
    // 调用sdk上传接口获得相应的observable，控制上传和暂停
    let observable = qiniu.upload(file, null, token, putExtra, config);
    observable.subscribe(observer);
}

