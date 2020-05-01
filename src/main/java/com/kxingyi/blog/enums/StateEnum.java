package com.kxingyi.blog.enums;

import lombok.Getter;

/**
 *@QualifiedClassName:
 *@ClassName:状态枚举
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/20 22:04
 *@Paramter:
 *@Return:
*/


@Getter
public enum StateEnum {
    /**
     *逻辑删除
     */
    DELETED(1, "已删除"),
    NOT_DELETE(0,"未删除"),
    /**
     *启用禁用
    */
    ENABLED(1,"启用"),
    NOT_ENABLED(0,"禁用"),
    /**
     *性别
    */
    SEX_MAN(1,"男"),
    SEX_WOMAN(0,"女"),

    ADMIN(1, "管理员"),
    NOT_ADMIN(0,"普通用户"),

    REQUEST_ERROR(500,"请求失败"),
    REQUEST_SUCCESS(200,"请求成功") ;

    private Integer code;
    private String msg;
    StateEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
