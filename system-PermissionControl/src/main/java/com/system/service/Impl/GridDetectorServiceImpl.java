package com.system.service.Impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.entity.character.GridDetector;
import com.system.mapper.GridDetectorMapper;
import com.system.service.GridDetectorService;
import org.springframework.stereotype.Service;

@Service
@DS("public")
public class GridDetectorServiceImpl extends ServiceImpl<GridDetectorMapper, GridDetector> implements GridDetectorService {
}
