package com.bairui.anychat.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.bairui.anychat.eduservice.entity.EduSubject;
import com.bairui.anychat.eduservice.entity.excel.SubjectData;
import com.bairui.anychat.eduservice.entity.subject.OneSubject;
import com.bairui.anychat.eduservice.entity.subject.TwoSubject;
import com.bairui.anychat.eduservice.listener.SubjectExcelListener;
import com.bairui.anychat.eduservice.mapper.EduSubjectMapper;
import com.bairui.anychat.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author caitao
 * @since 2020-11-19
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        InputStream in = null;
        try {
            //获取文件输入流
            in = file.getInputStream();
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<OneSubject> getOneAndTwoSubject() {
        // 查询所有的一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        // 查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //封装最终返回的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        // 封装一级分类
        for (int i = 0; i < oneSubjectList.size() ; i++) {
            // 得到每个EduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);
            // 把EduSubject 对象中的属性，赋值到OneSubject
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);

            // 封装二级分类
            List<TwoSubject> twoFinalSubject = new ArrayList<>();
            for (int m = 0; m < twoSubjectList.size(); m++) {
                // 得到每个二级分类
                EduSubject tSubject = twoSubjectList.get(m);
                //判断二级分类的parentid和一级分类的id是否一样
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubject.add(twoSubject);
                }
            }
            // 把一级分类下面的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubject);
        }
        return finalSubjectList;
    }
}
