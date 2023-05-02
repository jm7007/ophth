package jm.ophthalmic.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Reservation {
    private Long rs_id;
    private String rs_name;
    private String rs_contact;
    private LocalDateTime rs_datetime;
    private String rs_info;
    private Long rs_user_id;
    private Byte rs_ifuser;
    private Integer rs_ifvisit;
}
