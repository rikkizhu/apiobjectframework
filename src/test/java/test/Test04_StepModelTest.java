package test;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.steps.AssertModel;
import com.apiobject.framework.steps.StepModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: test.Test04_StepModelTest
 * @description:
 * @author: zhuruiqi
 * @create: 2021-07-02 14:55
 **/
public class Test04_StepModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test04_StepModelTest.class);

    @BeforeAll
    static void loadApi(){
        ApiLoader.load("src/test/resources/api");
        logger.info("Done!");
    }

    @Test
    void runTest() {
        //实参
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc8a676fe2024f770");
        actualParameter.add("Ns-cXO_h7-O0p32rDpMR7Uah4vDRCXRHd3NbHJjZLBI");

        //断言
        ArrayList<AssertModel> asserts = new ArrayList<>();
        AssertModel assertModel = new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setReason("getToken错误码校验01！");
        assertModel.setMatcher("equalTo");
        assertModel.setExpect(0);
        asserts.add(assertModel);

        //save
        HashMap<String,String> save = new HashMap<>();
        save.put("accesstoken","access_token");

        //saveGlobal
        HashMap<String,String> saveGlobal = new HashMap<>();
        saveGlobal.put("accesstoken","access_token");

        StepModel stepModel = new StepModel();
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.setActualParameter(actualParameter);
        stepModel.setAsserts(asserts);
        stepModel.setSave(save);
        stepModel.setSaveGlobal(saveGlobal);
        stepModel.run(null);
        logger.info("debbuger!");
    }

}
