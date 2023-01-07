package com.binhow.video.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Dto.VideoDto;
import com.binhow.video.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
public interface VideoMapper extends BaseMapper<Video> {
    //OneToMany一对多查询
    @Select("select * from v_video where ${ew.sqlSegment}")
    @Results({@Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "year", property = "year"),
            @Result(column = "duration", property = "duration"),
            @Result(column = "content", property = "content"),
            @Result(column = "date", property = "date"),
            @Result(column = "score", property = "score"),
            @Result(column = "hot", property = "hot"),
            @Result(column = "douban", property = "douban"),
            @Result(column = "picture", property = "picture"),
            @Result(column = "base", property = "base"),
            @Result(column = "id", property = "areaList", many = @Many(select = "com.binhow.video.mapper.AreaMapper.getAreaListByVideoId")),
            @Result(column = "id", property = "artorList", many = @Many(select = "com.binhow.video.mapper.ArtorMapper.getArtorListByVideoId")),
            @Result(column = "id", property = "directorList", many = @Many(select = "com.binhow.video.mapper.ArtorMapper.getDirectorListByVideoId")),
            @Result(column = "id", property = "writerList", many = @Many(select = "com.binhow.video.mapper.ArtorMapper.getWriterListByVideoId")),
            @Result(column = "id", property = "categoryList", many = @Many(select = "com.binhow.video.mapper.CategoryMapper.getCategoryListByVideoId")),
            @Result(column = "id", property = "languageList", many = @Many(select = "com.binhow.video.mapper.LanguageMapper.getLanguageListByVideoId")),
            @Result(column = "id", property = "mapIdList", many = @Many(select = "com.binhow.video.mapper.MapMapper.getMapIdListByVideoId"))
    })
    VideoDto getVideo(@Param("ew") QueryWrapper<Video> wrapper);

    @Select("SELECT DISTINCT `year` FROM v_video ORDER BY `year` DESC;")
    List<String> getYearList();
}
