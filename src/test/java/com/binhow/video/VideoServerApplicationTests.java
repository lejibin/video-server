package com.binhow.video;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.binhow.video.entity.Dto.VideoDto;
import com.binhow.video.entity.Video;
import com.binhow.video.mapper.VideoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    void testRetainAll(){
        List<Long> l= new ArrayList<>();
        l.add(1L);
        l.add(2L);
        l.add(3L);
        l.add(4L);
        l.add(5L);
        l.add(6L);
        l.add(7L);
        List<Long> x= new ArrayList<>();
        boolean t = l.retainAll(x);
        System.out.printf(l.toString());
    }
}
