package org.hyunsu.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hyunsu.domain.User;
import org.hyunsu.dto.response.UserSimpleResponseDto;
import org.hyunsu.dto.request.LoginRequestDto;
import org.hyunsu.repository.UserRepository;
import org.hyunsu.service.s.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public UserSimpleResponseDto login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (!loginRequestDto.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못됐습니다.");
        }

        HttpSession session = request.getSession(); // session이 존재하지 않으면 새로운 세션 생성
        session.setAttribute("user", user);

        return userService.convertUserToSimpleDto(user, user);
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // false를 전달하면, 현재 session이 존재하지 않으면 null을 반환
        if (session != null) {
            session.invalidate();  // 세션 비활성화
        }
    }

    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            throw new IllegalStateException("로그인되지 않았습니다.");  // unAuthorized
        }
        return (User) session.getAttribute("user");
    }
}
