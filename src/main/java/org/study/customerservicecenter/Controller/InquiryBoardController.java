package org.study.customerservicecenter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.study.customerservicecenter.Service.InquiryService;
import org.study.customerservicecenter.domain.Inquiry;

import java.util.List;

@Controller
public class InquiryBoardController {

    private final InquiryService inquiryService;

    public InquiryBoardController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/home/inquiry")
    public String listInquiry(Model model) {
        List<Inquiry> lists = inquiryService.getAllInquiries();
        model.addAttribute("InquiryLists", lists);
        return "cs/InquiryList";
    }
}
