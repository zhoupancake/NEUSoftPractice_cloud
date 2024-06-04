package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.RequestSubmissionEntity;
import com.system.entity.data.AirData;
import com.system.entity.data.Submission;
import com.system.service.AirDataServiceFallback;
import com.system.service.SubmissionService;
import com.system.service.TaskServiceFallback;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/submission")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final SubmissionService submissionService;
    @Resource
    private final TaskServiceFallback taskServiceFallback;
    private final AirDataServiceFallback airDataServiceFallback;
    @PostMapping("/submit")
    public HttpResponseEntity submit(@RequestBody RequestSubmissionEntity requestSubmissionEntity) {
        Submission submission = requestSubmissionEntity.getSubmission_create();
        AirData airData = requestSubmissionEntity.getAirData_create();

        if(null == taskServiceFallback.getTaskById(submission.getTaskId()))
            return HttpResponseEntity.error("任务不存在");

        boolean airDataSuccess = airDataServiceFallback.addAirData(airData);
        boolean submissionSuccess = submissionService.save(submission);

        return HttpResponseEntity.response(airDataSuccess && submissionSuccess, "提交成功", null);
    }
}
