package org.study.customerservicecenter.Service;

import org.springframework.stereotype.Service;
import org.study.customerservicecenter.domain.Inquiry;
import org.study.customerservicecenter.repository.InquiryRepository;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public List<Inquiry> getAllInquiries() {
        return inquiryRepository.findAll();
    }

    //카테고리찾기
    public List<Inquiry> getInquiriesByCategory(String category) {
        return inquiryRepository.findByCategory(category);
    }

    //작성자 id찾기
    public List<Inquiry> getInquiriesByAuthor(String authorId) {
        return inquiryRepository.findByAuthor(authorId);
    }

    //작성글 고유번호로 찾기
    public Inquiry getInquiryById(Long id) {
        return inquiryRepository.findById(id);
    }

    //문의 등록
    public void saveInquiry(Inquiry inquiry) {
        inquiry.setDate(LocalDateTime.now());
        inquiryRepository.save(inquiry);
    }
    //문의내용 삭제
    public void removeInquiry(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id);
        if(inquiry != null) {
            inquiryRepository.remove(inquiry);
        }
    }

    public void updateInquiry(Long id, String title, String category, String content) {
        Inquiry inquiry = inquiryRepository.findById(id);
        if (inquiry != null) {
            inquiry.setTitle(title);
            inquiry.setCategory(category);
            inquiry.setContent(content);
            inquiryRepository.update(inquiry);
        }
    }

    public void addReplyToInquiry(Long id, String replyContent, String replyAuthor) {
        Inquiry inquiry = inquiryRepository.findById(id);
        if (inquiry == null) {
            throw new RuntimeException("Inquiry not found");
        }
        inquiry.setReply(replyContent);
        inquiry.setReplyAuthor(replyAuthor);
        inquiryRepository.save(inquiry);
    }


}
