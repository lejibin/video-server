package com.binhow.video.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Dto.LanguageDto;
import com.binhow.video.entity.Language;
import com.binhow.video.service.ILanguageService;
import com.binhow.video.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
@Api(tags = "语言模块")
@RestController
@RequestMapping("/language")
public class LanguageController {
    @Resource
    private ILanguageService iLanguageService;
    private static QueryWrapper<Language> wrapper() {
        QueryWrapper<Language> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).eq("filter", 1);
        return wrapper;
    }
    @ApiOperation("获取语言列表")
    @GetMapping
    public R language(@RequestParam(required = false) Long id) {
        if (id == null) {
            List<Language> languageList = iLanguageService.list(wrapper());
            if (languageList.size() > 0) {
                List<LanguageDto> languageDtoList = languageList.stream().map(language -> {
                            LanguageDto languageDto = new LanguageDto();
                            BeanUtils.copyProperties(language, languageDto);
                            return languageDto;
                        }
                ).collect(Collectors.toList());
                return R.success().state("languageList", languageDtoList);
            }
        } else {
            Language language = iLanguageService.getOne(wrapper().eq("id", id));
            if (language != null) {
                LanguageDto languageDto = new LanguageDto();
                BeanUtils.copyProperties(language, languageDto);
                return R.success().state("language", languageDto);
            }
        }
        return R.error();
    }
}