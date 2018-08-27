package com.xshop.project.tool.qiniu;

import com.google.common.collect.Maps;
import com.qiniu.util.StringMap;
import com.xshop.common.utils.QiniuUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;
import java.util.UUID;


/**
 * 七牛上传
 * @program: xshop
 * @Date: 2018/8/27 16:42 
 * @Author: xuhao
 * @Description:
 **/
@Controller
@RequestMapping("/qiniu")
public class QiNiuController {
    private Logger logger = LoggerFactory.getLogger(getClass());


    //七牛Token
    @RequestMapping("/getToken")
    @ResponseBody
    public Map<String,Object> uptocken(HttpServletRequest request){
        Map<String,Object> resultMap = Maps.newHashMap();
        String token = QiniuUtil.getUpToken();

        resultMap.put("uptoken",token);
        return resultMap;
    }


    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImgage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        File destFile = null;
        if (!file.isEmpty()) {
            try {
                String path = request.getSession().getServletContext().getRealPath("/upload");
                String fileName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                destFile = new File(path + "/" + fileName);
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile); //复制临时文件到指定目录
                StringMap stringMap = QiniuUtil.upload(null, path + "/" + fileName);
                return stringMap.map();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return null;
            } finally {
                destFile.delete();
            }
        }
        return null;
    }
}