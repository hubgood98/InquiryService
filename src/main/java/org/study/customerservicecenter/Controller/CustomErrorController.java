package org.study.customerservicecenter.Controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(Model model, WebRequest webRequest) {
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        model.addAttribute("timestamp", errorAttributes.get("timestamp"));
        model.addAttribute("error", errorAttributes.get("error"));
        model.addAttribute("message", errorAttributes.get("message"));
        model.addAttribute("path", errorAttributes.get("path"));

        Integer status = (Integer) errorAttributes.get("status");
        model.addAttribute("status", status);

        if (status == null) {
            return "error/default"; // 기본 오류 페이지
        } else if (status == 400) {
            return "error/400";
        } else if (status == 403) {
            return "error/403";
        } else if (status == 404) {
            return "error/404";
        } else if (status == 500) {
            return "error/500";
        } else {
            return "error/default"; // 기본 오류 페이지
        }
    }
}