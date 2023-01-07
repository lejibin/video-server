package com.binhow.video.mapper;

import com.binhow.video.entity.Map;
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
public interface MapMapper extends BaseMapper<Map> {
    /**
     * 根据指定的video_id去video和video_language关联表查出该video所属language列表
     *
     * @param videoId
     * @return
     */
    @Select("select id from v_map where video_id=#{videoId}")
    List<String> getMapIdListByVideoId(Long videoId);
}
