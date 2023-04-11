package jm.ophthalmic.repository;

import java.util.List;
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
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> delete(Long id) {
        Optional<User> user = findbyId(id);
        em.remove(user);
        return user;
    }

    @Override
    public Optional<User> modifyUser(Long id, User newUser) {
        String sql = "UPDATE User u SET u.password = :password"
                + ", u.name = :name, u.contact = :contact, u.email = :email"
                + ", u.gender = :gender, u.birth = :birth WHERE u.id= :id";
        Query query = em.createQuery(sql);
        query.setParameter("password", newUser.getPassword());
        query.setParameter("name", newUser.getName());
        query.setParameter("contact", newUser.getContact());
        query.setParameter("email", newUser.getEmail());
        query.setParameter("gender", newUser.getGender());
        query.setParameter("birth", newUser.getBirth());
        query.setParameter("id", id);
        int result = query.executeUpdate();
        if (result != 1) {
            throw new RuntimeException("modify user is failed with id = " + id);
        }
        User updatedUser = em.find(User.class,id);
        System.out.println(updatedUser);
        return Optional.ofNullable(updatedUser);
    }

}
