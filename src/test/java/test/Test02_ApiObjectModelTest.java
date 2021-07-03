package test;

import com.apiobject.framework.api.ApiObjectModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: test.Test02_ApiObjectModelTest
 * @description:
 * @author: zhuruiqi
 * @create: 2021-07-01 20:26
 **/
public class Test02_ApiObjectModelTest {
    @Test
    void loadTest() throws IOException {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc8a676fe2024f770");
        actualParameter.add("Ns-cXO_h7-O0p32rDpMR7Uah4vDRCXRHd3NbHJjZLBI");

        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/api/tokenhelper.yaml");
        apiObjectModel.getActions().get("getToken").run(actualParameter);
    }
}
