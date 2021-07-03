package com.apiobject.framework.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: PlaceholderUtils
 * @description: 占位符替换工具类
 * @author: zhuruiqi
 * @create: 2021-07-01 11:55
 **/
public class PlaceholderUtils {
    public static final Logger logger = LoggerFactory.getLogger(PlaceholderUtils.class);

    public static final String PLACEHOLDER_PREFIX = "${";
    public static final String PLACEHOLDER_SUFFIX = "}";

    public static String resolveString(String text, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || text == null || text.isEmpty()) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text);
        /**
         * 计算变量名开始位置
         */
        int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
        while (startIndex != -1) {
            /**
             * 计算变量名结束位置
             */
            int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
            if (endIndex != -1) {
                /**
                 * 取出要替换的变量名
                 */
                String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
                int nextIndex = endIndex + PLACEHOLDER_SUFFIX.length();
                try {
                    /**
                     * 取出变量map中的真实值
                     */
                    String propVal = parameter.get(placeholder);
                    if (propVal != null) {
                        /**
                         * 替换变量
                         */
                        buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), propVal);
                        nextIndex = startIndex + propVal.length();
                    } else {
                        logger.info("Could not resolve placeholder '" + placeholder + "' in [" + text + "] ");
                    }
                } catch (Exception e) {
                    logger.info("Could not resolve placeholder '" + placeholder + "' in [" + text + "]: " + e);
                }
                startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
            } else {
                startIndex = -1;
            }
        }
        return buf.toString();
    }


    public static HashMap<String, String> resolveMap(HashMap<String, String> map, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || map == null || map.isEmpty()) {
            return map;
        }
        HashMap<String, String> returnMap = new HashMap<>();
        map.forEach((key, value) -> {
            if (value.contains(PLACEHOLDER_PREFIX)) {
                returnMap.put(key, resolveString(value, parameter));
            }
        });
        return returnMap;
    }

    public static ArrayList<String> resolveList(ArrayList<String> list, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || list == null || list.isEmpty()) {
            return list;
        }

        ArrayList<String> returnList = new ArrayList<>();
        list.forEach(str -> {
            if (str.contains(PLACEHOLDER_PREFIX)) {
                returnList.add(resolveString(str, parameter));
            } else {
                returnList.add(str);
            }
        });
        return returnList;
    }
}



