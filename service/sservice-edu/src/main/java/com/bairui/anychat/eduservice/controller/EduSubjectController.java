package com.bairui.anychat.eduservice.controller;


import com.bairui.anychat.eduservice.entity.subject.OneSubject;
import com.bairui.anychat.eduservice.service.EduSubjectService;
import com.bairui.common.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author caitao
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/addsubject")
    public Resp addSubject(MultipartFile file){
        //1获取上传的excel文件 MultipartFile
        eduSubjectService.saveSubject(file,eduSubjectService);
        return Resp.ok();
    }
    // 课程分类列表（数型结构）
    @GetMapping ("/getAllSubject")
    public Resp getAllSubject(){
        List<OneSubject> list = eduSubjectService.getOneAndTwoSubject();
        return Resp.ok().data("list",list);
    }
}

