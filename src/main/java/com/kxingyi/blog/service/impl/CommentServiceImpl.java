package com.kxingyi.blog.service.impl;

import com.kxingyi.blog.pojo.Comment;
import com.kxingyi.blog.pojo.CommentGoods;
import com.kxingyi.blog.service.CommentService;
import com.kxingyi.blog.utils.model.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Override
    public void save(Comment comment) {

    }

    @Override
    public List<Comment> getByBlog(String blogId) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void goodByCommentIdAndUser(CommentGoods commentGoods) {

    }

    @Override
    public int getGoodsCount(String commentId) {
        return 0;
    }

    @Override
    public Page<Comment> getByPage(Page<Comment> page) {
        return null;
    }

    @Override
    public Page<Comment> getByPageBack(Page<Comment> page) {
        return null;
    }
}
