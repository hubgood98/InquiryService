package org.study.customerservicecenter.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.study.customerservicecenter.Service.InquiryService;
import org.study.customerservicecenter.domain.Inquiry;
import org.study.customerservicecenter.domain.User;

@Slf4j
@Controller
public class InquiryManageController {
//문의사항 입력을 처리하는 로직

    @Autowired
    private InquiryService inquiryService;

    InquiryManageController(InquiryService inquiryService) {
        log.info("문의사항 작성폼 열기성공");
        this.inquiryService = inquiryService;
    }

    //문의사항 등록
    @PostMapping("/inquiries/new")
    public String writeInquiry(@RequestParam("title") String title,
                               @RequestParam("category") String category,
                               @RequestParam("content") String content,
                               HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                Inquiry inquiry = new Inquiry();
                inquiry.setTitle(title);
                inquiry.setCategory(category);
                inquiry.setContent(content);
                inquiry.setAuthorId(user.getId());
                log.info("문의 유저 정보 불러오기 완료");
                inquiryService.saveInquiry(inquiry);
                log.info("문의사항 저장 완료");
                return "redirect:/home/inquiries";
            }
        }
        model.addAttribute("error", "로그인 세션이 만료되었습니다.");
        return "cs/InquiryCreate";
    }

    //문의사항 수정
    @GetMapping("/inquiries/{id}/edit")
    public String openEditForm(@PathVariable("id") Long id, Model model) {
        log.info("게시글에서 수정버튼 누름");
        Inquiry inquiry = inquiryService.getInquiryById(id);
        model.addAttribute("inquiry", inquiry);
        return "cs/InquiryEdit";
    }

    //문의사항 삭제
    @PostMapping("/inquiries/{id}/delete")
    public String removeInquiry(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        inquiryService.removeInquiry(id);
        redirectAttributes.addFlashAttribute("message", "Inquiry removed successfully.");
        return "redirect:/home/inquiries";
    }

    //문의사항 업데이트
    @PostMapping("/inquiries/update/{id}")
    public String updateInquiry(@PathVariable("id") Long id,
                                @RequestParam("title") String title,
                                @RequestParam("category") String category,
                                @RequestParam("content") String content,
                                RedirectAttributes redirectAttributes) {
        try {
            inquiryService.updateInquiry(id, title, category, content);
            redirectAttributes.addFlashAttribute("message", "Inquiry updated successfully.");
            log.info("문의사항 업데이트 완료");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update inquiry.");
            log.error("Error updating inquiry: ", e);
        }
        return "redirect:/home/inquiries";
    }

    //관리자 답글 폼열기
    @GetMapping("/cs/adminReply/reply_form")
    public String openEditForm() {
        return "cs/adminReply/reply_form";
    }
}
