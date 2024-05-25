package org.study.customerservicecenter.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.customerservicecenter.Service.LoginService;
import org.study.customerservicecenter.domain.User;

@Slf4j
@Controller
public class MainController {

    private final LoginService loginService;

    @Autowired
    public MainController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String LoginSkip(@CookieValue(value = "SESSION", required = false) String sessionId, HttpServletRequest req, ModelMap model) {
        HttpSession session = req.getSession(false); // 기존 세션이 있는 경우에만 가져오기
        if (session != null && sessionId != null && sessionId.equals(session.getId())) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                log.info("세션 확인됌 : "+user.getId());
                model.addAttribute("user", user);
            }
        }
        return "Home";
    }

    @PostMapping("/home")
    public String doLogin(@RequestParam("id") String id, @RequestParam("pwd") String password,
                          HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
        log.info("로그인 시도중");
        User user = loginService.login(id, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

            // 세션 쿠키를 설정함
            Cookie cookie = new Cookie("SESSION", session.getId());
            cookie.setPath("/"); // 쿠키의 경로를 설정하여 애플리케이션 전체에서 사용 가능하게 합니다.
            resp.addCookie(cookie);

            model.addAttribute("user", user); // 모델에 사용자 정보 추가

            log.info(user.getId()+" 로그인 성공");
            return "redirect:/";
        }
        // 로그인 실패 시 리디렉션
        model.addAttribute("error", "Id 또는 Password가 잘못되었습니다.");
        return "Home";
    }
}