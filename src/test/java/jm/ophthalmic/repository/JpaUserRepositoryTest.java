package jm.ophthalmic.repository;


import static org.assertj.core.api.Assertions.*;

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

@SpringBootTest
@Transactional
public class JpaUserRepositoryTest{

    @Autowired UserRepository userRepository;
    

    @Test
    public void 회원가입_Id로조회_account로조회() {
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");
        user1.setEmail("test01@gmail.com");
        user1.setGender("M");
        user1.setBirth(LocalDate.now());
        user1.setAdmin((byte)0);

        User target = userRepository.save(user1);

        Optional<User> result = userRepository.findbyAccount(target.getAccount());

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getContact()).isEqualTo(target.getContact());
        assertThat(Optional.ofNullable(target)).isEqualTo(userRepository.findbyId(result.get().getId()));
        
    }
    @Test
    public void 이메일조회(){
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");
        user1.setEmail("test01@gmail.com");

        User target = userRepository.save(user1);
        
        Optional<User> result = userRepository.findbyEmail(user1.getEmail());

        assertThat(Optional.ofNullable(target)).isEqualTo(result);
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

        int originalSize = userRepository.findAll().size();

        User target1 = userRepository.save(user1);
        User target2 = userRepository.save(user2);
        User target3 = userRepository.save(user3);

        List<User> result = userRepository.findAll();

        int afterSize = userRepository.findAll().size();

        //전 자료보다 사이즈가 3개 늘었는지 확인
        assertThat(originalSize).isEqualTo(afterSize-3);
        
        //새로 세이브한 자료가 순서대로 잘 출력되었는지 확인
        assertThat(result.get(result.size()-1)).isEqualTo(target3);
        assertThat(result.get(result.size()-2)).isEqualTo(target2);
        assertThat(result.get(result.size()-3)).isEqualTo(target1);

    }
    @Test
    public void 계정삭제(){
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");

        User target = userRepository.save(user1);
        //삭제 전 저장되어있는지 확인
        assertThat(userRepository.findbyId(target.getId())).isEqualTo(Optional.of(target));

        userRepository.deletebyId(target.getId());

        assertThat(userRepository.findbyId(target.getId())).isEmpty();
    }
    @Test
    public void 정보수정(){
        User user1 = new User();
        user1.setAccount("test01");
        user1.setPassword("12341234");
        user1.setName("이원굉");
        user1.setContact("010-1234-5678");

        User target = userRepository.save(user1);
        //수정할 요소
        Map<String,Object> updates = new HashMap<>();
        updates.put("password","12345678");
        updates.put("name","수정된이름");
        
        Optional<User> result = userRepository.modifyUser(target.getId(), updates);

        assertThat(result.get().getPassword()).isEqualTo("12345678");
        assertThat(result.get().getName()).isEqualTo("수정된이름");
    }
}
