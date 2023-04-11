package jm.ophthalmic.domain;

import java.io.File;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Review {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
