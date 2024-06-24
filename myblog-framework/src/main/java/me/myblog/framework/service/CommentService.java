package me.myblog.framework.service;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Comment;
import me.myblog.framework.domain.entity.Comment_;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.repository.CommentRepository;
import me.myblog.framework.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByArticleId(Long articleId, Integer pageNum, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, Comment_.CREATE_TIME);
        PageRequest page = PageRequest.of(WebUtils.toJpaPageNumber(pageNum), pageSize, sort);

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setRootId(SystemConstants.ROOT_COMMENT);
        return commentRepository.findAll(Example.of(comment), page).getContent();
    }

    public List<Comment> getCommentsByRootId(Long id) {
        Sort sort = Sort.by(Sort.Direction.ASC, Comment_.CREATE_TIME);

        Comment comment = new Comment();
        comment.setRootId(id);
        return commentRepository.findAll(Example.of(comment), sort);
    }

    public void postComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(ResponseStatusEnum.CONTENT_NOT_NULL);
        }
        commentRepository.saveAndFlush(comment);
    }
}
