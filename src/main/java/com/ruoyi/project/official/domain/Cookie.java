package com.ruoyi.project.official.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("off_cookie")
public class Cookie {

    @ApiModelProperty("会话ID")
    @TableId(value = "cookie_id",type = IdType.AUTO)
    private Integer cookieId;

    @ApiModelProperty("会话唯一标志")
    @TableField(value = "cookie_uuid")
    private String cookieUuid;

    @ApiModelProperty("是否过期")
    @TableField("is_expires")
    private Integer isExpires ;

    @ApiModelProperty(name = "创建时间",notes = "")
    private LocalDateTime createTime ;
}
