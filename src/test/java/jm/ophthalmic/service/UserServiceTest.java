package jm.ophthalmic.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jm.ophthalmic.domain.User;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional
public class UserServiceTest {
    
    @Autowired UserService userService;
    

    @Test
    public void 회원가입_Id로조회() {
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("01012345678");
        user1.setEmail("test01@gmail.com");
        user1.setGender("M");
        user1.setBirth(LocalDate.of(1924,4,7));
        user1.setAdmin((byte)0);

        Long userId = userService.join(user1);
        //가입 확인
        assertThat(userService.findOnebyId(userId)).isEqualTo(Optional.of(user1));
        //아이디 중복 에러 검사
        User user2 = new User();
        user2.setAccount("test01");
        user2.setPassword("12341234");
        user2.setName("이원굉");
        user2.setContact("010-1234-5678");
        user2.setEmail("test50@gmail.com");

        IllegalStateException e = assertThrows(IllegalStateException.class,() -> userService.join(user2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 account 입니다.");
        //이메일 중복 에러 검사
        User user3 = new User();
        user3.setAccount("test03");
        user3.setPassword("12341234");
        user3.setName("이원굉");
        user3.setContact("010-1234-5678");
        user3.setEmail("test01@gmail.com");

        IllegalStateException e2 = assertThrows(IllegalStateException.class,() -> userService.join(user3));
        assertThat(e2.getMessage()).isEqualTo("이미 존재하는 email 입니다.");
    }
    @Test
    public void 이메일조회(){
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");
        user1.setEmail("test01@gmail.com");

        Long userId = userService.join(user1);
        
        Optional<User> result = userService.findOnebyId(userId);

        assertThat(result.get().getEmail()).isEqualTo(user1.getEmail());
    }
    @Test
    public void 모두조회(){
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");
        User user2 = new User();
        user2.setAccount("test02");
        user2.setPassword("12341234");
        user2.setName("이원굉");
        user2.setContact("010-1234-5678");
        User user3 = new User();
        user3.setAccount("test03");
        user3.setPassword("12341234");
        user3.setName("이원굉");
        user3.setContact("010-1234-5678");

        int originalSize = userService.findUsers().size();

        Long userId1 = userService.join(user1);
        Long userId2 = userService.join(user2);
        Long userId3 = userService.join(user3);

        List<User> result = userService.findUsers();

        int afterSize = userService.findUsers().size();

        //전 자료보다 사이즈가 3개 늘었는지 확인
        assertThat(originalSize).isEqualTo(afterSize-3);
        
        //새로 세이브한 자료가 순서대로 잘 출력되었는지 확인
        assertThat(result.get(result.size()-1)).isEqualTo(userService.findOnebyId(userId3).get());
        assertThat(result.get(result.size()-2)).isEqualTo(userService.findOnebyId(userId2).get());
        assertThat(result.get(result.size()-3)).isEqualTo(userService.findOnebyId(userId1).get());

    }
    @Test
    public void 계정삭제(){
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");

        Long userId = userService.join(user1);
        //삭제 전 저장되어있는지 확인
        assertThat(userService.findOnebyId(userId).get()).isEqualTo(user1);

        userService.deleteUser(userId);

        assertThat(userService.findOnebyId(userId)).isEmpty();
    }
    @Test
    public void 정보수정(){
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");

        Long userId = userService.join(user1);
        //수정할 요소
        Map<String,Object> updates = new HashMap<>();
        updates.put("password","12345678");
        updates.put("name","수정된이름");
        
        Optional<User> result = userService.modifyUser(userId, updates);

        assertThat(result.get().getPassword()).isEqualTo("12345678");
        assertThat(result.get().getName()).isEqualTo("수정된이름");
        
    }
}
