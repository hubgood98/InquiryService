package org.study.customerservicecenter.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.customerservicecenter.domain.User;
import org.study.customerservicecenter.repository.UserRepository;

@Controller
public class MainController {

    private final UserRepository userRepository;

    MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(@CookieValue(value = "SESSION", required = false) String sessionId,HttpServletRequest req, ModelMap model)
    {
        HttpSession session = req.getSession();
        if(session != null && sessionId != null && sessionId.equals(session.getId()))
        {
            User user = (User) session.getAttribute("user");
            if(user != null)
            {
                model.addAttribute("user", user);
            }
        }
        return "Home";
    }

    @PostMapping("/home")
    public String doLogin(@CookieValue(value = "SESSION", required = false) @RequestParam("id") String id, @RequestParam("pwd") String password,
                          HttpServletRequest req, HttpServletResponse resp, ModelMap model)
    {
        if(userRepository.matches(id,password))
        {
            HttpSession session = req.getSession(true);
            User user = userRepository.findById(id).orElse(null);
            if(user == null)
            {
                user = new User(id,password);
                userRepository.save(user);
            }
            session.setAttribute("user", user);

            // 세션 쿠키를 설정합니다.
            Cookie cookie = new Cookie("SESSION", session.getId());
            cookie.setPath("/"); // 쿠키의 경로를 설정하여 애플리케이션 전체에서 사용 가능하게 합니다.
            resp.addCookie(cookie);

            model.addAttribute("user", user);

        }else {
            // 로그인 실패 시 main 페이지로 리디렉션합니다.
            model.addAttribute("error", "Id 또는 Password가 잘못되었습니다.");
        }
        return "Home";
    }

}
