package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.enums.StateEnum;
import com.kxingyi.blog.mapper.AboutMapper;
import com.kxingyi.blog.pojo.About;
import com.kxingyi.blog.service.AboutService;
import com.kxingyi.blog.utils.IdWorker;
import com.kxingyi.blog.utils.model.Page;
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
 */
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {
    @Autowired
    private AboutMapper aboutMapper;

    @Autowired
    private IdWorker idWorker;
    @Override
    public void save(About about) {
        about.setAboutId(idWorker.nextId()+"");
        aboutMapper.save(about);
    }

    @Override
    public void update(About about) {
        aboutMapper.updateById(about);
    }

    @Override
    public About getById(Integer id) {
        return aboutMapper.getById(id);
    }

    @Override
    public About read() {
        return aboutMapper.getAbout();
    }

    @Override
    public void deleteById(Integer id) {
        aboutMapper.deleteById(id);
    }

    @Override
    public void enableById(Integer id) {
        About byId = aboutMapper.getById(id);
        byId.setEnable(StateEnum.ENABLED.getCode());
        aboutMapper.updateEnable(byId);
    }

    @Override
    public void disableById(Integer id) {
        About byId = aboutMapper.getById(id);
        byId.setEnable(StateEnum.NOT_ENABLED.getCode());
        aboutMapper.updateEnable(byId);

    }

    @Override
    public Page<About> getByPage(Page<About> page) {
        //获取数据
        List<About> byPage = aboutMapper.getByPage(page);
        //获取总条数
        int countByPage = aboutMapper.getCountByPage(page);
        page.setTotalCount(countByPage);
        page.setList(byPage);
        return page;
    }
}
