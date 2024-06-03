package com.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.entity.character.Administrator;
import com.system.mapper.AdministratorMapper;
import com.system.service.AdministratorService;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {
}
