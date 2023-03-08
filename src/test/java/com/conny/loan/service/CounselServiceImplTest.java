package com.conny.loan.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.conny.loan.domain.Counsel;
import com.conny.loan.dto.CounselDTO.Request;
import com.conny.loan.dto.CounselDTO.Response;
import com.conny.loan.exception.BaseException;
import com.conny.loan.exception.ResultType;
import com.conny.loan.repository.CounselRepository;
import java.util.Optional;
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

    @Test
    void Should_ReturnResponseOfExistCounselEntity_When_RequestExistCounselId() {
        Long findId = 1L;
        Counsel entity = Counsel.builder().counselId(1L).build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(findId);

        assertThat(actual.getCounselId()).isEqualTo(findId);
    }

    @Test
    void Should_ThrowException_WHen_RequestNotExistCounselId() {
        Long findId = 2L;
        when(counselRepository.findById(findId)).thenThrow(
            new BaseException(ResultType.SYSTEM_ERROR));

        assertThrows(BaseException.class, () -> counselService.get(findId));

    }

    @Test
    void Should_ReturnUpdatedResponseOfExistCounselEntity_When_RequestUpdateExistCounselInfo() {
        Long findId = 1L;
        Counsel entity = Counsel.builder().counselId(1L).name("Member Kim").build();
        Request request = Request.builder()
                                 .name("Member Kang")
                                 .build();
        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));
        when(counselRepository.save(any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.update(findId, request);

        assertThat(actual.getCounselId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());

    }

}