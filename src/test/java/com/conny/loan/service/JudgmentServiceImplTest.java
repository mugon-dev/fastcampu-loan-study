package com.conny.loan.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.conny.loan.domain.Application;
import com.conny.loan.domain.Judgment;
import com.conny.loan.dto.JudgmentDTO.Request;
import com.conny.loan.dto.JudgmentDTO.Response;
import com.conny.loan.repository.ApplicationRepository;
import com.conny.loan.repository.JudgmentRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class JudgmentServiceImplTest {

    @InjectMocks
    private JudgmentServiceImpl judgmentService;

    @Mock
    private JudgmentRepository judgmentRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewJudgmentEntity_When_RequestNewJudgment() {
        Judgment judgmentEntity = Judgment.builder()
                                          .name("Member Kim")
                                          .applicationId(1L)
                                          .approvalAmount(BigDecimal.valueOf(50000000))
                                          .build();

        Application applicationEntity = Application.builder()
                                                   .applicationId(1L)
                                                   .build();

        Request request = Request.builder()
                                 .name("Member Kim")
                                 .applicationId(1L)
                                 .approvalAmount(BigDecimal.valueOf(50000000))
                                 .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(judgmentRepository.save(any(Judgment.class))).thenReturn(judgmentEntity);

        Response actual = judgmentService.create(request);

        assertThat(actual.getJudgmentId()).isSameAs(judgmentEntity.getJudgmentId());
        assertThat(actual.getName()).isSameAs(judgmentEntity.getName());
        assertThat(actual.getApplicationId()).isSameAs(judgmentEntity.getApplicationId());
        assertThat(actual.getApprovalAmount()).isSameAs(judgmentEntity.getApprovalAmount());
    }
}