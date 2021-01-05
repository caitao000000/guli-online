package com.bairui.anychat.eduservice.service;

import com.bairui.anychat.eduservice.entity.EduTeacher;
import com.bairui.anychat.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author caitao
 * @since 2020-11-10
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery);
}
