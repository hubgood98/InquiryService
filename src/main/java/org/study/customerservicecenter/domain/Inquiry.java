package org.study.customerservicecenter.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class Inquiry {
    private Long id;
    private String category;
    private String title;
    private String content;
    private String authorId;
    private boolean answered;
    private LocalDateTime date;
    private String reply;//답글내용
    private String replyAuthor;//답글 작성자


    public Inquiry() {}

}
