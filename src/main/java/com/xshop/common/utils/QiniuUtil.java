package com.xshop.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.xshop.common.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;


/**
 * 七牛上传帮助类
 * @program: xshop
 * @Date: 2018/8/27 16:42 
 * @Author: xuhao
 * @Description:
 **/
public class QiniuUtil {
    private static Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    //密钥配置
    private static final Auth auth = Auth.create(CommonConstant.ACCESS_KEY, CommonConstant.SECRET_KEY);
    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // public static Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    public static Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    private static final Configuration c = new Configuration(z);

    //创建上传对象
    private static UploadManager uploadManager = new UploadManager(c);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken() {
        return auth.uploadToken(CommonConstant.BUCKET);
    }

    /**
     * 七牛云上传文件  断点续传
     *
     * @param fileName 上传后的文件名
     * @param filePath 上传的文件名路径
     * @return
     * @throws IOException
     */
    public static StringMap upload(String fileName, String filePath) throws IOException {
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), CommonConstant.BUCKET).toString();
        //设置断点续传文件进度保存目录
        FileRecorder fileRecorder = new FileRecorder(localTempDir);
        UploadManager uploadManager = new UploadManager(c, fileRecorder);
        try {
            //调用put方法上传
            Response res = uploadManager.put(filePath, fileName, getUpToken());

            //打印返回的信息
            return res.jsonToMap();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error(r.toString());
        }
        return null;
    }
}