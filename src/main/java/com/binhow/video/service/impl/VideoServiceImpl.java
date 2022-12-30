package com.binhow.video.service.impl;

import com.binhow.video.entity.Video;
import com.binhow.video.mapper.VideoMapper;
import com.binhow.video.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Richard
 * @since 2022-12-30
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
