package org.bighamapi.hmp.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class QCOSUtil {
    private static COSClient cosClient;
    private static String bucketName;
    static {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials("AKIDARXO4aS7CIKp5dODZdXJcnkVLKxxH96u", "k1Kov6ZPDq0q9sPq2yPaLcz70haI5rfE");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shenzhen-fsi"));
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        bucketName ="jszt-1256562854";
    }
    /**
     * 利用腾讯云cos api上传文件
     * @param file 文件
     * @param fileName 文件名
     * @return 上传后的文件链接
     */
    public static String uploadFile(File file,String fileName){
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);

        URL url = cosClient.generatePresignedUrl(bucketName, fileName, expiration);
        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();
        return url.toString();
    }
}
