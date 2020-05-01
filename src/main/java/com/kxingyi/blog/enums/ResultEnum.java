package com.kxingyi.blog.enums;

import lombok.Getter;

/**
 *@QualifiedClassName:com.kxingyi.blog.enums.ResultEnum
 *@ClassName:返回类枚举
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/20 21:44
 *@Paramter:
 *@Return:
*/
@Getter
public enum ResultEnum {
    SUCCESS(200,"操作成功！"),
    ERROR(500,"操作失败！"),
    DATA_NOT_FOUND(404,"查询失败！"),
    PARAMS_NOT_NULL(501,"参数不能为空！"),
    LACK_OF_PERMISSION(502,"没有足够的权限！"),
    PARAMS_ERROR(40005, "参数不合法！"),
    NOT_LOGIN(503,"当前未登录，请重新登录！");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
