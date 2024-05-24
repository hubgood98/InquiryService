package org.study.customerservicecenter.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class Inquiry {
    private Long id;
    private String category;
    private String title;
    private String author;
    private String status;
    private LocalDate date;

    // 생성자
    public Inquiry(Long id, String category, String title, String author, String status, LocalDate date) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.author = author;
        this.status = status;
        this.date = date;
    }
}
