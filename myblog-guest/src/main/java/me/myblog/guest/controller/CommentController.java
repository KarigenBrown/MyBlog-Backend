package me.myblog.guest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.dto.AddCommentDto;
import me.myblog.framework.domain.entity.Comment;
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

@Tag(name = "评论", description = "评论相关接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Lazy
    @Autowired
    private UserService userService;

    // @GetMapping("/comments/{articleId}/{pageNumber}/{pageSize}")
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
    public Response<Object> postComment(@RequestBody AddCommentDto addCommentDto) {
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        commentService.postComment(comment);
        return Response.ok();
    }

    @Operation(summary = "友链评论列表", description = "获取每一页友链评论")
    @Parameters({
            @Parameter(name = "pageNum", description = "页号"),
            @Parameter(name = "pageSize", description = "每页大小")
    })
    // @GetMapping("/linkComments/{pageNumber}/{pageSize}")
    @GetMapping("/linkCommentList")
    public Response<PageVo> getLinkComments(
            // @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            // @PathVariable("pageSize") Integer pageSize
            @RequestParam("pageSize") Integer pageSize
    ) {
        List<Comment> comments = commentService.getLinkComments(pageNum, pageSize);
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
}
