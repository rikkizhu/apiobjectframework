package test;

import com.apiobject.framework.actions.ApiActionModel;
import com.apiobject.framework.api.ApiObjectModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: test.Test07_HarToYamlTest
 * @description: 自动生成接口对象
 * @author: zhuruiqi
 * @create: 2021-07-03 16:18
 **/
public class Test07_HarToYamlTest {
    public static final Logger logger = LoggerFactory.getLogger(Test07_HarToYamlTest.class);

    @Test
    public void Test07_HarToYamlTest() throws HarReaderException, IOException {
        HarReader harReader = new HarReader();
        Har har = harReader.readFromFile(new File("src/test/resources/har/har_demo.har"));
        logger.info("debugger!");

        ApiObjectModel apiObjectModel = new ApiObjectModel();
        ApiActionModel apiActionModel = new ApiActionModel();
        HashMap<String, ApiActionModel> actions = new HashMap<>();
        HashMap<String, String> queryMap = new HashMap<>();

        har.getLog().getEntries().forEach(entrie -> {
            HarRequest harRequest = entrie.getRequest();
            harRequest.getQueryString().forEach(query -> {
                queryMap.put(query.getName(), query.getValue());
            });

            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();

            apiActionModel.setQuery(queryMap);
            if (method.equals("GET")) {
                apiActionModel.setGet(url);
            } else {
                apiActionModel.setPost(url);
            }

            actions.put(getRequestName(url), apiActionModel);

        });

        apiObjectModel.setName("tokenhelper_har");
        apiObjectModel.setActions(actions);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("src/test/resources/har/tokenhelper_har.yaml"), apiObjectModel);

    }

    public String getRequestName(String url) {
        String[] subUrl = url.split("\\u003F")[0].split("/");
        return subUrl[subUrl.length - 1];
    }

    @Test
    void harToYamlRunTest() throws IOException {
        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/har/tokenhelper_har.yaml");
        apiObjectModel.getActions().get("gettoken").run(null);
    }



}
