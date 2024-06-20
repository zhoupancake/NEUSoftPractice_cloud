package com.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.entity.data.City;
import com.system.mapper.CityMapper;
import com.system.service.CityService;
import org.springframework.stereotype.Service;


@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
}
