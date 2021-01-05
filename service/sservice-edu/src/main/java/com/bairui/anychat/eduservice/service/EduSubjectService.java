package com.bairui.anychat.eduservice.service;

import com.bairui.anychat.eduservice.entity.EduSubject;
import com.bairui.anychat.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author caitao
 * @since 2020-11-19
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getOneAndTwoSubject();
}
