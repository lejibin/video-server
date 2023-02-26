package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binhow.video.entity.*;
import com.binhow.video.entity.Dto.VideoDto;
import com.binhow.video.mapper.VideoMapper;
import com.binhow.video.service.impl.*;
import com.binhow.video.utils.ListTool;
import com.binhow.video.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@Api(tags = "视频模块")
@RestController
@RequestMapping("/video")
@CrossOrigin
public class VideoController {
    @Resource
    private VideoServiceImpl videoService;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private VideoCategoryServiceImpl videoCategoryService;
    @Resource
    private VideoAreaServiceImpl videoAreaService;
    @Resource
    private VideoLanguageServiceImpl videoLanguageService;
    @Resource
    private VideoArtorServiceImpl videoArtorService;
    @Resource
    private VideoDirectorServiceImpl videoDirectorService;
    @Resource
    private VideoWriterServiceImpl videoWriterService;
    @Resource
    private ArtorServiceImpl artorService;
    @Resource
    private ListTool listTool;

    private static QueryWrapper<Video> wrapper() {
        return new QueryWrapper<Video>().eq("status", 1).orderByDesc("date");
    }

    @ApiOperation("获取视频信息")
    @GetMapping
    public R video(@RequestParam(required = false) Long id, @RequestParam(required = false) Long channelId, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long areaId, @RequestParam(required = false) Long languageId, @RequestParam(required = false) Long artorId, @RequestParam(required = false) String year, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "30") Integer pageSize) {
        // 查询单个视频详细信息
        QueryWrapper<Video> wrapper = wrapper();
        if (id != null) {
            VideoDto videoDto = videoMapper.getVideo(wrapper.in("id", id));
            if (videoDto != null) {
                return R.success().state("video", videoDto);
            }
            return R.error();
        }
        if (pageSize > 100) {
            pageSize = 100;
        }
        Page<Video> page = new Page<>(pageNo, pageSize);
        Page<Video> videoList;
        // 查询频道所有视频信息
        if (channelId != null) {
            wrapper = wrapper.eq("channel_id", channelId);
        }
        List<Long> videoIdList = new ArrayList<>();
        // 类型
        List<Long> categoryVideoIdList = new ArrayList<>();
        if (categoryId != null) {
            List<VideoCategory> videoCategoryList = videoCategoryService.list(new QueryWrapper<VideoCategory>().eq("category_id", categoryId).eq("status", 1));
            categoryVideoIdList = videoCategoryList.stream().map(VideoCategory::getVideoId).collect(Collectors.toList());
            videoIdList = categoryVideoIdList;
        }
        // 地区
        List<Long> areaVideoIdList = new ArrayList<>();
        if (areaId != null) {
            List<VideoArea> videoAreaList = videoAreaService.list(new QueryWrapper<VideoArea>().eq("area_id", areaId).eq("status", 1));
            areaVideoIdList = videoAreaList.stream().map(VideoArea::getVideoId).collect(Collectors.toList());
            videoIdList = areaVideoIdList;
        }
        // 语言
        List<Long> languageVideoIdList = new ArrayList<>();
        if (languageId != null) {
            List<VideoLanguage> videoLanguageList = videoLanguageService.list(new QueryWrapper<VideoLanguage>().eq("language_id", languageId).eq("status", 1));
            languageVideoIdList = videoLanguageList.stream().map(VideoLanguage::getVideoId).collect(Collectors.toList());
            videoIdList = languageVideoIdList;
        }
        // 演员, 编剧, 导演
        List<Long> artorVideoIdList = new ArrayList<>();
        if (artorId != null) {
            List<VideoArtor> videoArtorList = videoArtorService.list(new QueryWrapper<VideoArtor>().eq("artor_id", artorId).eq("status", 1));
            artorVideoIdList.addAll(videoArtorList.stream().map(VideoArtor::getVideoId).collect(Collectors.toList()));
            List<VideoDirector> videoDirectorList = videoDirectorService.list(new QueryWrapper<VideoDirector>().eq("director_id", artorId).eq("status", 1));
            artorVideoIdList.addAll(videoDirectorList.stream().map(VideoDirector::getVideoId).collect(Collectors.toList()));
            List<VideoWriter> videoWriterList = videoWriterService.list(new QueryWrapper<VideoWriter>().eq("writer_id", artorId).eq("status", 1));
            artorVideoIdList.addAll(videoWriterList.stream().map(VideoWriter::getVideoId).collect(Collectors.toList()));
            videoIdList = artorVideoIdList;
        }
        if (categoryId != null && videoIdList != categoryVideoIdList) {
            videoIdList = listTool.intersection(videoIdList, categoryVideoIdList);
        }
        if (areaId != null && videoIdList != areaVideoIdList) {
            videoIdList = listTool.intersection(videoIdList, areaVideoIdList);
        }
        if (languageId != null && videoIdList != languageVideoIdList) {
            videoIdList = listTool.intersection(videoIdList, languageVideoIdList);
        }
        if (artorId != null && videoIdList != artorVideoIdList) {
            videoIdList = listTool.intersection(videoIdList, artorVideoIdList);
        }
        // 年份
        if (year != null) {
            wrapper = wrapper.eq("year", year);
        }
        if (categoryId != null || areaId != null || languageId != null || artorId != null) {
            if (videoIdList.size() > 0) {
                wrapper = wrapper.in("id", videoIdList);
            } else {
                return R.error();
            }
        }
        videoList = videoService.page(page, wrapper);
        if (videoList.getRecords().size() > 0) {
            return R.success().state("videoList", videoList);
        }
        return R.error();
    }

    @ApiOperation("搜索视频信息")
    @GetMapping("/search")
    private R search(@RequestParam String s, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "30") Integer pageSize) {
        QueryWrapper<Video> wrapper = wrapper().like("name", s);
        if (pageSize > 100) {
            pageSize = 100;
        }
        Page<Video> page = new Page<>(pageNo, pageSize);
        List<Artor> artorList = artorService.list(new QueryWrapper<Artor>().like("name", s).eq("status", 1));
        List<Long> artorIdList = artorList.stream().map(Artor::getId).collect(Collectors.toList());
        List<Long> videoIdList = new ArrayList<>();
        if (artorIdList.size() > 0) {
            List<VideoArtor> videoArtorList = videoArtorService.list(new QueryWrapper<VideoArtor>().in("artor_id", artorIdList).eq("status", 1));
            videoIdList.addAll(videoArtorList.stream().map(VideoArtor::getVideoId).collect(Collectors.toList()));
            List<VideoDirector> videoDirectorList = videoDirectorService.list(new QueryWrapper<VideoDirector>().in("director_id", artorIdList).eq("status", 1));
            videoIdList.addAll(videoDirectorList.stream().map(VideoDirector::getVideoId).collect(Collectors.toList()));
            List<VideoWriter> videoWriterList = videoWriterService.list(new QueryWrapper<VideoWriter>().in("writer_id", artorIdList).eq("status", 1));
            videoIdList.addAll(videoWriterList.stream().map(VideoWriter::getVideoId).collect(Collectors.toList()));
        }
        if (videoIdList.size() > 0) {
            wrapper = wrapper.or().in("id", videoIdList);
        }
        Page<Video> videoList = videoService.page(page, wrapper);
        if (videoList.getRecords().size() > 0) {
            return R.success().state("videoList", videoList);
        }
        return R.error();
    }
}
