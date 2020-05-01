package com.kxingyi.blog.controller;


import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.enums.StateEnum;
import com.kxingyi.blog.pojo.User;
import com.kxingyi.blog.service.UserService;
import com.kxingyi.blog.token.UsernamePasswordToken;
import com.kxingyi.blog.utils.model.Page;
import com.kxingyi.blog.utils.model.Result;
import com.kxingyi.blog.utils.StringUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *@QualifiedClassName:com.kxingyi.blog.controller.UserController
 *@ClassName:用户模块
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/5/1 15:13
 *@Paramter:
 *@Return:
*/
@Api(description = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody User user) {
        userService.save(user);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody User user) {
        userService.update(user);
        return new Result<>("修改成功！");
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public Result<Object> updateInfo(@RequestBody User user) {
        userService.updateInfo(user);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<User> get(@PathVariable Integer id) {
        User user = userService.getById(id);
        return new Result<>(user);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<User>> getByPage(@RequestBody Page<User> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"sex", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = userService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 重置密码
     *
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.PUT)
    public Result<Object> resetPwd(@RequestBody List<Integer> userIds) {
        userService.resetPwd(userIds);
        return new Result<>("重置完毕！");
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<Object> register(@RequestBody User user) {
        userService.register(user);
        return new Result<>("注册成功！");
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Object> login(@RequestBody User user) {
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NOT_NULL.getCode(), "用户名或密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(user.getUsername(), user.getPassword(), StateEnum.NOT_ADMIN.getCode());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            return new Result<>(ResultEnum.PARAMS_NOT_NULL.getCode(), "用户名或密码错误！");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        User u = (User) subject.getPrincipal();
        u.setPassword("");
        u.setDeleted(null);
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        returnMap.put("user", u);
        return new Result<>(returnMap);
    }

    /**
     * 查询当前用户的评论数和收藏数
     * @return
     */
    @RequestMapping(value = "/commentAndCollectionCount", method = RequestMethod.GET)
    public Result<Map<String, Object>> commentAndCollectionCount() {
        Map<String, Object> countMap = userService.getCommentAndCollectionCount();
        return new Result<>(countMap);
    }

}
