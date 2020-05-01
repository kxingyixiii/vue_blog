package com.kxingyi.blog.interceptor;

import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.exception.AuthorizationException;
import com.kxingyi.blog.exception.BlogException;
import com.kxingyi.blog.utils.ShiroUtils;
import com.kxingyi.blog.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@QualifiedClassName:com.kxingyi.blog.interceptor.LoginInterceptor
 *@ClassName:登录拦截器
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/21 16:53
 *@Paramter:
 *@Return:
*/
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 放行的白名单
     */
    private static String[] whiteList = {
            "/admin/login",
            "/user/login",
            "/user/register",
            "/link/list",
            "/music/getList",
            "/about/read",
            "/type/getList",
            "/blog/recomRead",
            "/blog/read",
            "/blog/getTimeLine",
            "/blog/getByPage",
            "/comment/getByBlog",
            "/admin/getAdmin",
            "/swagger-ui.html",
            "/swagger-resources",
            "/v2/api-docs",
            "/webjars/springfox-swagger-ui/",
            "/druid/"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (containsWhiteList(request.getRequestURI())) {
            return true;
        }
        // 获取token
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            // token不为空，获取当前登录用户
            if(!token.equals(ShiroUtils.getSessionId())){
                throw new AuthorizationException("认证失败");
            }
            Object loginUser = ShiroUtils.getLoginUser();
            if (loginUser != null) {
                // 说明token有效
                return true;
            }
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 判断url是否在白名单内
     *
     * @param s
     * @return
     */
    private boolean containsWhiteList(String s) {
        for (String url : whiteList) {
            if (s.contains(url)) {
                return true;
            }
        }
        return false;
    }

}
