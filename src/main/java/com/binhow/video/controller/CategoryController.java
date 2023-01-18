package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Category;
import com.binhow.video.entity.Dto.CategoryDto;
import com.binhow.video.service.impl.CategoryServiceImpl;
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
@Api(tags = "分类模块")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryServiceImpl categoryService;

    private static QueryWrapper<Category> wrapper() {
        return new QueryWrapper<Category>().eq("status", 1).eq("filter", 1);
    }

    @ApiOperation("获取分类列表")
    @GetMapping
    public R category(@RequestParam(required = false) Long channelId) {
        QueryWrapper<Category> wrapper = wrapper();
        if (channelId != null) {
            wrapper = wrapper().eq("channel_id", channelId);
        }
        List<Category> categoryList = categoryService.list(wrapper);
        if (categoryList.size() > 0) {
            List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> {
                CategoryDto categoryDto = new CategoryDto();
                BeanUtils.copyProperties(category, categoryDto);
                return categoryDto;
            }).collect(Collectors.toList());
            return R.success().state("categoryList", categoryDtoList);
        }
        return R.error();
    }
}
