package com.conny.loan.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.conny.loan.domain.Terms;
import com.conny.loan.dto.TermsDTO.Request;
import com.conny.loan.dto.TermsDTO.Response;
import com.conny.loan.repository.TermsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class TermsServiceImplTest {

    @InjectMocks
    TermsServiceImpl termsService;

    @Mock
    private TermsRepository termsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewTermsEntity_When_RequestTerm() {
        Terms entity = Terms.builder()
                            .name("대출 이용 약관")
                            .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsd")
                            .build();

        Request request = Request.builder()
                                 .name("대출 이용 약관")
                                 .termsDetailUrl("https://abc-storage.acc/dslfjdlsfjlsd")
                                 .build();

        when(termsRepository.save(any(Terms.class))).thenReturn(entity);

        Response actual = termsService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getTermsDetailUrl()).isSameAs(entity.getTermsDetailUrl());
    }
}