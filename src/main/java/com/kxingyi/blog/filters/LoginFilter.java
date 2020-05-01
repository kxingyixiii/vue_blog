package com.kxingyi.blog.filters;

import com.alibaba.fastjson.JSONObject;
import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.utils.model.Result;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 *@QualifiedClassName:com.kxingyi.blog.filters.LoginFilter
 *@ClassName:登录过滤器
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/21 16:55
 *@Paramter:
 *@Return:
*/
public class LoginFilter extends UserFilter {

    /**
     * 这个方法用于处理未登录时页面重定向的逻辑
     * 因此，只要进入了这个方法，就意味着登录失效了
     * 我们只需要在这个方法里，给前端返回一个登录失效的状态码即可
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(new Result<>(ResultEnum.NOT_LOGIN)));
    }
}
