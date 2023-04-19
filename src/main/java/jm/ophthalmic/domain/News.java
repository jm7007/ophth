package jm.ophthalmic.domain;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class News {
    private Long id;
    private String title;
    private String content;
    private String imageName;
    private String imagePath;
    private LocalDateTime createDatetime;
    private LocalDateTime lastUpdateDatetime;
    private Long adminId;
}
