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
import org.study.customerservicecenter.Service.InquiryService;
import org.study.customerservicecenter.Service.LoginService;
import org.study.customerservicecenter.domain.User;

@Slf4j
@Controller
public class MainController {

    private final LoginService loginService;
    private final InquiryService inquiryService;

    @Autowired
    public MainController(LoginService loginService, InquiryService inquiryService) {
        this.loginService = loginService;
        this.inquiryService = inquiryService;
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String LoginSkip(@CookieValue(value = "SESSION", required = false) String sessionId, HttpServletRequest req, ModelMap model) {
        HttpSession session = req.getSession(false); // 기존 세션이 있는 경우에만 가져오기
        if (session != null && sessionId != null && sessionId.equals(session.getId())) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                log.info("세션 확인됌 : "+user.getId());
                model.addAttribute("user", user);
              //  model.addAttribute("inquiries",inquiryService.); //게시판 조회기능 수정
            }
        }
        return "Home";
    }

    @PostMapping("/home")
    public String doLogin(@RequestParam("id") String id, @RequestParam("pwd") String password,
                          HttpServletRequest req, HttpServletResponse resp, ModelMap model) {
        log.info("로그인 시도중");
        try {
            User user = loginService.login(id, password);
            if (user != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);

                // 세션 쿠키를 설정함
                Cookie cookie = new Cookie("SESSION", session.getId());
                cookie.setPath("/"); // 쿠키의 경로를 설정하여 애플리케이션 전체에서 사용 가능하게 합니다.
                resp.addCookie(cookie);

                model.addAttribute("user", user); // 모델에 사용자 정보 추가
                log.info(user.getId() + " 로그인 성공");
                return "redirect:/home";
            } else {
                log.warn("로그인 실패: 유효하지 않은 사용자 ID 또는 비밀번호");
                model.addAttribute("error", "Id 또는 Password가 잘못되었습니다.");
                return "Home";
            }
        } catch (Exception e) {
            log.error("로그인 중 오류 발생: ", e);
            model.addAttribute("error", "로그인 중 오류가 발생했습니다. 다시 시도해 주세요.");
            return "Home";
        }
    }
}