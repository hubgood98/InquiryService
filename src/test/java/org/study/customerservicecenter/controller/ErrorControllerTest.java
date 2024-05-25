package org.study.customerservicecenter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.study.customerservicecenter.Controller.CustomErrorController;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(CustomErrorController.class)
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ErrorAttributes errorAttributes;

    @Test
    void testBadRequest() throws Exception {
        Map<String, Object> errorAttributesMap = new HashMap<>();
        errorAttributesMap.put("status", 400);
        errorAttributesMap.put("error", "Bad Request");
        errorAttributesMap.put("message", "Bad Request");
        errorAttributesMap.put("timestamp", System.currentTimeMillis());
        errorAttributesMap.put("path", "/error");

        given(errorAttributes.getErrorAttributes(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).willReturn(errorAttributesMap);

        mockMvc.perform(get("/error")
                        .requestAttr("javax.servlet.error.status_code", 400)
                        .requestAttr("javax.servlet.error.request_uri", "/error"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("error/400"));
    }

    @Test
    void testForbidden() throws Exception {
        Map<String, Object> errorAttributesMap = new HashMap<>();
        errorAttributesMap.put("status", 403);
        errorAttributesMap.put("error", "Forbidden");
        errorAttributesMap.put("message", "Forbidden");
        errorAttributesMap.put("timestamp", System.currentTimeMillis());
        errorAttributesMap.put("path", "/error");

        given(errorAttributes.getErrorAttributes(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).willReturn(errorAttributesMap);

        mockMvc.perform(get("/error")
                        .requestAttr("javax.servlet.error.status_code", 403)
                        .requestAttr("javax.servlet.error.request_uri", "/error"))
                .andExpect(status().isForbidden())
                .andExpect(view().name("error/403"));
    }

    @Test
    void testNotFound() throws Exception {
        Map<String, Object> errorAttributesMap = new HashMap<>();
        errorAttributesMap.put("status", 404);
        errorAttributesMap.put("error", "Not Found");
        errorAttributesMap.put("message", "Not Found");
        errorAttributesMap.put("timestamp", System.currentTimeMillis());
        errorAttributesMap.put("path", "/error");

        given(errorAttributes.getErrorAttributes(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).willReturn(errorAttributesMap);

        mockMvc.perform(get("/error")
                        .requestAttr("javax.servlet.error.status_code", 404)
                        .requestAttr("javax.servlet.error.request_uri", "/error"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/404"));
    }

    @Test
    void testInternalServerError() throws Exception {
        Map<String, Object> errorAttributesMap = new HashMap<>();
        errorAttributesMap.put("status", 500);
        errorAttributesMap.put("error", "Internal Server Error");
        errorAttributesMap.put("message", "Internal Server Error");
        errorAttributesMap.put("timestamp", System.currentTimeMillis());
        errorAttributesMap.put("path", "/error");

        given(errorAttributes.getErrorAttributes(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any())).willReturn(errorAttributesMap);

        mockMvc.perform(get("/error")
                        .requestAttr("javax.servlet.error.status_code", 500)
                        .requestAttr("javax.servlet.error.request_uri", "/error"))
                .andExpect(status().isInternalServerError())
                .andExpect(view().name("error/500"));
    }
}