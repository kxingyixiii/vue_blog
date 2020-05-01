package com.kxingyi.blog.controller;

import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.pojo.Log;
import com.kxingyi.blog.service.LogService;
import com.kxingyi.blog.utils.model.Page;
import com.kxingyi.blog.utils.model.Result;
import com.kxingyi.blog.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Api(description = "日志模块")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;


    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<Log>> getByPage(@RequestBody Page<Log> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"log_time", "created_time", "log_method"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = logService.getByPage(page);
        return new Result<>(page);
    }
    /**
     *@QualifiedClassName:com.kxingyi.blog.controller.LogController
     *@ClassName:deleteByIds
     *@Description:删除多条日志
     *@Author:byliu
     *@Date:2020/5/1 13:56
     *@Paramter:[ids]
     *@Return:com.kxingyi.blog.utils.Result<com.kxingyi.blog.pojo.Log>
    */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.DELETE)
    public Result<Log> deleteByIds(@RequestBody List<Integer> ids){
        logService.deleteByIds(ids);
        return new Result<>();
    }
    /**
     *@QualifiedClassName:com.kxingyi.blog.controller.LogController
     *@ClassName:deleteById
     *@Description:删除单条日志
     *@Author:byliu
     *@Date:2020/5/1 13:55
     *@Paramter:[id]
     *@Return:com.kxingyi.blog.utils.Result<com.kxingyi.blog.pojo.Log>
    */
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public Result<Log> deleteById(@RequestBody Integer id){
        logService.deleteById(id);
        return new Result<>();
    }

}
