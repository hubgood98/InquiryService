package org.study.customerservicecenter.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class logoutController {

    @PostMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false); //getSession 세션 반환하지만 boolean값에 따라 다름 -> true : 존재하지 않으면 새로 생성, false : 세션 존재하지 않으면 null 반환
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("SESSION", null);
        cookie.setPath("/");
        cookie.setMaxAge(0); //쿠키유효기간 0으로
        resp.addCookie(cookie);

        return "redirect:/home";
    }
}
