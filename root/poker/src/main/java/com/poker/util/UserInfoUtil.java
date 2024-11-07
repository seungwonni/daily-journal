package com.poker.util;

import com.poker.login.dto.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserInfoUtil {
    public static Login getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String nickname = (String) session.getAttribute("nickname");
        return new Login().getUserInfo(userId, nickname);
    }
}
