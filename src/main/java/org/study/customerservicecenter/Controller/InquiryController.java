package org.study.customerservicecenter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.study.customerservicecenter.Service.InquiryService;


@Controller
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/home/inquiry")
    public String getInquiries(Model model) {
        model.addAttribute("inquiries", inquiryService.getAllInquiries());
        return "cs/InquiryList";
    }
}
