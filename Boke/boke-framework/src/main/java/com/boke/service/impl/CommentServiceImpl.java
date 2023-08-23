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
import com.boke.domain.vo.PageVo;
import com.boke.mapper.CommentMapper;
import com.boke.service.CommentService;
import com.boke.service.UserService;
import com.boke.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseResult commentList(Integer commentType, Integer typeId, Integer pageNum, Integer pageSize) {
        //参数校验
        //判断要查询的是那种类型的评论
        if (commentType == SystemConstants.ARTICLE_COMMENT){
            //查询对应文章的父评论
            //查询文章对应id的评论
            LambdaQueryWrapper<Comment> lwq = new LambdaQueryWrapper<>();
            lwq.eq(Comment::getTypeId,typeId);
            //查询parentId为-1的跟评论
            lwq.eq(Comment::getParentId,SystemConstants.HAVE_PARENT_COMMENT);
            //分页查询
            Page<Comment> page = new Page<>(pageNum,pageSize);
            page(page,lwq);

            List<CommentVo> commentVos = toCommentVoList(page.getRecords());

            return ResponseResult.okResult(new PageVo(commentVos,page.getTotal()));
        }

        return ResponseResult.okResult();
    }

    public List<CommentVo> toCommentVoList(List<Comment> list){
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
                    childLqw.eq(Comment::getParentId,id);
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

    public List<CommentChildVo> toCommentChildVoList(List<Comment> list){
        List<CommentChildVo> commentChildVos = BeanCopyUtils.copyBeanList(list, CommentChildVo.class);

        commentChildVos.stream()
                .map(commentChildVo -> {
                    //通过fromUid查询 发表评论人
                    Integer fromUid = commentChildVo.getFromUid();
                    User user = userService.getById(fromUid);
                    //如果没有该用户，说明用户注销了
                    if (Objects.isNull(user)){
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
                    if (Objects.isNull(toUser)){
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

