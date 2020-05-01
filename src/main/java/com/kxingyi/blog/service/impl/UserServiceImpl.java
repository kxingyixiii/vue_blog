package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.enums.StateEnum;
import com.kxingyi.blog.exception.BusinessException;
import com.kxingyi.blog.mapper.UserMapper;
import com.kxingyi.blog.pojo.User;
import com.kxingyi.blog.service.UserService;
import com.kxingyi.blog.utils.Md5Utils;
import com.kxingyi.blog.utils.model.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        User byId = userMapper.getById(id);
        if(byId == null){
            throw new BusinessException("不存在该用户");
        }
        if(byId.getDeleted().equals(1)){
            throw new BusinessException("不能重复删除");
        }
        byId.setDeleted(StateEnum.DELETED.getCode());
        userMapper.update(byId);
    }

    @Override
    public Page<User> getByPage(Page<User> page) {

        List<User> byPage = userMapper.getByPage(page);
        int countByPage = userMapper.getCountByPage(page);
        page.setList(byPage);
        page.setTotalCount(countByPage);
        return page;
    }

    @Override
    public void resetPwd(List<Integer> userIds) {
        // JDK8新特性：集合的流式操作，这里使用到了Lambda表达式
        // 如果仅仅是遍历的话，增强for循环的效率比流式操作要高
        // 但是可读性没有流式操作高。
        // 当遍历时操作的业务逻辑较多时，流式操作的性能比普通的for循环要高
        List<User> userList = userMapper.getByIds(userIds);
        userList.forEach(e -> {
            e.setPassword(Md5Utils.toMD5("123456"));
            // 在for循环中修改数据 case when
            userMapper.update(e);
        });
    }

    @Override
    public void register(User user) {
        User u = userMapper.getByUsername(user.getUsername());
        // 如果存在，提示用户已存在
        if (u != null) {
            throw new BusinessException(ResultEnum.PARAMS_ERROR.getCode(), "用户已存在！");
        }
        userMapper.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateInfo(user);
    }

    @Override
    public Map<String, Object> getCommentAndCollectionCount() {
        return null;
    }
}
