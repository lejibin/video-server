package com.binhow.video.mapper;

import com.binhow.video.entity.Artor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.binhow.video.entity.Dto.ArtorDto;
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
public interface ArtorMapper extends BaseMapper<Artor> {
    /**
     * 根据指定的video_id去video和video_artor关联表查出该video的artor列表
     *
     * @param videoId
     * @return
     */
    @Select("select * from v_artor left join v_video_artor on v_artor.id=v_video_artor.artor_id where v_video_artor.video_id=#{videoId}")
    List<ArtorDto> getArtorListByVideoId(Long videoId);
    /**
     * 根据指定的video_id去video和video_director关联表查出该video的director列表
     *
     * @param videoId
     * @return
     */
    @Select("select * from v_artor left join v_video_director on v_artor.id=v_video_director.director_id where v_video_director.video_id=#{videoId}")
    List<ArtorDto> getDirectorListByVideoId(Long videoId);
    /**
     * 根据指定的video_id去video和video_writer关联表查出该video的writer列表
     *
     * @param videoId
     * @return
     */
    @Select("select * from v_artor left join v_video_writer on v_artor.id=v_video_writer.writer_id where v_video_writer.video_id=#{videoId}")
    List<ArtorDto> getWriterListByVideoId(Long videoId);
}
