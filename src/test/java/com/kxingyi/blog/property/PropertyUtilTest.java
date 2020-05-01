package com.kxingyi.blog.property;

import com.kxingyi.blog.utils.property.PropertyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertyUtilTest {

    @Test
    public void testPropertyUtil(){
        PropertyUtil propertyUtil = PropertyUtil.newInstance("application.properties");
        propertyUtil.getProperties("fdfs.pool.test-on-borrow");
    }
}
