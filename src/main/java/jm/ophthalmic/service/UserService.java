package jm.ophthalmic.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import jm.ophthalmic.controller.form.LoginForm;
import jm.ophthalmic.controller.form.UserForm;
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
        return userRepository.delete(id);
    }
    public Optional<User> modifyUser(Long id, User newUser){
        return userRepository.modifyUser(id, newUser);
    }

    //로그인
    public Optional<User> login(LoginForm loginForm)throws Exception{

        try{
        return Optional.ofNullable(userRepository.findbyAccount(loginForm.getAccount()))
        .filter(user -> user.get().getPassword().equals(loginForm.getPassword()))
        .orElse(null);
        }catch(NoSuchElementException e){
            System.out.println("로그인 아이디가 존재하지 않습니다.");
            return null;
        }
    }
    public User convertForm(UserForm userForm){
        User user = new User();
        user.setAccount(userForm.getAccount());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setName(userForm.getName());
        user.setContact(userForm.getContact());
        user.setGender(userForm.getGender());
        user.setEmail(userForm.getEmail());
        user.setBirth(userForm.getBirth());
        return user;
    }
    public UserForm convertForm(User user){
        UserForm userForm = new UserForm();
        userForm.setInt_id(user.getAccount());
        userForm.setInt_name(user.getName());
        userForm.setInt_contact(user.getContact());
        userForm.setInt_yy(String.valueOf(user.getBirth().getYear()));
        userForm.setInt_mm(String.valueOf(user.getBirth().getMonth().getValue()));
        userForm.setInt_dd(String.valueOf(user.getBirth().getDayOfMonth()));
        userForm.setInt_email(user.getEmail());
        userForm.setInt_gender(user.getGender());
        return userForm;
    }
}
