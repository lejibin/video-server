package com.binhow.video.service.impl;

import com.binhow.video.entity.User;
import com.binhow.video.mapper.UserMapper;
import com.binhow.video.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
