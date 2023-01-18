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
 * @since 2022-12-05
 */
@Getter
@Setter
public class UserDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String rule;

}
