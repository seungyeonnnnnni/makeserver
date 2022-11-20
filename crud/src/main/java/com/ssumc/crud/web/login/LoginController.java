package com.ssumc.crud.web.login;

import com.ssumc.crud.SessionConst;
import com.ssumc.crud.domain.login.LoginService;
import com.ssumc.crud.domain.user.User;
import com.ssumc.crud.session.SessionManager;
import com.ssumc.crud.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "login/loginForm";
        }

        User loginUser = loginService.login(form.getLoginEmail(), form.getPassword());
        if (loginUser == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginUser);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        Httpseesion
    }
}
