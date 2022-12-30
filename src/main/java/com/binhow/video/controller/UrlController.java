package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.service.IUrlService;
import com.binhow.video.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Richard
 * @since 2022-12-23
 */
@Api(tags = "链接模块")
@RestController
@RequestMapping("/url")
public class UrlController {
    @Resource
    private IUrlService iUrlService;

    private static QueryWrapper<Url> wrapper() {
        QueryWrapper<Url> wrapper = new QueryWrapper<>();
        wrapper.eq("url_status", 1);
        return wrapper;
    }

    @ApiOperation("获取链接信息")
    @GetMapping
    public R url(@RequestParam Long videoId) {
        List<Url> urlList = iUrlService.list(wrapper().eq("video_id", videoId));
        return R.success().state("urlList", urlList);
    }
}
