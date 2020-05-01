package com.kxingyi.blog.controller;

import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.exception.BusinessException;
import com.kxingyi.blog.pojo.Admin;
import com.kxingyi.blog.service.AdminService;
import com.kxingyi.blog.token.UsernamePasswordToken;
import com.kxingyi.blog.utils.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Api(description = "管理员功能模块")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name="username",dataType="String",required=true,value="用户的姓名",defaultValue=""),
            @ApiImplicitParam(paramType = "query", name="password",dataType="String",required=true,value="用户的密码",defaultValue="")
    })
    @RequestMapping(value = "/login")
    public Result<Object> login(Admin admin){
        /*String errMsg = BeanValidator.validator(admin);
        if(!StringUtils.isNullOrEmpty(errMsg)) {
            throw new BusinessException(ResultEnum.ERROR.getCode(),errMsg);
        }*/
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
           throw new BusinessException(ResultEnum.ERROR.getCode(),"用户名或密码错误，请重新登陆");
        }

        //登陆成功
        Serializable sessionId = subject.getSession().getId();
        Map<String,Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }
    @RequestMapping(value = "/getAdmin", method = RequestMethod.GET)
    public Result<Admin> getAdmin(){
        Admin admin = adminService.getAdmin();
        return new Result<Admin>(admin);
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public Result<Admin> updateInfo(Admin admin){
        adminService.updateInfo(admin);
        return new Result<Admin>("更新成功");
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public Result<Admin> updatePassword(Admin admin){
        adminService.updatePassword(admin);
        return new Result<Admin>("更新成功");
    }
}
