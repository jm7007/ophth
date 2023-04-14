package jm.ophthalmic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import jm.ophthalmic.repository.InquiryRepository;
import jm.ophthalmic.repository.JpaUserRepository;
import jm.ophthalmic.repository.MemoryInquiryRepository;
import jm.ophthalmic.repository.MemoryReservationRepository;
import jm.ophthalmic.repository.ReservationRepository;
import jm.ophthalmic.repository.UserRepository;
import jm.ophthalmic.service.InquiryService;
import jm.ophthalmic.service.ReservationService;
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
    public ReservationService reservationService(){
        return new ReservationService(reservationRepository());
    }
    @Bean 
    UserRepository userRepository(){
        return new JpaUserRepository(em);
    }
    @Bean
    ReservationRepository reservationRepository(){
        return new MemoryReservationRepository();
    }
    @Bean
    public InquiryService inquiryService(){
        return new InquiryService(inquiryRepository());
    }
    @Bean 
    InquiryRepository inquiryRepository(){
        return new MemoryInquiryRepository();
    }
}
