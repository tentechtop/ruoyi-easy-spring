package com.ruoyi.project.official.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ruoyi.project.official.domain.Course;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends BaseMapper<Course> {


    @Select("select max(course_id) from off_course")
    Integer getMaxId();


}
