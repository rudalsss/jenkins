package com.example.oliveback.service.user;

import com.example.oliveback.domain.user.Users;
import com.example.oliveback.dto.user.UserSignupRequest;
import com.example.oliveback.dto.user.UserResponse;
import com.example.oliveback.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public UserResponse signup(UserSignupRequest request) {
        // username 중복 체크
        if (usersRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        // 회원 저장
        Users newUser = Users.builder()
                .username(request.getUsername())
                .password(request.getPassword()) // 비밀번호 해싱 필요
                .email(request.getEmail())
                .build();

        usersRepository.save(newUser);
        return UserResponse.fromEntity(newUser);
    }
}
