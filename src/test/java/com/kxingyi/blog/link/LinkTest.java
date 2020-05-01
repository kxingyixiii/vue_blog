package com.kxingyi.blog.link;

import com.kxingyi.blog.mapper.LinkMapper;
import com.kxingyi.blog.pojo.Link;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinkTest {

    @Autowired
    private LinkMapper linkMapper;

    @Test
    public void testSaveLink(){
        Link link = new Link();
        link.setLinkName("百度");
        link.setLinkUrl("www.baidu.com");
        linkMapper.save(link);
    }
}
