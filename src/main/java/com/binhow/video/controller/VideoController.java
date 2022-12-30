package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binhow.video.service.ICategoryService;
import com.binhow.video.service.IVideoService;
import com.binhow.video.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private IVideoService iVideoService;
    @Resource
    private ICategoryService iCategoryService;

    private static QueryWrapper<Video> wrapper() {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("video_status", 1).orderByDesc("video_date");
        return wrapper;
    }

    @ApiOperation("获取视频列表")
    @GetMapping("")
    public R all() {
        return R.error();
    }

    @ApiOperation("获取最新视频列表")
    @GetMapping("/newList")
    public R newList(@RequestParam(required = false, defaultValue = "1") Long categoryId, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        if (pageSize > 50) {
            pageSize = 50;
        }
        Page<Video> page = new Page<>(pageNo, pageSize);
        Page<Video> newList;
        if (categoryId == 1) {
            newList = iVideoService.page(page, wrapper());
        } else {
            List<Long> categoryIdList = new ArrayList<>();
            categoryIdList.add(categoryId);
            getCategoryIdList(categoryId, categoryIdList);
            newList = iVideoService.page(page, wrapper().in("video_category", categoryIdList));
        }
        if (newList.getRecords().size() > 0) {
            return R.success().state("newList", newList);
        }
        return R.error();
    }

    @ApiOperation("按分类获取视频列表")
    @GetMapping("/getByCategory")
    public R getByCategory(@RequestParam Long categoryId, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        if (pageSize > 50) {
            pageSize = 50;
        }
        List<Long> categoryIdList = new ArrayList<>();
        categoryIdList.add(categoryId);
        getCategoryIdList(categoryId, categoryIdList);
        Page<Video> page = new Page<>(pageNo, pageSize);
        Page<Video> videoList = iVideoService.page(page, wrapper().in("video_category", categoryIdList));
        if (videoList.getRecords().size() > 0) {
            return R.success().state("videoList", videoList);
        }
        return R.error();
    }

    @ApiOperation("按用户获取视频列表")
    @GetMapping("/getByUser")
    public R getByUser(@RequestParam Integer userId, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        if (pageSize > 50) {
            pageSize = 50;
        }
        Page<Video> page = new Page<>(pageNo, pageSize);
        Page<Video> videoList = iVideoService.page(page, wrapper().eq("video_user_id", userId));
        if (videoList.getRecords().size() > 0) {
            return R.success().state("videoList", videoList);
        }
        return R.error();
    }

    @ApiOperation("ID获取视频信息")
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        Video video = iVideoService.getOne(wrapper().eq("id", id));
        if (video != null) {
            return R.success().state("video", video);
        }
        return R.error();
    }

    public void getCategoryIdList(Long categoryId, List<Long> categoryIdList) {
        List<Category> childCategoryList = iCategoryService.list(new QueryWrapper<Category>().eq("category_parent", categoryId));
        for (Category childCategory : childCategoryList) {
            Long childCategoryId = childCategory.getId();
            categoryIdList.add(childCategoryId);
            getCategoryIdList(childCategoryId, categoryIdList);
        }
    }
}
