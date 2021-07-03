package com.apiobject.framework.steps;

import lombok.Data;

/**
 * @program: com.apiobject.framework.steps.AssertModel
 * @description: 存储断言数据对象
 * @author: zhuruiqi
 * @create: 2021-07-01 22:23
 **/
@Data
public class AssertModel {
    private String actual;
    private String matcher;
    private Integer expect;
    private String reason;
}
