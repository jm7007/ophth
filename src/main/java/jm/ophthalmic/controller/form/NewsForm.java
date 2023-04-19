package jm.ophthalmic.controller.form;

import org.springframework.web.multipart.MultipartFile;

import jm.ophthalmic.domain.News;
import lombok.Data;

@Data
public class NewsForm {
    
    private String news_title;
    private String news_content;
    private MultipartFile news_image;

    public News transferToNews(){
        News news = new News();
        news.setTitle(news_title);
        news.setContent(news_content);
        return news;
    }
    
}
