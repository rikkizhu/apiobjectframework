package com.apiobject.framework.api;

import com.apiobject.framework.actions.ApiActionModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: com.apiobject.framework.api.ApiObjectModel
 * @description: 接口对象类
 * @author: zhuruiqi
 * @create: 2021-07-01 20:15
 **/

@Data
public class ApiObjectModel {
    private String name;
    private HashMap<String,ApiActionModel>  actions;

    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path),ApiObjectModel.class);
    }




}
