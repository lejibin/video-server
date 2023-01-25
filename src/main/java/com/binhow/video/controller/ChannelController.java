package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Channel;
import com.binhow.video.entity.Dto.ChannelDto;
import com.binhow.video.service.impl.ChannelServiceImpl;
import com.binhow.video.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
@Api(tags = "频道模块")
@RestController
@RequestMapping("/channel")
@CrossOrigin
public class ChannelController {
    @Resource
    private ChannelServiceImpl channelService;

    private static QueryWrapper<Channel> wrapper() {
        return new QueryWrapper<Channel>().eq("status", 1);
    }

    @ApiOperation("获取频道列表")
    @GetMapping
    public R channel() {
        List<Channel> channelList = channelService.list(wrapper());
        if (channelList.size() > 0) {
            List<ChannelDto> channelDtoList = channelList.stream().map(channel -> {
                ChannelDto channelDto = new ChannelDto();
                BeanUtils.copyProperties(channel, channelDto);
                return channelDto;
            }).collect(Collectors.toList());
            return R.success().state("channelList", channelDtoList);
        }
        return R.error();
    }
}
