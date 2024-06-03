package com.system.service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.entity.data.Submission;
import com.system.mapper.SubmissionMapper;
import com.system.service.SubmissionService;
import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpl extends ServiceImpl<SubmissionMapper, Submission> implements SubmissionService {
}
