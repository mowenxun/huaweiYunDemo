package com.deepexi.dd.system.mall.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 文件上传工具
 * @Author: 可乐
 * @Date: 2020/7/6 18:39
 * @Version: 1.0
 */
@Component
public class AliyunOssUtil {

    private static final Logger logger = LoggerFactory.getLogger(AliyunOssUtil.class);

    @Value("${aliyun.oss.bucket}")
    private String bucketName;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 上传文件
     *
     * @param file 需要上传的文件流
     * @return 上传完毕返回链接
     */
    public String upLoad(File file) {

        Preconditions.checkNotNull(file);
        logger.info("------OSS文件上传开始--------" + file.getName());
        String fileUrl = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            String key = dateStr + File.separator + file.getName();
            fileUrl = "https://" + bucketName + "." + endpoint + File.separator +  key;

            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, key, file));
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                logger.info("------OSS文件上传成功------" + fileUrl);
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return fileUrl;
    }

    /**
     * 删除文件
     * @param key
     */
    public void deleteFile(String key) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, key);
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }


    /**
     * 通过文件名下载文件
     *
     * @param objectName    要下载的文件名
     * @param localFileName 本地要创建的文件名
     */
    public void downloadFile(String objectName, String localFileName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFileName));
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }

    /**
     * 查询文件
     * @return
     */
    public List<String> listFile() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        // 设置prefix参数来获取fun目录下的所有文件。
        //listObjectsRequest.setPrefix("test/");
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        List<String> result = listing.getObjectSummaries().stream().map(OSSObjectSummary::getKey).collect(Collectors.toList());
        if (ossClient != null) {
            ossClient.shutdown();
        }
        return result;
    }
}
