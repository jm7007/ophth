package jm.ophthalmic.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import jm.ophthalmic.controller.form.LoginForm;
import jm.ophthalmic.domain.User;
import jm.ophthalmic.repository.UserRepository;

@Transactional
public class UserService {
    

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user){

        //아이디 중복 검증
        validateDuplicateAccount(user);
        //이메일 중복 검증
        validateDuplicateEmail(user);
        //DB 저장
        //생성된 id 조회
        return userRepository.save(user).getId();
    }
    private void validateDuplicateAccount(User user){
        userRepository.findbyAccount(user.getAccount()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 account 입니다.");
        });
    }
    private void validateDuplicateEmail(User user){
        userRepository.findbyEmail(user.getEmail()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 email 입니다.");
        });
    }
    public List<User> findUsers(){
        return userRepository.findAll();
    }
    public Optional<User> findOnebyId(Long id){
        return userRepository.findbyId(id);
    }
    public Optional<User> findOnebyAccount(String account){
        return userRepository.findbyAccount(account);
    }
    public Optional<User> findOnebyEmail(String email){
        return userRepository.findbyEmail(email);
    }
    public Optional<User> deleteUser(Long id){
        return userRepository.deletebyId(id);
    }
    public Optional<User> modifyUser(Long id, Map<String,Object> updateClauses){
        return userRepository.modifyUser(id, updateClauses);
    }

    //로그인
    public Optional<User> login(LoginForm loginForm)throws Exception{

        return Optional.ofNullable(userRepository.findbyAccount(loginForm.getAccount()))
        .filter(user -> user.get().getPassword().equals(loginForm.getPassword()))
        .orElse(null);
    }
}
