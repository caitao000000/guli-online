package com.bairui.anychat.eduservice.service.impl;

import com.bairui.anychat.eduservice.entity.EduCourse;
import com.bairui.anychat.eduservice.entity.EduCourseDescription;
import com.bairui.anychat.eduservice.entity.vo.CourseInfoForm;
import com.bairui.anychat.eduservice.mapper.EduCourseDescriptionMapper;
import com.bairui.anychat.eduservice.mapper.EduCourseMapper;
import com.bairui.anychat.eduservice.service.EduCourseDescriptionService;
import com.bairui.anychat.eduservice.service.EduCourseService;
import com.bairui.anychat.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author caitao
 * @since 2020-12-08
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.insert(eduCourse);
        if(result == 0){
            throw new GuliException(30001,"保存课程信息失败");
        }
        String cid = eduCourse.getId();
        // 保存课程描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm,eduCourseDescription);
        //手动设置id
        eduCourseDescription.setId(cid);
        eduCourseDescriptionMapper.insert(eduCourseDescription);
        return cid;
    }
}
