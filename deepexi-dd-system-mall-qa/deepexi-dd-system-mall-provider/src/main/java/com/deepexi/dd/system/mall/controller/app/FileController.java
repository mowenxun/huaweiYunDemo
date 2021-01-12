package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.system.mall.util.AliyunOssUtil;
import com.deepexi.dd.system.mall.util.MultipartFileUtils;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin-api/v1/app/file")
public class FileController {

    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    @Value("${aliyun.oss.endpoint}")
    private String intervalUrl;

    @Value("${aliyun.oss.extranet}")
    private String extranetUrl;

    @PostMapping("/upload")
    public Payload<String> upload(@RequestParam("file") MultipartFile file) {

        File tmpFile = null;
        String result = null;
        try {
            tmpFile = MultipartFileUtils.copyMultipartFile2File(file);
             result = aliyunOssUtil.upLoad(tmpFile);
            result=   result.replace(intervalUrl, extranetUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(tmpFile != null){
                tmpFile.delete();
            }
        }
        return new Payload<>(result);
    }
}
