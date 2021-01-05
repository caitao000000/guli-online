package com.bairui.anychat.eduservice.controller;

import com.bairui.anychat.eduservice.entity.EduTeacher;
import com.bairui.anychat.eduservice.entity.vo.TeacherQuery;
import com.bairui.anychat.eduservice.service.EduTeacherService;
import com.bairui.common.utils.Resp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author caitao
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/eduservice/eduteacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    EduTeacherService teacherService;

    @RequestMapping("findAll")
    public Resp findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return Resp.ok().data("list",list);
    }
    // 2.删除讲师
    @DeleteMapping("{id}")
    public Resp removeById(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return Resp.ok();
        }else {
            return Resp.error();
        }
    }

    //  分页查询讲师
    @GetMapping("pageTeacher/{current}/{limmit}")
    public Resp pageTeacher(@PathVariable long current,@PathVariable long limmit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limmit);
        //调用分页方法
        teacherService.page(pageTeacher, null);
        //获取总记录数和分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return Resp.ok().data("total",total).data("rows",records);
    }

    //条件+ 分页查询讲师
    @PostMapping("pageTeacherCondition/{current}/{limmit}")
    public Resp  pageTeacherCondition(@PathVariable long current,
                                      @PathVariable long limmit,
                                      @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(current,limmit);
        teacherService.pageQuery(pageTeacher,teacherQuery);
        //获取总记录数和分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return Resp.ok().data("total",total).data("rows",records);
    }

    // 添加讲师
    @PostMapping("addTeacher")
    public Resp addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.save(eduTeacher);
        if(flag){
            return Resp.ok();
        }else{
            return Resp.error();
        }
    }

    // 根据id 查询讲师
    @RequestMapping("getTeacher/{id}")
    public Resp getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return Resp.ok().data("teacher",eduTeacher);
    }

    // 修改讲师
    @PostMapping("updateTeacher")
    public Resp updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag){
            return Resp.ok();
        }else{
            return Resp.error();
        }
    }


}

