package com.binhow.video.mapper;

import com.binhow.video.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.binhow.video.entity.Dto.CategoryDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 根据指定的video_id去video和video_category关联表查出该video所属category列表
     *
     * @param videoId
     * @return
     */
    @Select("select * from v_category left join v_video_category on v_category.id=v_video_category.category_id where v_video_category.video_id=#{videoId}")
    List<CategoryDto> getCategoryListByVideoId(Long videoId);

    @Select("selec * from v_category where parent_id=#{id}")
    List<CategoryDto> childCategoryList(Long id);
}

