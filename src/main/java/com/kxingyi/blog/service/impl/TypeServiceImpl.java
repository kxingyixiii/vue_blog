package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.exception.BusinessException;
import com.kxingyi.blog.mapper.TypeMapper;
import com.kxingyi.blog.pojo.Type;
import com.kxingyi.blog.service.TypeService;
import com.kxingyi.blog.utils.model.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {
@Autowired
private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        Type typeInDB = typeMapper.getByName(type.getTypeName());
        if(typeInDB != null){
            throw new BusinessException("该分类已存在");
        }
        typeMapper.insert(type);
    }

    @Override
    public List<Type> getAll(Page<Object> page) {
        return typeMapper.getAll(page);
    }

    @Override
    public List<Type> getTypeList() {
        return typeMapper.getTypeList();
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void enableById(Integer id) {
        Type type = typeMapper.getById(id);
        if(1 == type.getEnable()){
            throw new BusinessException("已经启用分类[" + type.getTypeName() + "],请勿重复启用");
        }
        type.setEnable(1);
        typeMapper.update(type);
    }

    @Override
    public void disableById(Integer id) {
        Type type = typeMapper.getById(id);
        if(0 == type.getEnable()){
            throw new BusinessException("已经禁用分类[" + type.getTypeName() + "],请勿重复禁用");
        }
        type.setEnable(0);
        typeMapper.update(type);
    }

    @Override
    public void deleteById(Integer id) {
        Type type = typeMapper.getById(id);
        if(null == type){
            throw new BusinessException("已经删除了[" + type.getTypeName() + "],请勿多次提交");
        }
        type.setDeleted(1);
        typeMapper.update(type);
    }

    @Override
    public Type getById(Integer id) {
        return typeMapper.getById(id);
    }
}
