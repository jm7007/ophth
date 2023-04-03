package jm.ophthalmic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import jm.ophthalmic.repository.JpaUserRepository;
import jm.ophthalmic.repository.UserRepository;
import jm.ophthalmic.service.UserService;

@Configuration
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }
    @Bean 
    UserRepository userRepository(){
        return new JpaUserRepository(em);
    }

    
}
