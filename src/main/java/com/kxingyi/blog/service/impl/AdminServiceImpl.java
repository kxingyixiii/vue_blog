package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.mapper.AdminMapper;
import com.kxingyi.blog.pojo.Admin;
import com.kxingyi.blog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin getByUsername(String username) {
        return adminMapper.getByUsername(username);
    }

    @Override
    public Admin getAdmin() {
        Admin admin = adminMapper.getAdmin();
        admin.setPassword(null);
        return admin;
    }

    @Override
    public void updateInfo(Admin admin) {
        adminMapper.update(admin);
    }

    @Override
    public void updatePassword(Admin admin) {
        Admin adminInDB = adminMapper.getAdmin();
        adminInDB.setPassword(admin.getPassword());
        adminMapper.update(adminInDB);
    }
}
