package com.pipa.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pipa.back.common.CertificationNumber;
import com.pipa.back.dto.request.auth.CheckCertificationRequestDto;
import com.pipa.back.dto.request.auth.EmailCertificationRequestDto;
import com.pipa.back.dto.request.auth.IdCheckRequestDto;
import com.pipa.back.dto.response.ResponseDto;
import com.pipa.back.dto.response.auth.CheckCertificationResponseDto;
import com.pipa.back.dto.response.auth.EmailCertificationResponseDto;
import com.pipa.back.dto.response.auth.IdCheckResponseDto;
import com.pipa.back.entity.CertificationEntity;
import com.pipa.back.provider.EmailProvider;
import com.pipa.back.repository.CertificationRepository;
import com.pipa.back.repository.UserRepository;
import com.pipa.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {
    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final EmailProvider emailProvider;

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);

            if (isExistId)
                return IdCheckResponseDto.duplicateId();

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseDto.databaseError();
        }
        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();
            boolean isExistId = userRepository.existsByUserId(userId);

            if (isExistId)
                return EmailCertificationResponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);

            if (!isSuccessed)
                return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);

            certificationRepository.save(certificationEntity);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseDto.databaseError();
        }
        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try {
            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.getByUserId(userId);
            if (certificationEntity == null)
                return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email)
                    && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched)
                return CheckCertificationResponseDto.certificationFail();

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseDto.databaseError();
        }
        return CheckCertificationResponseDto.success();
    }
}
