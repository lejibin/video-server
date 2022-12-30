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
@TableName("v_video_artor")
@ApiModel(value = "VideoArtor对象", description = "")
public class VideoArtor implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long videoId;

    private Long artorId;

    private LocalDateTime dateLatest;

    private Boolean status;


}
