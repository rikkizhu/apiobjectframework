package test;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.testcase.ApiTestCaseModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @program: test.Test06_ApiParameterizedTest
 * @description:
 * @author: zhuruiqi
 * @create: 2021-07-02 16:04
 **/
public class Test06_ApiParameterizedTest {
    public static final Logger logger = LoggerFactory.getLogger(Test06_ApiParameterizedTest.class);

    @ParameterizedTest(name = "{index}{1}")
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel, String name, String description) {
        logger.info("【用例开始执行】");
        logger.info("【用例名称】" + name);
        logger.info("【用例描述】" + description);
        apiTestCaseModel.run();
    }

    static List<Arguments> apiTest() {
        ApiLoader.load("src/test/resources/api");
        List<Arguments> testcase = new ArrayList<>();
        String testCaseDir = "src/test/resources/testcase";

        Arrays.stream(new File(testCaseDir).list()).forEach(name -> {
            String path = testCaseDir + "/" + name;
            try {
                ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load(path);
                testcase.add(arguments(apiTestCaseModel, apiTestCaseModel.getName(), apiTestCaseModel.getDescription()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        return testcase;
    }

}
