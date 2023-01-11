package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binhow.video.entity.*;
import com.binhow.video.entity.Dto.VideoDto;
import com.binhow.video.mapper.VideoMapper;
import com.binhow.video.service.*;
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
public class VideoController {
    @Resource
    private ICategoryService iCategoryService;
    @Resource
    private IVideoCategoryService iVideoCategoryService;
    @Resource
    private VideoMapper videoMapper;
    @Resource
    private IVideoService iVideoService;
    @Resource
    private IVideoAreaService iVideoAreaService;

    @Resource
    private IVideoLanguageService iVideoLanguageService;

    private static QueryWrapper<Video> wrapper() {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).orderByDesc("date");
        return wrapper;
    }

    @ApiOperation("获取视频信息")
    @GetMapping
    public R getVideo(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long areaId, @RequestParam(required = false) Long languageId, @RequestParam(required = false) String year, @RequestParam(required = false) Long id, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "50") Integer pageSize) {
        if (id != null) {
            VideoDto videoDto = videoMapper.getVideo(wrapper().in("id", id));
            if (videoDto != null) {
                return R.success().state("video", videoDto);
            }
            return R.error();
        }
        if (pageSize > 100) {
            pageSize = 100;
        }
        Page<Video> page = new Page<>(pageNo, pageSize);
        if (categoryId != null) {
            Page<Video> videoList;
            if (categoryId == 1) {
                videoList = iVideoService.page(page, wrapper());
            } else {
                List<Long> categoryIdList = new ArrayList<>();
                categoryIdList.add(categoryId);
                getCategoryIdList(categoryId, categoryIdList);
                List<VideoCategory> videoCategoryList = iVideoCategoryService.list(new QueryWrapper<VideoCategory>().in("category_id", categoryIdList));
                List<Long> videoIdList = videoCategoryList.stream().map(VideoCategory::getVideoId).collect(Collectors.toList());
                // 地区
                if (areaId != null) {
                    ListTool listTool = new ListTool();
                    List<VideoArea> videoAreaList = iVideoAreaService.list(new QueryWrapper<VideoArea>().eq("area_id", areaId).eq("status", 1));
                    videoIdList = listTool.intersection(videoIdList, videoAreaList.stream().map(VideoArea::getVideoId).collect(Collectors.toList()));
                }
                // 语言
                if (languageId != null) {
                    ListTool listTool = new ListTool();
                    List<VideoLanguage> videoLanguageList = iVideoLanguageService.list(new QueryWrapper<VideoLanguage>().eq("language_id", languageId).eq("status", 1));
                    videoIdList = listTool.intersection(videoIdList, videoLanguageList.stream().map(VideoLanguage::getVideoId).collect(Collectors.toList()));
                }
                // 年份
                QueryWrapper<Video> w = wrapper();
                if (year != null) {
                    w = w.eq("year", year);
                }
                if (videoIdList.size() > 0) {
                    videoList = iVideoService.page(page, w.in("id", videoIdList));
                } else {
                    return R.error();
                }
            }
            if (videoList.getRecords().size() > 0) {
                return R.success().state("videoList", videoList);
            }
            return R.error();
        }
        return R.error();
    }

    @ApiOperation("获取视频信息")
    @GetMapping("/yearList")
    private R getYearList() {
        List<String> yearList = videoMapper.getYearList();
        if (yearList.size() > 0) {
            return R.success().state("yearList", yearList);
        }
        return R.error();
    }

    @ApiOperation("搜索视频信息")
    @GetMapping("/search")
    private R search(@RequestParam String s) {
        Page<Video> page = new Page<>(1, 50);
        Page<Video> videoList = iVideoService.page(page, wrapper().like("name", s));
        if (videoList.getRecords().size() > 0) {
            return R.success().state("videoList", videoList);
        }
        return R.error();
    }

    private void getCategoryIdList(Long categoryId, List<Long> categoryIdList) {
        List<Category> childCategoryList = iCategoryService.list(new QueryWrapper<Category>().eq("parent_id", categoryId));
        for (Category childCategory : childCategoryList) {
            Long childCategoryId = childCategory.getId();
            categoryIdList.add(childCategoryId);
            getCategoryIdList(childCategoryId, categoryIdList);
        }
    }
}
