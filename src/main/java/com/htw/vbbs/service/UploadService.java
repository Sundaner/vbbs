package com.htw.vbbs.service;

import com.htw.vbbs.util.TencentUtil;
import com.htw.vbbs.util.UUIDUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

    @Autowired
    private TencentUtil tencentUtil;

    public String uploadImgage(MultipartFile file){
        COSClient cosClient = tencentUtil.getClient();
        String bucketName = tencentUtil.getBucketName();
        // 指定要上传到 COS 上对象键
        String key = tencentUtil.getQianzhui() + "/img" + UUIDUtil.uuid() + "." + file.getContentType().substring(6);
        File localFile = null;
        try {
            //将 MultipartFile 类型 转为 File 类型
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return tencentUtil.getPath() + key;
    }

    public String uploadVideo(MultipartFile file){
        return null;
    }
}
