package jm.ophthalmic.domain;

import java.io.File;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Inquiry{
    private Long inquiry_id;
    private String inquiry_title;
    private String inquiry_content;
    private File inquiry_image;
    private LocalDateTime inquiry_create_datetime;
    private String inquiry_name;
    private String inquiry_contact;
    private String inquiry_password;
    private Long user_id;
}
