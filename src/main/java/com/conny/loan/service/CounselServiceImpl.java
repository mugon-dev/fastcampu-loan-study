package com.conny.loan.service;

import com.conny.loan.domain.Counsel;
import com.conny.loan.dto.CounselDTO.Request;
import com.conny.loan.dto.CounselDTO.Response;
import com.conny.loan.repository.CounselRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService {

    private final CounselRepository counselRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class);
        counsel.setAppliedAt(LocalDateTime.now());
        Counsel created = counselRepository.save(counsel);
        return modelMapper.map(created, Response.class);
    }
}
