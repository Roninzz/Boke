package com.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Friend;
import com.boke.domain.vo.AllFriendVo;
import com.boke.mapper.FriendMapper;
import com.boke.service.FriendService;
import com.boke.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Friend)表服务实现类
 *
 * @author DreamRay
 * @since 2023-08-14 15:21:38
 */
@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {

    @Override
    public ResponseResult getAllFriend() {
        //查询所有友链
        List<Friend> friendList = list();
        //封装vo 并 返回
        return ResponseResult.okResult(BeanCopyUtils.copyBeanList(friendList, AllFriendVo.class));
    }
}

