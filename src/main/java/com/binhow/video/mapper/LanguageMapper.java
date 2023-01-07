package com.binhow.video.mapper;

import com.binhow.video.entity.Dto.LanguageDto;
import com.binhow.video.entity.Language;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface LanguageMapper extends BaseMapper<Language> {
    /**
     * 根据指定的video_id去video和video_language关联表查出该video所属language列表
     *
     * @param videoId
     * @return
     */
    @Select("select * from v_language left join v_video_language on v_language.id=v_video_language.language_id where v_video_language.video_id=#{videoId}")
    List<LanguageDto> getLanguageListByVideoId(Long videoId);
}
