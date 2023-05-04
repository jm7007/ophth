package jm.ophthalmic.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jm.ophthalmic.domain.Inquiry;

public class MemoryInquiryRepository implements InquiryRepository{
    
    private Map<Long, Inquiry> store = new HashMap<>();
    private static long sequence = 0l;
    @Override
    public Inquiry save(Inquiry inquiry) {
        inquiry.setInquiry_id(++sequence);
        store.put(inquiry.getInquiry_id(), inquiry);
        return inquiry;
        
    }
    @Override
    public Optional<Inquiry> findbyId(Long review_id) {
        return Optional.ofNullable(store.get(review_id));
    }
    @Override
    public List<Inquiry> findbyUserId(Long user_id) {
        return store.values().stream()
                .filter(inquiry -> user_id == inquiry.getUser_id())
                .collect(Collectors.toList());
    }
    @Override
    public List<Inquiry> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Long storageSize() {
        return sequence;
    }
    @Override
    public Inquiry saveAnswer(Inquiry answer){
        Inquiry inquiry = store.get(answer.getInquiry_id());
        inquiry.setInquiry_answer(answer.getInquiry_answer());
        inquiry.setInquiry_ifdone(answer.getInquiry_ifdone());
        inquiry.setInquiry_adminUserId(answer.getInquiry_adminUserId());
        return inquiry;
    }
    //더미파일
    public MemoryInquiryRepository(){
        Inquiry iq1 = new Inquiry();
        iq1.setInquiry_title("라식을 고민중입니다.");
        iq1.setInquiry_content("안녕하세요\n최근에 눈이 나빠져서 라식을 고민하고있는데,\n비용이 얼마나 들지, 라식을 할 수 있을지 궁금합니다.");
        iq1.setInquiry_create_datetime(LocalDateTime.of(2023,05,01,13,24));
        iq1.setInquiry_imageName("noimage.png");
        iq1.setInquiry_imagePath("/images/noimage.png");
        iq1.setInquiry_name("감자바");
        iq1.setInquiry_contact("01039394747");
        iq1.setInquiry_ifdone((byte)1);
        iq1.setInquiry_answer("안녕하세요 JM안과입니다.\n질문주신 내용은 잘 확인하였습니다.\n고객님의 각막의 두께, 시력등을 측정하여 라식이나 라섹, 렌즈삽입이 가능한지 판단할 수 있습니다.\n병원방문을 예약주시어 방문해주신다면 최선을 다해 검사에 응하도록 하겠습니다.\n감사합니다.");
        iq1.setUser_id(9l);
        iq1.setInquiry_adminUserId(1l);
        save(iq1);

        Inquiry iq2 = new Inquiry();
        iq2.setInquiry_title("다래끼인가요?");
        iq2.setInquiry_content("눈이 너무 간지러워서 자꾸 긁게되는데 안쪽에 이상한게 난거같아요\n다래끼인가요?");
        iq2.setInquiry_create_datetime(LocalDateTime.of(2023,05,05,2,14));
        iq2.setInquiry_imageName("다래끼인가.png");
        iq2.setInquiry_imagePath("/upload/inquiry/다래끼인가.png");
        iq2.setInquiry_name("차용증");
        iq2.setInquiry_contact("01022921426");
        save(iq2);
    }
}
