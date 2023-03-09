package com.conny.loan.service;

import com.conny.loan.dto.TermsDTO.Request;
import com.conny.loan.dto.TermsDTO.Response;
import java.util.List;

public interface TermsService {

    Response create(Request request);

    List<Response> getAll();
}
