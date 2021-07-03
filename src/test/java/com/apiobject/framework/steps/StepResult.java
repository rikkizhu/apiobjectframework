package com.apiobject.framework.steps;

import lombok.Data;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: com.apiobject.framework.steps.StepResult
 * @description: step run result
 * @author: zhuruiqi
 * @create: 2021-07-01 22:29
 **/

@Data
public class StepResult {
    /**
     * 关心断言和执行后生成的变量
     */
    private ArrayList<Executable> assertList;
    private HashMap<String,String> stepVariables = new HashMap<>();

}
