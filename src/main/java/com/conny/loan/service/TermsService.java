package com.conny.loan.service;

import com.conny.loan.dto.TermsDTO.Request;
import com.conny.loan.dto.TermsDTO.Response;

public interface TermsService {

    Response create(Request request);

}
