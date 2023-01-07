package com.binhow.video.mapper;

import com.binhow.video.entity.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.binhow.video.entity.Dto.AreaDto;
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
public interface AreaMapper extends BaseMapper<Area> {
    /**
     * 根据指定的video_id去video和video_area关联表查出该video所属area列表
     *
     * @param videoId
     * @return
     */
    @Select("SELECT * FROM v_area LEFT JOIN v_video_area ON v_area.id=v_video_area.area_id WHERE v_video_area.video_id=#{videoId}")
    List<AreaDto> getAreaListByVideoId(Long videoId);
}
