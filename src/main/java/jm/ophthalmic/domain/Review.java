package jm.ophthalmic.domain;

import java.io.File;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Review {
    
    private Long review_id;
    private String review_title;
    private File review_image;
    private String review_abstract;
    private String review_content;
    private Byte review_score;
    private LocalDate review_create_date;
    private LocalDate review_update_date;
    private Long user_id;
}
