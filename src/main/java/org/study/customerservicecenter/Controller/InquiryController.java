package org.study.customerservicecenter.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.study.customerservicecenter.Service.InquiryService;
import org.study.customerservicecenter.domain.Inquiry;
import org.study.customerservicecenter.domain.User;

@Slf4j
@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/home/inquiries")
    public String getInquiries(HttpServletRequest req,Model model) {
        HttpSession session = req.getSession();
        if(session!=null)
        {
            User user = (User)session.getAttribute("user");
            if(user!=null)
            {
                model.addAttribute("user", user);
            }
        }
        model.addAttribute("inquiries", inquiryService.getAllInquiries());

        return "cs/InquiryList";
    }

    //새 문의사항 작성
    @GetMapping("/inquiries/new")
    public String openInquiryForm(Model model) {
        model.addAttribute("inquiry", new Inquiry()); //게시글 작성을 위한 선언
        log.info("새로운 문의사항 클래스 생성");
        return "cs/InquiryCreate";
    }



    @RequestMapping("/inquiries/{id}")
    public String getInquiry(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        Inquiry inquiry = inquiryService.getInquiryById(id);
        model.addAttribute("inquiry", inquiry);

        // 세션에서 사용자 정보 가져오기
        HttpSession session = request.getSession(false);
        if (session != null) {
            User currentUser = (User) session.getAttribute("user");
            if (currentUser != null) {
                // 작성자인지 확인
                boolean isAuthor = inquiry.getAuthorId().equals(currentUser.getId());
                model.addAttribute("isAuthor", isAuthor);
            } else {
                model.addAttribute("isAuthor", false);
            }
        } else {
            model.addAttribute("isAuthor", false);
        }

        return "cs/InquiryDetail";
    }

}