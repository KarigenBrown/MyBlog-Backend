package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Link;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping("/link")
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public Response<PageVo> getLinkList(
            // @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("name") String name,
            @RequestParam("status") String status
    ) {
        List<Link> categories = linkService.getLinkList(pageNum, pageSize, name, status);
        PageVo pageVo = new PageVo(categories, Integer.toUnsignedLong(categories.size()));
        return Response.ok(pageVo);
    }

    @PostMapping
    public Response<Object> postLink(@RequestBody Link link) {
        linkService.save(link);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response<Link> getById(@PathVariable("id") Long id) {
        Link link = linkService.getLinkById(id);
        return Response.ok(link);
    }

    @PutMapping
    public Response<Object> putLink(@RequestBody Link link) {
        linkService.save(link);
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteById(@PathVariable("id") Long id) {
        linkService.deleteById(id);
        return Response.ok();
    }
}
