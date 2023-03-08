package com.conny.loan.service;

import com.conny.loan.domain.Application;
import com.conny.loan.dto.ApplicationDTO.Request;
import com.conny.loan.dto.ApplicationDTO.Response;
import com.conny.loan.repository.ApplicationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Request request) {
        Application application = modelMapper.map(request, Application.class);
        application.setAppliedAt(LocalDateTime.now());
        Application applied = applicationRepository.save(application);
        return modelMapper.map(applied, Response.class);
    }

}
