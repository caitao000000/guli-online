package com.bairui.anychat.eduservice.service;

import com.bairui.anychat.eduservice.entity.EduCourse;
import com.bairui.anychat.eduservice.entity.vo.CourseInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author caitao
 * @since 2020-12-08
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);
}
