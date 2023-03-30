package jm.ophthalmic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jm.ophthalmic.domain.User;

@Transactional
public class JpaUserRepository implements UserRepository {
    
    private final EntityManager em;

    
    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findbyId(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findbyAccount(String account) {
        return em.createQuery("select u from User u where u.account = :account", User.class)
                .setParameter("account", account)
                .getResultList().stream().findAny();
    }
    @Override
    public Optional<User> findbyEmail(String email) {
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList().stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u",User.class)
            .getResultList();
    }

    @Override
    public Optional<User> deletebyId(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> modifyUser(Long id, Map<String,Object> updates) {
        String sql = "UPDATE User u SET ";
        List<String> updateClauses = new ArrayList<>();
        for(String column : updates.keySet()){
            updateClauses.add("u."+column + " = :" + column);
        }
        sql += String.join(", ",updateClauses);
        sql += " WHERE u.id = :id";

        Query query = em.createQuery(sql);

        for(String column : updates.keySet()){
            query.setParameter(column, updates.get(column));
        }
        query.setParameter("id",id);
        int result = query.executeUpdate();
        if(result != 1){
            throw new RuntimeException("modify user is failed with id = "+id);
        }
        //1차 캐시에 들어있는 값을 참조하지 못하게 하기 위해 clear()메소드를 사용하여 실제 값을 불러오게 한다.
        em.clear();
        User updatedUser = em.find(User.class,id);
        System.out.println(updatedUser);
        return Optional.ofNullable(updatedUser);
    }

}
