package com.binhow.video.entity.Dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long parentId;

    private List<CategoryDto> childCategoryList;

}
