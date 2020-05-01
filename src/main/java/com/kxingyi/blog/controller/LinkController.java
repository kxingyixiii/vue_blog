package com.kxingyi.blog.controller;

import com.kxingyi.blog.pojo.Link;
import com.kxingyi.blog.service.LinkService;
import com.kxingyi.blog.utils.IdWorker;
import com.kxingyi.blog.utils.model.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(description = "友情链接模块")
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @Autowired
    private IdWorker idWorker;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(Link link) {
        link.setLinkId((int) idWorker.nextId());
        linkService.save(link);
        return new Result<>();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(Link link) {
        linkService.update(link);
        return new Result<>();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public Result<Object> getById(@PathVariable Integer id) {
        Link link = linkService.getById(id);
        return new Result<>(link);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result<Object> getAll() {
        List<Link> all = linkService.getAll();
        return new Result<>(all);
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public Result<Object> deleteById(@PathVariable Integer id) {
        linkService.deleteById(id);
        return new Result<>();
    }
}
