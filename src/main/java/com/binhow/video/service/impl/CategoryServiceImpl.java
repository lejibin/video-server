package com.binhow.video.service.impl;

import com.binhow.video.entity.Category;
import com.binhow.video.mapper.CategoryMapper;
import com.binhow.video.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
