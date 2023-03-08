package com.conny.loan.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.conny.loan.domain.Application;
import com.conny.loan.dto.ApplicationDTO.Request;
import com.conny.loan.dto.ApplicationDTO.Response;
import com.conny.loan.repository.ApplicationRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewApplicationEntity_When_RequestCreateApplication() {
        Application entity = Application.builder()
                                        .name("Member Kim")
                                        .cellPhone("010-1111-2222")
                                        .email("mail@abc.de")
                                        .hopeAmount(BigDecimal.valueOf(50000000))
                                        .build();

        Request request = Request.builder()
                                 .name("Member Kim")
                                 .cellPhone("010-1111-2222")
                                 .email("mail@abc.de")
                                 .hopeAmount(BigDecimal.valueOf(50000000))
                                 .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);
        Response actual = applicationService.create(request);
        
        assertThat(actual.getName()).isSameAs(entity.getName());
    }
}