package com.kxingyi.blog.controller;

import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.pojo.About;
import com.kxingyi.blog.service.AboutService;
import com.kxingyi.blog.utils.model.Page;
import com.kxingyi.blog.utils.model.Result;
import com.kxingyi.blog.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(description = "关于我功能模块")
@RestController
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 保存
     *
     * @param about
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody About about) {
        aboutService.save(about);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param about
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody About about) {
        aboutService.update(about);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<About> get(@PathVariable Integer id) {
        About about = aboutService.getById(id);
        return new Result<>(about);
    }

    /**
     * 阅读
     *
     * @return
     */
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public Result<About> read() {
        About about = aboutService.read();
        return new Result<>(about);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        aboutService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enable/{id}", method = RequestMethod.PUT)
    public Result<Object> enable(@PathVariable Integer id) {
        aboutService.enableById(id);
        return new Result<>("启用成功");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}", method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id) {
        aboutService.disableById(id);
        return new Result<>("弃用成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<About>> getByPage(@RequestBody Page<About> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"about_read", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = aboutService.getByPage(page);
        return new Result<>(page);
    }
}
