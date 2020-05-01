package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.mapper.LogMapper;
import com.kxingyi.blog.pojo.Log;
import com.kxingyi.blog.service.LogService;
import com.kxingyi.blog.utils.model.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void save(Log logger) {
        logMapper.save(logger);
    }

    @Override
    public Page<Log> getByPage(Page<Log> page) {
        List<Log> byPage = logMapper.getByPage(page);
        int countByPage = logMapper.getCountByPage(page);
        page.setList(byPage);
        page.setTotalCount(countByPage);
        return page;
    }

    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        logMapper.deleteByIds(ids);
    }

    @Override
    public Workbook export() {
        return null;
    }
}
