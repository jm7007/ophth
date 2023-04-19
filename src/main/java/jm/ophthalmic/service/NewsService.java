package jm.ophthalmic.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jm.ophthalmic.domain.News;
import jm.ophthalmic.repository.NewsRepository;

public class NewsService {
    
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    
    public Long write(News news){
        return newsRepository.save(news).getId();
    }

    public Optional<News> findOnebyId(Long id){
        return newsRepository.findById(id);
    }

    public List<News> findAllNews(){
        return newsRepository.findAll();
    }
    public News modifyNews(Long id, News news){
        if(!newsRepository.findById(id).isPresent()){
            System.out.println("뉴스 수정이 중단되었습니다.");
            return null;
        }
        return newsRepository.modify(id, news);
    }
    public News saveImageDetail(News news, MultipartFile image){
            //원본파일의 이름
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            //파일 이름에 추가할 inquiry의 id를 저장소 크기+1로 미리 조회
            long id = (newsRepository.storageSize()+1);
            Path path = Paths.get("src/main/resources/static/upload/news/"+id+"_"+fileName);
            try{ 
                //copy 메서드의 replace 옵션으로 중복저장을 허용해주고 복사
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                //복사가 정상 완료되면 디테일 정보를 객체에 세팅
                news.setImageName(fileName);
                news.setImagePath("/upload/news/"+id+"_"+fileName);
            }catch(IOException e){
                System.out.println("news 이미지 저장에 실패했습니다.");
            }
            return news;
    }
    public News saveStandardImage(News news){
        news.setImageName("standardimg.png");
        news.setImagePath("images/standardimg.png");
        return news;
    }
}
