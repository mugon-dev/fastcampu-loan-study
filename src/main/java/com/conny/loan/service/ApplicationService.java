package com.conny.loan.service;

import com.conny.loan.dto.ApplicationDTO.Request;
import com.conny.loan.dto.ApplicationDTO.Response;

public interface ApplicationService {

    Response create(Request request);

    Response get(Long applicationId);
}
