package com.kxingyi.blog.controller;

import com.kxingyi.blog.enums.ResultEnum;
import com.kxingyi.blog.pojo.Music;
import com.kxingyi.blog.service.MusicService;
import com.kxingyi.blog.utils.model.Page;
import com.kxingyi.blog.utils.model.Result;
import com.kxingyi.blog.utils.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(description = "音乐模块")
@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;


    /**
     * 保存
     *
     * @param music
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Music music) {
        musicService.save(music);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param music
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Music music) {
        musicService.update(music);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<Music> get(@PathVariable Integer id) {
        Music Music = musicService.getById(id);
        return new Result<>(Music);
    }



    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        musicService.deleteById(id);
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
        musicService.enableById(id);
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
        musicService.disableById(id);
        return new Result<>("弃用成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<Music>> getByPage(@RequestBody Page<Music> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"name", "artist", "created_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = musicService.getByPage(page);
        return new Result<>(page);
    }

}
