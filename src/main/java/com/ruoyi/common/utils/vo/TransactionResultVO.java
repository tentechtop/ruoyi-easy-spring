package com.ruoyi.common.utils.vo;


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
public class TransactionResultVO {

    private int code;

    private long id;

    private String data;

    private List<String> alternatives;

}
