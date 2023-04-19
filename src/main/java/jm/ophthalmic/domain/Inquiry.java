package jm.ophthalmic.domain;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Inquiry{
    private Long inquiry_id;
    private String inquiry_title;
    private String inquiry_content;
    private MultipartFile inquiry_image;
    private String inquiry_imageName;
    private String inquiry_imagePath;
    private LocalDateTime inquiry_create_datetime;
    private String inquiry_name;
    private String inquiry_contact;
    private String inquiry_password;
    private String inquiry_answer;
    private Byte inquiry_ifdone;
    private Long user_id;
}
