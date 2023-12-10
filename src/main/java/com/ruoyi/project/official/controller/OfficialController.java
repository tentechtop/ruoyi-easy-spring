package com.ruoyi.project.official.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.official.domain.Course;
import com.ruoyi.project.official.mapper.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/official/page")
public class OfficialController extends BaseController {


    @Autowired
    private CourseDao courseDao;

    @GetMapping("/test")
    public Course test(){
        Course course = courseDao.selectById(1);
        return course;
    }



}
