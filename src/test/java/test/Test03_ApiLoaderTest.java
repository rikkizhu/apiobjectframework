package test;

import com.apiobject.framework.global.ApiLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @program: test.Test03_ApiLoaderTest
 * @description:
 * @author: zhuruiqi
 * @create: 2021-07-01 21:28
 **/
public class Test03_ApiLoaderTest {
    public static final Logger logger = LoggerFactory.getLogger(Test03_ApiLoaderTest.class);

    @BeforeAll
    static void loadApi(){
        ApiLoader.load("src/test/resources/api");
        logger.info("Done!");
    }

    @Test
    void loadTest(){
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc8a676fe2024f770");
        actualParameter.add("Ns-cXO_h7-O0p32rDpMR7Uah4vDRCXRHd3NbHJjZLBI");

        ApiLoader.getAction("tokenhelper","getToken").run(actualParameter);
    }
}
