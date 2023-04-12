package jm.ophthalmic.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Comment {
    
    private Long comment_id;
    private String comment_content;
    private LocalDateTime comment_time;
    private Long review_id;
    private Long user_id;
}
