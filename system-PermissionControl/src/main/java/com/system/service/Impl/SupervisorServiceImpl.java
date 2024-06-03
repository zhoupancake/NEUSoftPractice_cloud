package com.system.service.Impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.entity.character.Supervisor;
import com.system.mapper.SupervisorMapper;
import com.system.service.SupervisorService;
import org.springframework.stereotype.Service;

@Service
public class SupervisorServiceImpl extends ServiceImpl<SupervisorMapper, Supervisor> implements SupervisorService {
}
