package com.binhow.video;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Dto.VideoDto;
import com.binhow.video.entity.Video;
import com.binhow.video.mapper.VideoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class VideoServerApplicationTests {
    @Resource
    VideoMapper videoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void getVideoList() {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        VideoDto videoDto = videoMapper.getVideo(wrapper);
        System.out.println(videoDto.getName());
    }
}
