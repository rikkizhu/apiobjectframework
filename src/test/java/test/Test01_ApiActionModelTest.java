package test;

import com.apiobject.framework.actions.ApiActionModel;
import com.apiobject.framework.global.GlobalVariables;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: test.Test01_ApiActionModelTest
 * @description: ApiActionModel单元测试
 * @author: zhuruiqi
 * @create: 2021-07-01 16:17
 **/
public class Test01_ApiActionModelTest {
    @Test
    void runTest() {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("wwc8a676fe2024f770");
        actualParameter.add("Ns-cXO_h7-O0p32rDpMR7Uah4vDRCXRHd3NbHJjZLBI");

        ApiActionModel apiActionModel = new ApiActionModel();
        apiActionModel.setUrl("https://qyapi.weixin.qq.com/cgi-bin/${x}");

        HashMap<String,String> globalVariables = new HashMap<>();
        globalVariables.put("x","gettoken");
        GlobalVariables.setGlobalVariables(globalVariables);

        ArrayList<String> formalParameter = new ArrayList<>();
        formalParameter.add("corpid");
        formalParameter.add("corpsecret");
        apiActionModel.setFormalParam(formalParameter);

        HashMap<String,String> query = new HashMap<>();
        query.put("corpid","${corpid}");
        query.put("corpsecret","${corpsecret}");
        apiActionModel.setQuery(query);

        Response response = apiActionModel.run(actualParameter);

    }
}
