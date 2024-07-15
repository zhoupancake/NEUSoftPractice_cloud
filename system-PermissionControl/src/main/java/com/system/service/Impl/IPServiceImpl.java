package com.system.service.Impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.dto.IP;
import com.system.mapper.IPMapper;
import com.system.service.IPService;
import org.springframework.stereotype.Service;

@Service
@DS("service")
public class IPServiceImpl extends ServiceImpl<IPMapper, IP> implements IPService {
}
