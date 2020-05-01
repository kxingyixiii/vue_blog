package com.kxingyi.blog.pojo;

import lombok.Data;


/**
 * 评论点赞
 * @Author: 杨德石
 * @Date: 2020/2/16 10:22
 * @Version 1.0
 */
@Data
public class CommentGoods  {

    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 评论id
     */
    private String commentId;

}
