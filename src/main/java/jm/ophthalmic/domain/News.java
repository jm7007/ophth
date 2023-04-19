package jm.ophthalmic.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class News {
    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDate date;
    private LocalDate last_update;

}
