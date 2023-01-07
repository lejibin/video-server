package com.binhow.video.entity.Dto;


import java.io.Serializable;
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
public class UrlDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String url;

    private String name;

    private String site;

    private Boolean download;
}
