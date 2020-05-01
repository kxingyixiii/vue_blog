package com.kxingyi.blog.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class ParamsUtils {
    /**
     *@QualifiedClassName:com.kxingyi.blog.utils.ParamsUtils
     *@ClassName:parseJsonObjectFromParams
     *@Description:把用@request获取的参数转成jsonObject
     *@Author:byliu
     *@Date:2020/4/24 17:05
     *@Paramter:[params]
     *@Return:com.alibaba.fastjson.JSONObject
    */
    private static JSONObject parseJsonObjectFromParams(String params) throws UnsupportedEncodingException {
        params = URLEncoder.encode(params, "utf-8");
        params = URLDecoder.decode(params, "utf-8");
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotEmpty(params)) {
            String[] paramsArray = StringUtils.split(params, "&");
            if (paramsArray != null && paramsArray.length > 0) {
                for (String param : paramsArray) {
                    int idx = StringUtils.indexOf(param, "=");
                    String queryParam = StringUtils.substring(param, 0, idx);
                    String queryValue = StringUtils.substring(param, idx + 1);
                    jsonObject.put(queryParam, queryValue);
                }
            }
        }
        return jsonObject;
    }
    /**
     *@QualifiedClassName:com.kxingyi.blog.utils.ParamsUtils
     *@ClassName:getValueByParams
     *@Description:从request获取参数，默认先取query的参数，没有再从header取
     *@Author:byliu
     *@Date:2020/4/24 17:06
     *@Paramter:[request, params]
     *@Return:java.lang.String
    */
    private static String getValueByParams(HttpServletRequest request, String params) {
        String param = request.getParameter(params);
        if (StringUtils.isBlank(param)) {
            param = request.getHeader(params);
        }
        return param;
    }
}
