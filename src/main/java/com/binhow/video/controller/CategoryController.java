package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Dto.CategoryDto;
import com.binhow.video.service.ICategoryService;
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
@Api(tags = "分类模块")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private ICategoryService iCategoryService;

    private static QueryWrapper<Category> wrapper() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("category_status", 1);
        return wrapper;
    }

    @ApiOperation("获取分类列表")
    @GetMapping
    public R category(@RequestParam(required = false) Long id) {
        List<CategoryDto> categoryDtoList = getCategoryDtoList();
        if (categoryDtoList != null) {
            if (id == null) {
                categoryDtoList = categoryDtoList.stream().filter(categoryDto -> categoryDto.getCategoryParent().equals(0L)).collect(Collectors.toList());
            } else {
                categoryDtoList = categoryDtoList.stream().filter(categoryDto -> categoryDto.getId().equals(id)).collect(Collectors.toList());
            }
            if (categoryDtoList.size() > 0) {
                return R.success().state("categoryList", categoryDtoList);
            }
            return R.error();
        }
        return R.error();
    }

    public List<CategoryDto> getCategoryDtoList() {
        List<Category> categoryList = iCategoryService.list(wrapper());
        if (categoryList != null) {
            List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> {
                CategoryDto categoryDto = new CategoryDto();
                BeanUtils.copyProperties(category, categoryDto);
                return categoryDto;
            }).collect(Collectors.toList());
            categoryDtoList.forEach(categoryDto -> {
                List<CategoryDto> childCategoryDtoList = getChildCategoryDtoList(categoryDto, categoryDtoList);
                categoryDto.setChildCategoryList(childCategoryDtoList);
            });
            return categoryDtoList;
        }
        return null;
    }

    public static List<CategoryDto> getChildCategoryDtoList(CategoryDto categoryDto, List<CategoryDto> categoryDtoList) {
        return categoryDtoList.stream().filter(dto -> dto.getCategoryParent().equals(categoryDto.getId())).collect(Collectors.toList());
    }
}
