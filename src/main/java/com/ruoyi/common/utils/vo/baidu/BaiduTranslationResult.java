package com.ruoyi.common.utils.vo.baidu;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "翻译")
public class BaiduTranslationResult {

    private String from;
    private String to;
    private List<Translation> transResult;
}
