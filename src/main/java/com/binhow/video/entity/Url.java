package com.binhow.video.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("v_url")
@ApiModel(value = "Url对象", description = "")
public class Url implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String url;

    private String html;

    private String name;

    private String site;

    private String mapId;

    private Long videoId;

    private Boolean download;

    private LocalDateTime dateLatest;

    private Boolean status;


}
