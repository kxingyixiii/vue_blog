package com.kxingyi.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(description = "测试模块")
@RestController
@RequestMapping("/swaggerTest")
public class SwaggerTestController {
    @ApiOperation(value="helloworld",notes = "你好,世界")
    @ApiParam(name = "name", value = "姓名" ,defaultValue = "张三")
    @RequestMapping(value = "/hellworld/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Object helloWorld(@PathVariable  String name){
        return "HelloWorld : " + name;
    }
}
