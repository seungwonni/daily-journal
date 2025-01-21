
package com.rate.common.login.dto;

import com.rate.common.login.entity.LoginEntity;
import lombok.Getter;

@Getter
public class Login {
    private String email;
    private String password;
    private String nickname;

    public LoginEntity toEntity() {
        return LoginEntity.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .build();
    }

    public Login getUserInfo(String email, String nickname) {
        Login login = new Login();
        login.email = email;
        login.nickname = nickname;
        return login;
    }

}
