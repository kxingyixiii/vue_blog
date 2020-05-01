package com.kxingyi.blog.controller;

import com.kxingyi.blog.pojo.Type;
import com.kxingyi.blog.service.TypeService;
import com.kxingyi.blog.utils.model.Page;
import com.kxingyi.blog.utils.model.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(description = "分类模块")
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;
    @RequestMapping(value = "/insertType", method = RequestMethod.POST)
    public Result<Object> insertType(Type type){
        typeService.save(type);
        return new Result<Object>();
    }
    /**
     *@QualifiedClassName:com.kxingyi.blog.controller.TypeController
     *@ClassName:getTypeForBack
     *@Description:TODO
     *@Author:byliu
     *@Date:2020/4/23 17:38
     *@Paramter:[]
     *@Return:com.kxingyi.blog.utils.Page<com.kxingyi.blog.pojo.Type>
     * 后台获取所有的分类信息
    */
    @RequestMapping(value = "/getTypeForBack", method = RequestMethod.POST)
    public Result<Object> getTypeForBack(Page page){
        List<Type> all = typeService.getAll(page);
        return new Result<Object>(all);
    }
    /**
     *@QualifiedClassName:com.kxingyi.blog.controller.TypeController
     *@ClassName:getTypeForShow
     *@Description:TODO
     *@Author:byliu
     *@Date:2020/4/23 17:36
     *@Paramter:[]
     *@Return:com.kxingyi.blog.utils.Page<com.kxingyi.blog.pojo.Type>
     * 前台获取所有的分类信息
    */
    @RequestMapping(value = "/getTypeForShow", method = RequestMethod.POST)
    public Result<Object> getTypeForShow(){
        List<Type> typeList = typeService.getTypeList();
        return new Result<Object>(typeList);
    }



    /**
     * 根据id更新
     * @param type
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Type type) {
        typeService.update(type);
        return new Result<>("更新成功！");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<Type> getById(@PathVariable Integer id) {
        Type type = typeService.getById(id);
        return new Result<>(type);
    }

    /**
     * 根据id启用
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable Integer id) {
        typeService.enableById(id);
        return new Result<>("已启用");
    }

    /**
     * 根据id弃用
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id) {
        typeService.disableById(id);
        return new Result<>("已弃用");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        typeService.deleteById(id);
        return new Result<>("删除成功！");
    }
}
