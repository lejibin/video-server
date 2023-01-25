package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Dto.UrlDto;
import com.binhow.video.entity.Url;
import com.binhow.video.service.impl.UrlServiceImpl;
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
 * 前端控制器
 * </p>
 *
 * @author Richard
 * @since 2022-12-23
 */
@Api(tags = "链接模块")
@RestController
@RequestMapping("/url")
@CrossOrigin
public class UrlController {
    @Resource
    private UrlServiceImpl urlService;

    private static QueryWrapper<Url> wrapper() {
        return new QueryWrapper<Url>().eq("status", 1);
    }

    @ApiOperation("获取链接信息")
    @GetMapping
    public R url(@RequestParam List<String> mapIdList) {
        List<Url> urlList = urlService.list(wrapper().in("map_id", mapIdList));
        if (urlList.size() > 0) {
            List<UrlDto> urlDtoList = urlList.stream().map(url -> {
                UrlDto urlDto = new UrlDto();
                BeanUtils.copyProperties(url, urlDto);
                return urlDto;
            }).collect(Collectors.toList());
            return R.success().state("urlList", urlDtoList);
        }
        return R.error();
    }
}
