package com.kxingyi.blog.log;

import com.kxingyi.blog.mapper.LogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class logTest {
    @Autowired
    private LogMapper logMapper;
    @Test
    public void  testDeleteLogByIds(){
        List ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        logMapper.deleteByIds(ids);

    }
}
