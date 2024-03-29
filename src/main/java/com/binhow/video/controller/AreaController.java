package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Area;
import com.binhow.video.entity.Dto.AreaDto;
import com.binhow.video.service.impl.AreaServiceImpl;
import com.binhow.video.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
@Api(tags = "地区模块")
@RestController
@RequestMapping("/area")
@CrossOrigin
public class AreaController {
    @Resource
    AreaServiceImpl areaService;

    private static QueryWrapper<Area> wrapper() {
        return new QueryWrapper<Area>().eq("status", 1).eq("filter", 1);
    }

    @ApiOperation("获取区域列表")
    @GetMapping
    public R area(@RequestParam(required = false) Long id) {
        if (id == null) {
            List<Area> areaList = areaService.list(wrapper());
            if (areaList.size() > 0) {
                List<AreaDto> areaDtoList = areaList.stream().map(area -> {
                    AreaDto areaDto = new AreaDto();
                    BeanUtils.copyProperties(area, areaDto);
                    return areaDto;
                }).collect(Collectors.toList());
                return R.success().state("areaList", areaDtoList);
            }
        } else {
            Area area = areaService.getOne(wrapper().eq("id", id));
            if (area != null) {
                AreaDto areaDto = new AreaDto();
                BeanUtils.copyProperties(area, areaDto);
                return R.success().state("area", areaDto);
            }
        }
        return R.error();
    }
}
