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


    public Inquiry() {}

    public Inquiry(Long id, String category, String title, String authorId,boolean answered, LocalDateTime date) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = "";
        this.authorId = authorId;
        this.answered = answered;
        this.date = date;
    }


}
