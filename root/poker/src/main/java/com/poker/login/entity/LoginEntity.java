package com.poker.login.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="login")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNum;
    private String email;
    private String password;
    private String nickname;
}
