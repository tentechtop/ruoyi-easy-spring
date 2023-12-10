package com.ruoyi.common.utils.vo.baidu;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "翻译")
public class Translation {
    
    private String src;
    private String dst;

}
