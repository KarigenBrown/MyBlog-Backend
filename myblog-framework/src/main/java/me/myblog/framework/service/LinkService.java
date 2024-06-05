package me.myblog.framework.service;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Link;
import me.myblog.framework.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
}
