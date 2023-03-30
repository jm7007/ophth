package jm.ophthalmic.domain;

import java.sql.Blob;
import java.util.Date;

import lombok.Data;

@Data
public class News {
    private int id;
    private String title;
    private String content;
    private String writer;
    private Date date;
    private Date last_update;
    private Blob image;

}
