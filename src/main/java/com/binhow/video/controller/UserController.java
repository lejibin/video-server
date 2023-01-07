package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Dto.UserDto;
import com.binhow.video.entity.User;
import com.binhow.video.service.IUserService;
import com.binhow.video.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Richard
 * @since 2022-12-23
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    private static QueryWrapper<User> wrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        return wrapper;
    }

    @ApiOperation("获取用户列表")
    @GetMapping
    public R user(@RequestParam(required = false) Long id) {
        if (id == null) {
            List<User> userList = iUserService.list(wrapper());
            if (userList.size() > 0) {
                List<UserDto> userDtoList = userList.stream().map(user -> {
                            UserDto userDto = new UserDto();
                            BeanUtils.copyProperties(user, userDto);
                            return userDto;
                        }
                ).collect(Collectors.toList());
                return R.success().state("userList", userDtoList);
            }
        } else {
            User user = iUserService.getOne(wrapper().eq("id", id));
            if (user != null) {
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(user, userDto);
                return R.success().state("user", userDto);
            }
        }
        return R.error();
    }
}
