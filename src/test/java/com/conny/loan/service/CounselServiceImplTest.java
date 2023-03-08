package com.conny.loan.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.conny.loan.domain.Counsel;
import com.conny.loan.dto.CounselDTO.Request;
import com.conny.loan.dto.CounselDTO.Response;
import com.conny.loan.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CounselServiceImplTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy // mocking하지 않고 순수하게 쓰기 위해 사용
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
        Counsel entity = Counsel.builder()
                                .name("Member Kim")
                                .cellPhone("010-1111-2222")
                                .email("mail@abc.de")
                                .memo("I hope to get a loan")
                                .zipCode("123456")
                                .address("Somewhere in Gangnam-gu, Seoul")
                                .addressDetail("What Apartment No. 101, 1st floor No. 101")
                                .build();

        Request request = Request.builder()
                                 .name("Member Kim")
                                 .cellPhone("010-1111-2222")
                                 .email("mail@abc.de")
                                 .memo("I hope to get a loan")
                                 .zipCode("123456")
                                 .address("Somewhere in Gangnam-gu, Seoul")
                                 .addressDetail("What Apartment No. 101, 1st floor No. 101")
                                 .build();

        when(counselRepository.save(any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);
        assertThat(actual.getName()).isSameAs(entity.getName());
    }

}