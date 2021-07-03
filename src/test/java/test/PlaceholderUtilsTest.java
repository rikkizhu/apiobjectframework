package test;

import com.apiobject.framework.utils.PlaceholderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: test.PlaceholderUtilsTest
 * @description:
 * @author: zhuruiqi
 * @create: 2021-07-01 12:26
 **/
public class PlaceholderUtilsTest {

    @Test
    void resolveStringTest() {
        String demo = "a=${paramA},b=${paramB}";

        HashMap<String, String> replaceStr = new HashMap<>();
        replaceStr.put("paramA", "A");
        replaceStr.put("paramB", "B");

        String result = PlaceholderUtils.resolveString(demo, replaceStr);
        System.out.println(result);
    }

    @Test
    void resolveStringTest2(){
        String demo = "a=${paramA},b=${paramB}";

        HashMap<String, String> replaceStr = new HashMap<>();
        replaceStr.put("x", "A");
        replaceStr.put("y", "B");

        String result = PlaceholderUtils.resolveString(demo, replaceStr);
        System.out.println(result);
    }

    @Test
    void resolveMapTest() {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "${departmentId}");
        map.put("name", "${name}");

        HashMap<String,String> parameter = new HashMap<>();
        parameter.put("departmentId","111");
        parameter.put("name","this is name");

        HashMap<String, String> stringStringHashMap = PlaceholderUtils.resolveMap(map, parameter);
        System.out.println(stringStringHashMap);
    }


}
