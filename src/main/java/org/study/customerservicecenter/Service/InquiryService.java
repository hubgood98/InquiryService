package org.study.customerservicecenter.Service;


import org.springframework.stereotype.Service;
import org.study.customerservicecenter.domain.Inquiry;

import java.time.LocalDate;
import java.util.List;

@Service
public class InquiryService {

    public List<Inquiry> getAllInquiries() {

        //고객 문의 목록을 가져오는 로직
        return List.of(
                //임시데이터
                new Inquiry(1L, "일반", "문의 제목 1", "user1", "답변 완료", LocalDate.now()),
                new Inquiry(2L, "제품", "문의 제목 2", "user2", "답변 대기", LocalDate.now()),
                new Inquiry(3L, "배송", "문의 제목 3", "테스트", "답변 완료", LocalDate.now())
        );
    }
}
