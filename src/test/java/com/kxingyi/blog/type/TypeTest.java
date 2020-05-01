package com.kxingyi.blog.type;

import com.kxingyi.blog.mapper.TypeMapper;
import com.kxingyi.blog.pojo.Type;
import com.kxingyi.blog.utils.IdWorker;
import com.kxingyi.blog.utils.model.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TypeTest {
@Autowired
private IdWorker idWorker;
@Autowired
private TypeMapper typeMapper;

    @Test
    public void TestSaveType(){
        Type type = new Type();
        type.setTypeName("c分类");
        type.setTypeId((int) idWorker.nextId());
        type.setEnable(0);
        List list = new ArrayList();
        list.add(1);
        typeMapper.insert(type);
    }

    @Test
    public void TestGetAllTypeByParams(){
        Page page = new Page();
        page.setCurrentPage(1);
        page.setPageSize(50);
        page.setSortMethod("asc");
        page.setSortColumn("typeBlogCount");

        List<Type> all = typeMapper.getAll(page);

        for (Type type : all) {
            System.out.println(type.toString());
        }

    }

    @Test
    public void TestGetTypeList(){

        List<Type> all = typeMapper.getTypeList();

        for (Type type : all) {
            System.out.println(type.toString());
        }

    }
    @Test
    public void updateType(){
        Type type = new Type();
        type.setTypeId(8);
        type.setEnable(1);
        type.setDeleted(1);
        type.setTypeName("TEST");
        type.setTypeBlogCount(9000);
        typeMapper.update(type);

    }

    @Test
    public void deleteTypeById(){
        typeMapper.deleteById(8);

    }
}
