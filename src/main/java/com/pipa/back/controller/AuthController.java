package com.pipa.back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pipa.back.dto.request.auth.CheckCertificationRequestDto;
import com.pipa.back.dto.request.auth.EmailCertificationRequestDto;
import com.pipa.back.dto.request.auth.IdCheckRequestDto;
import com.pipa.back.dto.request.auth.SignUpRequestDto;
import com.pipa.back.dto.response.auth.CheckCertificationResponseDto;
import com.pipa.back.dto.response.auth.EmailCertificationResponseDto;
import com.pipa.back.dto.response.auth.IdCheckResponseDto;
import com.pipa.back.dto.response.auth.SignUpResponseDto;
import com.pipa.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(
            @RequestBody @Valid IdCheckRequestDto dto) {
        ResponseEntity<? super IdCheckResponseDto> responseBody = authService.idCheck(dto);
        return responseBody;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Valid EmailCertificationRequestDto dto) {
        ResponseEntity<? super EmailCertificationResponseDto> responseBody = authService.emailCertification(dto);
        return responseBody;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto dto) {
        ResponseEntity<? super CheckCertificationResponseDto> responseBody = authService.checkCertification(dto);
        return responseBody;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signup(
            @RequestBody @Valid SignUpRequestDto dto) {
        ResponseEntity<? super SignUpResponseDto> responseBody = authService.signUp(dto);
        return responseBody;
    }

}
