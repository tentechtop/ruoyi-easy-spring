package com.ruoyi.project.official.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ruoyi.project.official.domain.Cookie;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieDao extends BaseMapper<Cookie> {

    @Select("select MAX(cookie_id) from off_cookie")
    Integer getMaxId();
}
