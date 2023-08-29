package com.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.constants.SystemConstants;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Comment;
import com.boke.domain.entity.User;
import com.boke.domain.vo.CommentChildVo;
import com.boke.domain.vo.CommentVo;
import com.boke.domain.vo.NewCommentVo;
import com.boke.domain.vo.PageVo;
import com.boke.enums.AppHttpCodeEnum;
import com.boke.exception.SystemException;
import com.boke.mapper.CommentMapper;
import com.boke.service.CommentService;
import com.boke.service.UserService;
import com.boke.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (Comment)表服务实现类
 *
 * @author DreamRay
 * @since 2023-08-23 14:14:32
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    //查询评论
    @Override
    public ResponseResult commentList(Integer commentType, Integer typeId, Integer pageNum, Integer pageSize) {
        //参数校验
        //查询对应文章的父评论
        //查询文章对应id的评论
        LambdaQueryWrapper<Comment> lwq = new LambdaQueryWrapper<>();
        lwq.eq(Comment::getCommentType,commentType);
        lwq.eq(!Objects.isNull(typeId),Comment::getTypeId, typeId);
        //查询parentId为-1的跟评论
        lwq.eq(Comment::getParentId, SystemConstants.HAVE_PARENT_COMMENT);
        //分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, lwq);

        List<CommentVo> commentVos = toCommentVoList(page.getRecords());

        return ResponseResult.okResult(new PageVo(commentVos, page.getTotal()));
    }

    //添加评论
    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容过滤
        if (StringUtils.hasText(comment.getCommentContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    //查询最新评论
    @Override
    public ResponseResult newComment() {
        //查询最新的5条评论，按时间排序
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        //查询通过的评论
        lqw.eq(Comment::getIsCheck,SystemConstants.ARTICLE_STATUS_PUBLIC);
        //按时间排序
        //截取5条
        lqw.orderByDesc(Comment::getCreateTime).last("limit 3");
        List<Comment> commentList = commentService.list(lqw);
        //封装 最新评论 VO
        List<NewCommentVo> newCommentVos = new ArrayList<>();
        //拿评论者的id查评论者的信息
        commentList.stream()
                .map(comment -> {
                    //获取fromid
                    Integer fromUid = comment.getFromUid();
                    //根据fromid到user表中查询用户
                    User user = userService.getById(fromUid);
                    //把每个评论信息封装成vo
                    NewCommentVo newCommentVo = BeanCopyUtils.copyBean(comment, NewCommentVo.class);
                    //每个vo中的用户头像和昵称赋值
                    if (Objects.isNull(user)){
                        return comment;
                    }
                    newCommentVo.setAvatar(user.getAvatar());
                    newCommentVo.setNickname(user.getNickname());
                    //添加到vo集合中
                    newCommentVos.add(newCommentVo);
                    return newCommentVos;
                })
                .collect(Collectors.toList());

        return ResponseResult.okResult(newCommentVos);
    }

    //封装父评论
    public List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历commentVos
        commentVos.stream()
                .map(commentVo -> {
                    //通过fromUid查询 发表评论人
                    Integer fromUid = commentVo.getFromUid();
                    User user = userService.getById(fromUid);
                    //赋值给CommentVo的 fromNickname 和 avatar
                    commentVo.setFromNickname(user.getNickname());
                    commentVo.setAvatar(user.getAvatar());

                    //取出每个跟评论id
                    Integer id = commentVo.getId();
                    LambdaQueryWrapper<Comment> childLqw = new LambdaQueryWrapper<>();
                    //在所有评论中找出id==parentid的评论
                    childLqw.eq(Comment::getParentId, id);
                    childLqw.orderByAsc(Comment::getCreateTime);
                    List<Comment> childList = commentService.list(childLqw);
                    List<CommentChildVo> commentChildVos = toCommentChildVoList(childList);
                    commentVo.setChildList(commentChildVos);
                    commentVo.setChildCount(list.size());

                    return commentVo;
                })
                .collect(Collectors.toList());


        return commentVos;
    }

    //封装子评论
    public List<CommentChildVo> toCommentChildVoList(List<Comment> list) {
        List<CommentChildVo> commentChildVos = BeanCopyUtils.copyBeanList(list, CommentChildVo.class);

        commentChildVos.stream()
                .map(commentChildVo -> {
                    //通过fromUid查询 发表评论人
                    Integer fromUid = commentChildVo.getFromUid();
                    User user = userService.getById(fromUid);
                    //如果没有该用户，说明用户注销了
                    if (Objects.isNull(user)) {
                        commentChildVo.setFromNickname("该用户已注销");
                        commentChildVo.setAvatar(null);
                        return commentChildVo;
                    }
                    //赋值给CommentVo的 fromNickname 和 avatar
                    commentChildVo.setFromNickname(user.getNickname());
                    commentChildVo.setAvatar(user.getAvatar());

                    //通过toUid查询 回复的评论人
                    Integer toUid = commentChildVo.getToUid();
                    User toUser = userService.getById(toUid);
                    if (Objects.isNull(toUser)) {
                        commentChildVo.setFromNickname("该用户已注销");
                        commentChildVo.setAvatar(null);
                        return commentChildVo;
                    }
                    commentChildVo.setToNickname(toUser.getNickname());
                    return commentChildVo;
                })
                .collect(Collectors.toList());


        return commentChildVos;
    }
}

