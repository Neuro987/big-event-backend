package com.learn.bigevent.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class TencentCosUtil {
    private static String SECRET_ID;
    private static String SECRET_KEY;
    private static String BUCKET_NAME;
    private static String REGION;
    private static COSClient COS_CLIENT;

    @Value("${tencent.cos.secret-id}")
    public void setSecretId(String secretId) {
        TencentCosUtil.SECRET_ID = secretId;
    }

    @Value("${tencent.cos.secret-key}")
    public void setSecretKey(String secretKey) {
        TencentCosUtil.SECRET_KEY = secretKey;
    }

    @Value("${tencent.cos.bucket-name}")
    public void setBucketName(String bucketName) {
        TencentCosUtil.BUCKET_NAME = bucketName;
    }

    @Value("${tencent.cos.region}")
    public void setRegion(String region) {
        TencentCosUtil.REGION = region;
    }

    @PostConstruct
    private void init() {
        COS_CLIENT = createCli();
    }

    public static String uploadFile(String destKey, InputStream inputStream) {
        try {
            putObject(destKey, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            COS_CLIENT.shutdown();
        }
        return "https://" + BUCKET_NAME + ".cos." + REGION + ".myqcloud.com/" + destKey;
    }

    private static COSClient createCli() {
        // 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
        // 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(REGION));
        // 生成cos客户端
        return new COSClient(cred, clientConfig);
    }

    private static void putObject(String destKey, InputStream inputStream) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, destKey, inputStream, null);
        try {
            PutObjectResult putObjectResult = COS_CLIENT.putObject(putObjectRequest);
            System.out.println(putObjectResult.getRequestId());
        } catch (CosClientException e) {
            e.printStackTrace();
        }
    }

}
