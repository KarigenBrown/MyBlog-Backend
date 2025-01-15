package me.myblog.framework.service;

import jakarta.persistence.criteria.Predicate;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Link;
import me.myblog.framework.domain.meta.Link_;
import me.myblog.framework.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    public List<Link> getAllLinks() {
        Link link = new Link();
        link.setStatus(SystemConstants.LINK_STATUS_NORMAL);
        return linkRepository.findAll(Example.of(link));
    }

    public List<Link> getLinkList(Integer pageNum, Integer pageSize, String name, String status) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Link link = new Link();
        link.setName(name);
        link.setStatus(status.charAt(0));
        return linkRepository.findAll((root, query, builder) -> {
            Predicate n = builder.like(root.get(Link_.NAME), name);
            Predicate s = builder.equal(root.get(Link_.STATUS), status);
            query.where(builder.and(n, s));
            return query.getRestriction();
        }, pageRequest).getContent();
    }

    public void save(Link link) {
        linkRepository.saveAndFlush(link);
    }

    public Link getLinkById(Long id) {
        return linkRepository.getReferenceById(id);
    }

    public void deleteById(Long id) {
        linkRepository.deleteById(id);
    }
}
