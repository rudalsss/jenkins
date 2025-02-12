package com.example.oliveback.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // 예약어 user 대신 users 사용
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;
}
