package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.enums.StateEnum;
import com.kxingyi.blog.exception.BusinessException;
import com.kxingyi.blog.mapper.MusicMapper;
import com.kxingyi.blog.pojo.Music;
import com.kxingyi.blog.service.MusicService;
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
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;
    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }

    @Override
    public Music getById(Integer id) {
        return musicMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        Music byId = musicMapper.getById(id);
        if(byId == null){
            throw new BusinessException("不存在id为[" + id + "]的数据");
        }
        if(StateEnum.DELETED.getCode().equals(byId.getDeleted())){
            throw new BusinessException("已经删除了id为[" + id + "]的数据");
        }
        musicMapper.deleteById(id);
    }

    @Override
    public void enableById(Integer id) {
        Music byId = musicMapper.getById(id);
        if(byId == null){
            throw new BusinessException("不存在id为[" + id + "]的数据");
        }
        if(StateEnum.ENABLED.getCode().equals(byId.getEnable())){
            throw new BusinessException("已经启用了id为[" + id + "]的数据");
        }
        byId.setEnable(StateEnum.ENABLED.getCode());
        musicMapper.update(byId);
    }

    @Override
    public void disableById(Integer id) {
        Music byId = musicMapper.getById(id);
        if(byId == null){
            throw new BusinessException("不存在id为[" + id + "]的数据");
        }
        if(StateEnum.NOT_DELETE.getCode().equals(byId.getEnable())){
            throw new BusinessException("已经弃用了id为[" + id + "]的数据");
        }
        byId.setEnable(StateEnum.NOT_ENABLED.getCode());
        musicMapper.deleteById(id);
    }

    @Override
    public Page<Music> getByPage(Page<Music> page) {

        List<Music> byPage = musicMapper.getByPage(page);
        int countByPage = musicMapper.getCountByPage(page);
        page.setList(byPage);
        page.setTotalCount(countByPage);
        return page;
    }

    @Override
    public List<Music> getList() {
        return musicMapper.getList();
    }
}
