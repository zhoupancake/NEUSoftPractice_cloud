package com.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.entity.data.Submission;
import com.system.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submission")
@Slf4j
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService submissionService;
    @PostMapping("/addSubmission")
    public HttpResponseEntity addSubmission(@RequestBody Submission submission) {
        boolean success = submissionService.save(submission);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifySubmission")
    public HttpResponseEntity modifySubmission(@RequestBody Submission submission) {
        boolean success = submissionService.updateById(submission);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteSubmission")
    public HttpResponseEntity deleteSubmissionById(@RequestBody Submission submission) {
        boolean success = submissionService.removeById(submission);
        return HttpResponseEntity.response(success, "删除", null);
    }

    @PostMapping("/querySubmissionList")
    public HttpResponseEntity querySubmissionList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Submission> page = new Page<>(pageNum, pageSize);
        submissionService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<Submission> submissionList = page.getRecords();
        boolean success = !submissionList.isEmpty();
        return HttpResponseEntity.response(success, "查询", submissionList);
    }
}
