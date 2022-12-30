package com.binhow.video.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("v_map")
@ApiModel(value = "Map对象", description = "")
public class Map implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String detail;

    private Long videoId;

    private LocalDateTime dateLatest;

    private Boolean status;


}
