package jm.ophthalmic.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jm.ophthalmic.domain.User;

public class MemoryUserRepository implements UserRepository{

    private Map<Long, User> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findbyAccount(String account) {
        return store.values().stream().filter(user -> user.getAccount().equals(account)).findAny();
    }

    @Override
    public Optional<User> findbyEmail(String email) {
        return store.values().stream().filter(user -> user.getEmail().equals(email)).findAny();
    }

    @Override
    public List<User> findAll() {
        return store.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> delete(Long id) {
        //초기 레파지토리 인터페이스 선언에서 반환타입을 잘못 생각하여 의미없는 프로세스가 생성됨
        Optional<User> user = findbyId(id);
        store.remove(id);
        return user;
    }

    @Override
    public Optional<User> modifyUser(Long id, User newUser) {
        newUser.setId(id);
        store.put(id, newUser);
        return Optional.ofNullable(newUser);
    }
    //더미파일 생성자
    public MemoryUserRepository(){
        User u1 = new User();
        u1.setAccount("admin");
        u1.setPassword("1234");
        u1.setEmail("jongminleg@gmail.com");
        u1.setName("이종민");
        u1.setContact("01033934738");
        u1.setBirth(LocalDate.of(1994,4,4));
        u1.setGender("M");
        u1.setAdmin((byte)1);
        save(u1);

        User u2 = new User();
        u2.setAccount("jung");
        u2.setPassword("2345");
        u2.setEmail("jung@naver.com");
        u2.setName("조정래");
        u2.setContact("01077671637");
        u2.setBirth(LocalDate.of(1993,7,7));
        u2.setGender("M");
        u2.setAdmin((byte)1);
        save(u2);

        User u3 = new User();
        u3.setAccount("minsujjang");
        u3.setPassword("dsafhk62326");
        u3.setEmail("minmin@gogo.co.kr");
        u3.setName("김민수");
        u3.setContact("01012341234");
        u3.setBirth(LocalDate.of(1971,3,21));
        u3.setGender("M");
        u3.setAdmin((byte)0);
        save(u3);

        User u4 = new User();
        u4.setAccount("shm1223");
        u4.setPassword("1223shm");
        u4.setEmail("shm1223@naver.com");
        u4.setName("손흥민");
        u4.setContact("01055555555");
        u4.setBirth(LocalDate.of(1992,7,8));
        u4.setGender("M");
        u4.setAdmin((byte)0);
        save(u4);

        User u5 = new User();
        u5.setAccount("chch5020");
        u5.setPassword("5020");
        u5.setEmail("chch9090@gmail.com");
        u5.setName("이철수");
        u5.setContact("01053487652");
        u5.setBirth(LocalDate.of(1961,5,7));
        u5.setGender("M");
        u5.setAdmin((byte)0);
        save(u5);

        User u6 = new User();
        u6.setAccount("hkd123");
        u6.setPassword("hong123123");
        u6.setEmail("hong55@hanmail.net");
        u6.setName("홍길동");
        u6.setContact("01012341237");
        u6.setBirth(LocalDate.of(1925,1,1));
        u6.setGender("N");
        u6.setAdmin((byte)0);
        save(u6);

        User u7 = new User();
        u7.setAccount("yungho77");
        u7.setPassword("skajfsla");
        u7.setEmail("younghogood@naver.com");
        u7.setName("이영호");
        u7.setContact("01012341240");
        u7.setBirth(LocalDate.of(1987,3,23));
        u7.setGender("M");
        u7.setAdmin((byte)0);
        save(u7);

        User u8 = new User();
        u8.setAccount("jina77");
        u8.setPassword("kimjina77");
        u8.setEmail("kimjina@naver.com");
        u8.setName("김진아");
        u8.setContact("01077777777");
        u8.setBirth(LocalDate.of(1977,7,7));
        u8.setGender("W");
        u8.setAdmin((byte)0);
        save(u8);
    }
}
