package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.dto.TagListDto;
import me.myblog.framework.domain.entity.Tag;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.domain.vo.TagVo;
import me.myblog.framework.service.TagService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping("/tag")
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public Response<PageVo> getTags(
            // @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            // @RequestParam("pageSize") Integer pageSize
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam TagListDto tagListDto
    ) {
        List<Tag> tags = tagService.pageTagList(pageNum, pageSize, tagListDto.getName(), tagListDto.getRemark());
        return Response.ok(new PageVo(tags, Integer.toUnsignedLong(tags.size())));
    }

    @PostMapping
    public Response<Object> postTag(@RequestBody Tag tag) {
        tagService.postTag(tag);
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteTagById(@PathVariable("id") Long id) {
        tagService.deleteTagById(id);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response<Tag> getTagById(@PathVariable("id") Long id) {
        Tag tag = tagService.getTagById(id);
        return Response.ok(tag);
    }

    @PutMapping
    public Response<Object> putTag(@RequestBody Tag tag) {
        tagService.putTag(tag);
        return Response.ok();
    }

    // @GetMapping("/all")
    @GetMapping("/listAllTag")
    public Response<List<TagVo>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagVo> data = BeanCopyUtils.copyBeanList(tags, TagVo.class);
        return Response.ok(data);
    }
}
