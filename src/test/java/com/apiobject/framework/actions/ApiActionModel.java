package com.apiobject.framework.actions;

import com.apiobject.framework.global.GlobalVariables;
import com.apiobject.framework.utils.PlaceholderUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


/**
 * @program: ApiActionModel
 * @description: 接口动作对象
 * @author: zhuruiqi
 * @create: 2021-07-01 11:24
 **/

@Data
public class ApiActionModel {
    private String url;

    private String method="get";
    private String post;
    private String get;

    private String contentType;
    private HashMap<String, String> headers;

    private HashMap<String, String> query;
    private ArrayList<String> formalParam;
    private HashMap<String, String> actionVariables = new HashMap<>();
    private String body;

    private Response response;


    public Response run(ArrayList<String> actionParameter) {
        String runURL = this.url;
        String runBody = this.body;
        HashMap<String, String> finalQuery = new HashMap<>();

        /**
         * 1、确定请求方式的写法和url
         */
        if (post != null) {
            runURL = post;
            method = "post";
        } else if (get != null) {
            runURL = get;
            method = "get";
        }

        /**
         * 2、请求参数、url中全局变量替换
         */
        if (query != null) {
            finalQuery.putAll(PlaceholderUtils.resolveMap(query, GlobalVariables.getGlobalVariables()));
        }
        //body全局变量替换
        runBody = PlaceholderUtils.resolveString(runBody, GlobalVariables.getGlobalVariables());
        //url全局变量替换
        runURL = PlaceholderUtils.resolveString(runURL, GlobalVariables.getGlobalVariables());

        if (formalParam != null && actionParameter != null && formalParam.size() > 0 && actionParameter.size() > 0) {
            /**
             * 3、根据形参和实参构建内部变量MAP
             */
            for (int index = 0; index < formalParam.size(); index++) {
                actionVariables.put(formalParam.get(index), actionParameter.get(index));
            }

            /**
             * 4、请求、URL中的内部变量替换
             */
            if (query != null) {
                finalQuery.putAll(PlaceholderUtils.resolveMap(query, actionVariables));
            }
            //body全局变量替换
            runBody = PlaceholderUtils.resolveString(runBody, actionVariables);
            //url全局变量替换
            runURL = PlaceholderUtils.resolveString(runURL, actionVariables);
        }

        /**
         * 5、拿到了上面完成了变量替换的请求数据，我们要进行请求并返回结果
         */
        RequestSpecification requestSpecification = given().log().all();
        if (contentType != null) {
            requestSpecification.contentType(contentType);
        }
        if (headers != null) {
            requestSpecification.headers(headers);
        }
        if (finalQuery != null&&finalQuery.size()>0){
            requestSpecification.formParams(finalQuery);
        }else if(runBody!=null){
            requestSpecification.body(runBody);
        }
        response=requestSpecification.request(method,runURL).then().log().all().extract().response();
        return response;
    }

}
