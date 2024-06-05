package me.myblog.guest.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Link;
import me.myblog.framework.domain.vo.LinkVo;
import me.myblog.framework.service.LinkService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    // @GetMapping("/all")
    @GetMapping("/getAllLink")
    public Response<List<LinkVo>> getAllLinks() {
        List<Link> links = linkService.getAllLinks();
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return Response.ok(linkVos);
    }
}

