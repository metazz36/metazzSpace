package com.metazz.metazzspace.common.util;

import com.google.gson.Gson;
import com.metazz.metazzspace.common.enums.ExceptionEnum;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.properties.QiNiuProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QiniuUtil {

    @Autowired
    QiNiuProperties qiNiuProperties;

    /**
     * 获取覆盖上传凭证
     */
    public String getToken(String key){
        Auth auth = Auth.create(qiNiuProperties.getAccess_key(), qiNiuProperties.getSecret_key());
        return auth.uploadToken(qiNiuProperties.getBucket(), key);
    }

    /**
     * 服务器直传
     */
    public String serverUpload(String fileName){
        // 1、构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
        // 2、生成上传凭证，然后准备上传
        String accessKey = qiNiuProperties.getAccess_key();
        String secretKey = qiNiuProperties.getSecret_key();
        String bucket = qiNiuProperties.getBucket();
        String key = fileName; //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        // 3、上传的文件，如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\qiniu\\" + fileName;
        try {
            // 4、上传文件
            Response response = uploadManager.put(localFilePath, key, upToken);
            // 5、解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
            throw new BaseException(ExceptionEnum.SERVER_ERROR);
        }
        return qiNiuProperties.getDownload_url() + fileName;
    }

}
