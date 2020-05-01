package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.enums.StateEnum;
import com.kxingyi.blog.exception.BusinessException;
import com.kxingyi.blog.mapper.LinkMapper;
import com.kxingyi.blog.pojo.Link;
import com.kxingyi.blog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class LinkServiceImpl implements LinkService {
@Autowired
private LinkMapper linkMapper;
    @Override
    public void save(Link link) {
        Link byId = linkMapper.getById(link.getLinkId());
        if(null != byId ){
            throw new BusinessException("违反唯一约束");
        }
        linkMapper.save(link);
    }

    @Override
    public void update(Link link) {
        linkMapper.update(link);
    }

    @Override
    public Link getById(Integer id) {
        return linkMapper.getById(id);
    }

    @Override
    public List<Link> getAll() {
        return linkMapper.getAll();
    }

    @Override
    public void deleteById(Integer id) {
        Link byId = linkMapper.getById(id);
        if(null == byId || StateEnum.DELETED.getCode().equals(byId.getDeleted())){
            throw new BusinessException("已经删除了[" + id + "],请勿多次提交");
        }
        linkMapper.deleteById(id);
    }
}
