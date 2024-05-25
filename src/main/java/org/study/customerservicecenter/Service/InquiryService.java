package org.study.customerservicecenter.Service;


import org.springframework.stereotype.Service;
import org.study.customerservicecenter.domain.Inquiry;
import org.study.customerservicecenter.repository.InquiryRepository;

import java.util.List;


@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public List<Inquiry> getAllInquiries()
    {
        return inquiryRepository.findAll();
    }

    public void saveInquiry(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

}
