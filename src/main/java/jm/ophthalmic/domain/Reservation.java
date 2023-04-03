package jm.ophthalmic.domain;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString


public class Reservation {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rs_id;
    private String rs_name;
    private String rs_contact;
    private LocalDateTime rs_datetime;
    private String rs_info;
    private Long rs_user_id;
    private Byte rs_ifuser;

}
