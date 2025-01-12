package me.myblog.framework.service;

import me.myblog.framework.domain.entity.Tag;
import me.myblog.framework.repository.TagRepository;
import me.myblog.framework.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    public List<Tag> pageTagList(Integer pageNum, Integer pageSize, String name, String remark) {
        // 分页查询
        PageRequest page = PageRequest.of(WebUtils.toJpaPageNumber(pageNum), pageSize);
        Tag example = new Tag();
        if (StringUtils.hasText(name)) {
            example.setName(name);
        }
        if (StringUtils.hasText(remark)) {
            example.setRemark(remark);
        }
        return tagRepository.findAll(Example.of(example), page).getContent();
        // 封装数据返回
    }

    public void postTag(Tag tag) {
        tagRepository.saveAndFlush(tag);
    }

    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }

    public Tag getTagById(Long id) {
        return tagRepository.getReferenceById(id);
    }

    public void putTag(Tag tag) {
        tagRepository.saveAndFlush(tag);
    }
}
