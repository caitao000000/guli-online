package com.bairui.anychat.eduservice.controller;

import com.bairui.common.utils.Resp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public Resp login( ){
        return Resp.ok().data("token","admin");
    }

    @GetMapping("/info")
    public Resp info( ){
        return Resp.ok().data("roles","[admin]").data("name","admin").data("avatar","https://image.so.com/view?q=tu&listsrc=sobox&listsign=0b331b9ae5d43a78e42cb462763d8e94&src=360pic_normal&correct=tu&ancestor=list&cmsid=5096e62e974c6119d4daa4c0e5b186d4&cmras=0&cn=0&gn=0&kn=18&crn=0&bxn=20&fsn=98&cuben=0&pornn=0&manun=0&adstar=0&clw=255#id=0b331b9ae5d43a78e42cb462763d8e94&currsn=0&ps=79&pc=79");
    }
}
