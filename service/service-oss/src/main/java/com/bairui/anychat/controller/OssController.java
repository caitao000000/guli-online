package com.bairui.anychat.controller;

import com.bairui.anychat.service.OssService;
import com.bairui.common.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/uploadfile")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping
    public Resp  upload(MultipartFile file){

        String imageurl = ossService.uploadfileAvatar(file);
        return  Resp.ok().data("imageurl",imageurl);
    }
}
