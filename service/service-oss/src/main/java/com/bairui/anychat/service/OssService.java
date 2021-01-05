package com.bairui.anychat.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    //上传讲师头像到阿里云，返回图片url
    String uploadfileAvatar(MultipartFile file);
}
