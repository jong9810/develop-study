package com.example.testsecurity.service;

import com.example.testsecurity.dto.JoinDto;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {

        String username = joinDto.getUsername();

        // 회원 중복 검증
        // 1. 프론트단에서도 검증
        // httpXMLRequest 메소드를 통해 백엔드에 미리 구현해둔 API에 이미 존재하는 username인지 검증하는 로직을 추가해야 한다.
        // 2. 가입 불가 문자 정규식 처리
        // * 아이디의 자리수
        // * 아이디의 특수문자 포함 불가
        // * admin과 같은 아이디 사용 불가
        // * 비밀번호 자리수
        // * 비밀번호 특수문자 포함 필수
        boolean isUser = userRepository.existsByUsername(username);
        if (isUser) {
            return;
        }

        UserEntity data = new UserEntity();
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
