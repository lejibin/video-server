package com.binhow.video.entity.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
public class ArtorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

}
