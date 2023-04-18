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
        return store.values().stream().filter(user -> user.getAccount() == account).findAny();
    }

    @Override
    public Optional<User> findbyEmail(String email) {
        return store.values().stream().filter(user -> user.getEmail() == email).findAny();
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
        u2.setContact("01077777777");
        u2.setBirth(LocalDate.of(1993,7,7));
        u2.setGender("M");
        u2.setAdmin((byte)0);
        save(u2);
    }
}
