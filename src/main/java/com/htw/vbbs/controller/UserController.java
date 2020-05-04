package com.htw.vbbs.controller;

import com.htw.vbbs.Result.CodeMsg;
import com.htw.vbbs.Result.Result;
import com.htw.vbbs.util.TencentUtil;
import com.htw.vbbs.util.UUIDUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TencentUtil tencentUtil;

    @RequestMapping("/settings")
    public String updateUserInfo () {

        return "settings";
    }

    @PostMapping("/uploadImg")
    @ResponseBody
    public Result<String> uploadImg(@RequestParam(value = "file") MultipartFile file){
        if(file.isEmpty()){
            Result.error(CodeMsg.UPLOAD_IMG_FAIL);
        }
        COSClient cosClient = tencentUtil.getClient();
        String bucketName = tencentUtil.getBucketName();
        // 指定要上传到 COS 上对象键
        String key = tencentUtil.getQianzhui() + "/img/" + UUIDUtil.uuid() + "." + file.getContentType().substring(6);
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
        return Result.success(tencentUtil.getPath() + key);
    }

}
