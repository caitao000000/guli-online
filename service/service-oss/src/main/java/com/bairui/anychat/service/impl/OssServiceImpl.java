package com.bairui.anychat.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.bairui.anychat.service.OssService;
import com.bairui.anychat.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadfileAvatar(MultipartFile file) {

       try{
           // Endpoint以杭州为例，其它Region请按实际情况填写。
           String endpoint = ConstantPropertiesUtil.END_POINT;
           // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
           String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
           String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
           String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
           // 创建OSSClient实例。
           OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

           // 获取上传文件输入流。
           InputStream inputStream = file.getInputStream();
           //获取文件名称
           String filename = file.getOriginalFilename();
           // 给文件名称添加随机值
           filename = UUID.randomUUID().toString().replaceAll("-","")+filename;
           // 创建时间文件夹
           String  datePath = new DateTime().toString("yyyy/MM/dd");
           filename = datePath+"/"+ filename;
           //第一个参数：bucket 的名称
           //第二个参数：上传到oss文件的路径和文件名称
           //第三个参数：上传文件的输入流
           ossClient.putObject(bucketName, filename, inputStream);
           // 关闭OSSClient。
           ossClient.shutdown();
           //拼接imageurl
           String imageurl = "https://"+bucketName+"."+endpoint+"/"+filename;
           return imageurl;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }

    }
}
