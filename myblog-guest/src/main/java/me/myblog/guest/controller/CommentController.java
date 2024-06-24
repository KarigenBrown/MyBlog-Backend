package me.myblog.guest.controller;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Comment;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.domain.vo.CommentVo;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.CommentService;
import me.myblog.framework.service.UserService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Lazy
    @Autowired
    private UserService userService;

    // @GetMapping("/comments")
    @GetMapping("/commentList")
    public Response<PageVo> getComments(
            // @PathVariable("articleId") Long articleId,
            @RequestParam(name = "articleId", required = false) Long articleId,
            // @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            // @PathVariable("pageSize") Integer pageSize
            @RequestParam("pageSize") Integer pageSize
    ) {
        List<Comment> comments = commentService.getCommentsByArticleId(articleId, pageNum, pageSize);
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(comments, CommentVo.class);

        Consumer<CommentVo> commentVoConsumer = commentVo -> {
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            if (!Objects.equals(commentVo.getToCommentUserId(), SystemConstants.ROOT_COMMENT)) {
                String toCommentUsername = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUsername);
            }
            List<Comment> children = commentService.getCommentsByRootId(commentVo.getId());
            commentVo.setChildren(BeanCopyUtils.copyBeanList(children, CommentVo.class));
        };

        commentVos.forEach(commentVo -> {
            commentVoConsumer.accept(commentVo);
            commentVo.getChildren().forEach(commentVoConsumer);
        });

        PageVo pageVo = new PageVo(commentVos, Integer.toUnsignedLong(commentVos.size()));
        return Response.ok(pageVo);
    }

    @PostMapping
    public Response<Object> postComment(@RequestBody Comment comment) {
        commentService.postComment(comment);
        return Response.ok();
    }
}
