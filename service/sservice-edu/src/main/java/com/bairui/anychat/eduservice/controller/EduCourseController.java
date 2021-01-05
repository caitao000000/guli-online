package com.bairui.anychat.eduservice.controller;


import com.bairui.anychat.eduservice.entity.vo.CourseInfoForm;
import com.bairui.anychat.eduservice.service.EduCourseService;
import com.bairui.common.utils.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author caitao
 * @since 2020-12-08
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("/addCourseInfoForm")
    public Resp addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if(!StringUtils.isEmpty(courseId)){
            return Resp.ok().data("courseid",courseId);
        }else{
            return Resp.error().message("保存课程信息失败");
        }
    }

}

