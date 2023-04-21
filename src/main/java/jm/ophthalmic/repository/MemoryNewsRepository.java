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
        //줄바꿈을 <br>태그로 바꿔줌
        news.setContent(news.getContent().replace("\n","<br>"));
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
        News n2 = new News();
        n2.setId(2l);
        n2.setTitle("월드컵 4강 진출 기념 특별행사");
        n2.setContent(
            "안녕하세요 JM안과 입니다."
            +"<br>대한민국의 월드컵 4강 진출이라는 기적적인 성과를 기념하여"
            +"<br>게시물 작성일로부터 2002년 8월 31일까지"
            +"<br>라식, 라섹, 렌즈삽입술에 대한 시술비용을 80% ~ 90% 할인 이벤트를 시행합니다."
            +"<br>많은 관심 부탁드립니다."
            +"<br>감사합니다."
            );
        n2.setImageName("2_월드컵.png");
        n2.setImagePath("/upload/news/2_월드컵.png");
        n2.setCreateDatetime(LocalDateTime.of(2002,7,5,8,0));
        n2.setAdminId(2l);
        save(n2);
        News n3 = new News();
        n3.setId(3l);
        n3.setTitle("크리스마스 휴무 안내");
        n3.setContent(
            "안녕하세요 JM안과 입니다."
            +"<br>크리스마스 당일 12월 25일이 휴무일로 지정되었습니다."
            +"<br>별도 공지가 없을 시 매년 12월 25일은 휴무입니다."
            +"<br>감사합니다."
        );
        n3.setImageName("standardimg.png");
        n3.setImagePath("images/standardimg.png");
        n3.setCreateDatetime(LocalDateTime.of(2002,12,1,12,0));
        n3.setAdminId(2l);
        save(n3);
        News n4 = new News();
        n4.setId(4l);
        n4.setTitle("1주년 기념 라식체험단 이벤트");
        n4.setContent(
            "안녕하세요 JM안과 입니다."
            +"<br>안과 개원 1주년을 맞이하여 라식체험단 이벤트를 진행합니다."
            +"<br>체험단 신청자중 100분에 한하여 시술비 없이 스마트 라식 시술을 받으실 수 있으며"
            +"<br>시술 후 3개월 내에 리뷰 게시글을 작성해주셔야 합니다."
            +"<br>상세 정보는 아래와 같습니다.<br><br><br><br>"
            +"신청 기간 : 3월 1일 12시 ~ 3월 12일 12시"
            +"<br>신청 방법 : 유선전화로 상담"
        );
        n4.setImageName("4_1st.jpg");
        n4.setImagePath("/upload/news/4_1st.jpg");
        n4.setCreateDatetime(LocalDateTime.of(2003,2,11,11,38));
        n4.setAdminId(2l);
        save(n4);
        News n5 = new News();
        n5.setId(5l);
        n5.setTitle("6월 15일 임시 휴무 안내");
        n5.setContent("안녕하세요 JM안과입니다.<br>안과 사정으로 6월 15일이 임시 휴무일로 지정되었습니다.<br>이용에 불편이 없으시길 바랍니다.");
        n5.setImageName("standardimg.png");
        n5.setImagePath("images/standardimg.png");
        n5.setCreateDatetime(LocalDateTime.of(2007,6,1,11,0));
        n5.setAdminId(1l);
        save(n5);
        News n6 = new News();
        n6.setId(6l);
        n6.setTitle("리모델링 및 휴무 안내");
        n6.setContent(
            "안녕하세요 JM안과입니다."
            +"<br>안과 리모델링 일정으로 휴무일을 안내드립니다."
            +"<br>휴무 기간: 2월 12일 ~ 2월 15일"
            +"<br>개원일: 2월 16일"
        );
        n6.setImageName("standardimg.png");
        n6.setImagePath("images/standardimg.png");
        n6.setCreateDatetime(LocalDateTime.of(2010,1,17,12,0));
        n6.setAdminId(2l);
        save(n6);
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
