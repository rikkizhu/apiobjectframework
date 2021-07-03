package test;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: test.Test05_TestCaseModelTest
 * @description:
 * @author: zhuruiqi
 * @create: 2021-07-02 15:45
 **/
public class Test05_TestCaseModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test05_TestCaseModelTest.class);

    @BeforeAll
    static void loadApi() {
        ApiLoader.load("src/test/resources/api");
        logger.info("Done!");
    }

    @Test
    public void runTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/test/resources/testcase/creatdepartment.yaml");
        apiTestCaseModel.run();
        logger.info("debugger!");

    }


}
