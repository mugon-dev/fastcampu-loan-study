package com.conny.loan.service;

import com.conny.loan.dto.JudgmentDTO.Request;
import com.conny.loan.dto.JudgmentDTO.Response;

public interface JudgmentService {

    Response create(Request request);

    Response get(Long judgmentId);

    Response getJudgmentOfApplication(Long applicationId);

    Response update(Long judgmentId, Request request);
}
