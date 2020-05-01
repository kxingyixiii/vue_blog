package com.kxingyi.blog.admin;

import com.kxingyi.blog.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class adminTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testGetUserByName(){
        System.out.println(adminMapper.getByUsername("1"));
    }
}
