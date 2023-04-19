package jm.ophthalmic.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import jm.ophthalmic.controller.form.NewsForm;
import jm.ophthalmic.domain.News;
import jm.ophthalmic.service.NewsService;

@Controller
public class NewsController {

    private final NewsService newsService;
    
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @GetMapping("news")
    public String news(Model model){
        model.addAttribute("allnews", newsService.findAllNews());
        return "news/news";
    }
    @GetMapping("news/write")
    public String newsWrite(){
        return "news/news_write";
    }
    @PostMapping("news/write")
    public String newsWritePost(NewsForm newsForm, @SessionAttribute("id") Long id){
        //DTO 내부 메서드로 뉴스객체로 전환
        News news = newsForm.transferToNews();
        //관리자 아이디 세팅
        news.setAdminId(id);
        //생성시간 세팅
        news.setCreateDatetime(LocalDateTime.now());
        //이미지가 있을경우 세팅, 없을경우 기본이미지 매핑
        if(newsForm.getNews_image().getOriginalFilename() != ""){
            news = newsService.saveImageDetail(news, newsForm.getNews_image());
        }else{
            news = newsService.saveStandardImage(news);
        }
        newsService.write(news);
        return "redirect:/";
    }
    @GetMapping("news/view/{id}")
    public String newsView(@PathVariable("id") Long id, Model model){
        News news = newsService.findOnebyId(id).get();
        model.addAttribute("news", news);
        return "news/news_view";
    }
}
