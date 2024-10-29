package com.pipa.back.service;

import org.springframework.http.ResponseEntity;

import com.pipa.back.dto.request.auth.CheckCertificationRequestDto;
import com.pipa.back.dto.request.auth.EmailCertificationRequestDto;
import com.pipa.back.dto.request.auth.IdCheckRequestDto;
import com.pipa.back.dto.request.auth.SignInRequestDto;
import com.pipa.back.dto.request.auth.SignUpRequestDto;
import com.pipa.back.dto.response.auth.CheckCertificationResponseDto;
import com.pipa.back.dto.response.auth.EmailCertificationResponseDto;
import com.pipa.back.dto.response.auth.IdCheckResponseDto;
import com.pipa.back.dto.response.auth.SignInResponseDto;
import com.pipa.back.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
