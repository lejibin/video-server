package com.binhow.video.entity.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
@Getter
@Setter
public class VideoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String year;

    private String duration;

    private String content;

    private LocalDateTime date;

    private Float score;

    private Long hot;

    private String douban;

    private String picture;

    private String base;

    private Long channelId;

    private Boolean comment;

    private List<AreaDto> areaList;

    private List<ArtorDto> artorList;

    private List<ArtorDto> directorList;

    private List<ArtorDto> writerList;

    private List<CategoryDto> categoryList;

    private List<LanguageDto> languageList;

    private List<String> mapIdList;
}
