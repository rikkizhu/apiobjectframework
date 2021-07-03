package com.apiobject.framework.global;

import java.util.HashMap;

/**
 * @program: GlobalVariables
 * @description: 用来存储全局变量
 * @author: zhuruiqi
 * @create: 2021-07-01 15:00
 **/

public class GlobalVariables {
    static private HashMap<String,String> globalVariables = new HashMap<>();

    public static HashMap<String, String> getGlobalVariables() {
        return globalVariables;
    }

    public static void setGlobalVariables(HashMap<String, String> globalVariables) {
        GlobalVariables.globalVariables = globalVariables;
    }
}
