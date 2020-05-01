package com.kxingyi.blog.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 友情链接
 * @Author: 杨德石
 * @Date: 2020/2/9 14:43
 * @Version 1.0
 */
@Data
public class Link  {

    private Integer linkId;
    @NotNull(message = "友情链接名称不能为空")
    private String linkName;
    @NotNull(message = "友情链接地址不能为空")
    private String linkUrl;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer deleted;

}
