package com.bairui.anychat.eduservice.service.impl;

import com.bairui.anychat.eduservice.entity.EduTeacher;
import com.bairui.anychat.eduservice.entity.vo.TeacherQuery;
import com.bairui.anychat.eduservice.mapper.EduTeacherMapper;
import com.bairui.anychat.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author caitao
 * @since 2020-11-10
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageTeacher, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //teacherQuery为空 查询所有
        if (teacherQuery == null){
            //baseMapper.selectPage(pageTeacher, queryWrapper);
            baseMapper.selectPage(pageTeacher,queryWrapper);
            return;
        }
        // 获取参数中的数据
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        queryWrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageTeacher,queryWrapper);
    }
}
