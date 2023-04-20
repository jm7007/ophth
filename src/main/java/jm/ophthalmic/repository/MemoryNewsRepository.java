package jm.ophthalmic.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jm.ophthalmic.domain.News;

public class MemoryNewsRepository implements NewsRepository{

    private Map<Long, News> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public News save(News news) {
        news.setId(++sequence);
        store.put(news.getId(),news);
        return news;
    }

    @Override
    public Optional<News> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<News> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public News modify(Long id, News news) {
        news.setId(id);
        store.put(id,news);
        return news;
    }

    @Override
    public Long delete(Long id) {
        store.remove(id);
        return id;
    }
    @Override
    public Long storageSize() {
        return sequence;
    }

    //더미파일 생성자
    public MemoryNewsRepository(){
        News n1 = new News();
        n1.setId(1l);
        n1.setTitle("JM안과가 개원하였습니다.");
        n1.setContent(
            "여러분들의 많은 성원에 힘입어 2002년 4월 4일 JM안과를 개원할 수 있게 되었습니다."
            +"<br>검사부터 시술까지 완벽한 처치로 고객님의 소중한 눈을 지켜드리겠습니다."
            +"<br>많은 응원과 방문 부탁드립니다."
            );
        n1.setImageName("1_first.png");
        n1.setImagePath("/upload/news/1_first.png");
        n1.setCreateDatetime(LocalDateTime.of(2002,4,4,8,38));
        n1.setAdminId(1l);
        save(n1);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);
        // News n = new News();
        // n.setId();
        // n.setTitle();
        // n.setContent();
        // n.setImageName();
        // n.setImagePath();
        // n.setCreateDatetime();
        // n.setAdminId();
        // save(n);

    }
    
}
