package com.apiobject.framework.steps;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.global.GlobalVariables;
import com.apiobject.framework.utils.PlaceholderUtils;
import io.restassured.response.Response;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.function.Executable;


/**
 * @program: com.apiobject.framework.steps.StepModel
 * @description: step对象
 * @author: zhuruiqi
 * @create: 2021-07-01 22:21
 **/

@Data
public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);


    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;
    private HashMap<String, String> save;
    private HashMap<String, String> saveGlobal;

    private ArrayList<String> finalActualParameter = new ArrayList<>();
    private HashMap<String, String> stepVariable = new HashMap<>();
    private ArrayList<Executable> assertList = new ArrayList<>();
    private StepResult stepResult = new StepResult();

    public StepResult run(HashMap<String, String> testCaseVariables) {
        /**
         * 实参占位符替换
         */
        if (actualParameter != null) {
            finalActualParameter.addAll(PlaceholderUtils.resolveList(actualParameter, testCaseVariables));
        }

        /**
         * 2、执行action
         */
        Response response = ApiLoader.getAction(api, action).run(finalActualParameter);

        /**
         * 3、存save
         */
        if (save != null) {
            save.forEach((variablesName, path) -> {
                String value = response.path(path).toString();
                stepVariable.put(variablesName, value);
                logger.info("step变量更新：" + stepVariable);
            });
        }

        /**
         * 4、存 saveGlobal
         */
        if (saveGlobal != null) {
            saveGlobal.forEach((variablesName, path) -> {
                String value = response.path(path);
                GlobalVariables.getGlobalVariables().put(variablesName, value);
                logger.info("全局变量更新：" + GlobalVariables.getGlobalVariables());
            });
        }

        /**
         * 5、存储断言结果
         */
        if(asserts != null){
            asserts.stream().forEach(assertModel -> {
                assertList.add(()->{assertThat(assertModel.getReason(),response.path(assertModel.getActual().toString()),equalTo(assertModel.getExpect()));});
            });
        }

        /**
         * 6、组装result
         */
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariable);
        return stepResult;

    }

}
