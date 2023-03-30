package jm.ophthalmic.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import jm.ophthalmic.domain.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findbyId(Long id);
    Optional<User> findbyAccount(String account);
    Optional<User> findbyEmail(String email);
    List<User> findAll();
    Optional<User> deletebyId(Long id);
    //수정할 자료는 Map<컬럼명,데이터>로 받는다
    Optional<User> modifyUser(Long id, Map<String,Object> updates);
}
