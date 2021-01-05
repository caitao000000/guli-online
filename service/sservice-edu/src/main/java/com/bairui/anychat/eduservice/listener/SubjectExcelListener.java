package com.bairui.anychat.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bairui.anychat.eduservice.entity.EduSubject;
import com.bairui.anychat.eduservice.entity.excel.SubjectData;
import com.bairui.anychat.eduservice.service.EduSubjectService;
import com.bairui.anychat.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象  不能进行数据库操作
    public EduSubjectService eduSubjectService;
    public SubjectExcelListener() { }
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //
        if(subjectData == null ){
            throw new GuliException(20001,"Excel中没有数据");
        }
        //添加一级分类
        EduSubject oneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(oneSubject == null){
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(oneSubject);
        }
        //添加二级分类
        String pid = oneSubject.getId();
        EduSubject twoSubject = this.existOTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if(twoSubject == null ){
            twoSubject = new EduSubject();
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            twoSubject.setParentId(pid);
            eduSubjectService.save(twoSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(queryWrapper);
        return  oneSubject;
    }
    //判断二级分类不能重复添加
    private EduSubject existOTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        EduSubject twoSubject = eduSubjectService.getOne(queryWrapper);
        return  twoSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
