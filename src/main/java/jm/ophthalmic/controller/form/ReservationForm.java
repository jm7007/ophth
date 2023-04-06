package jm.ophthalmic.controller.form;

import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.Data;

@Data
public class ReservationForm {
    private Long rs_id;
    private String rs_name;
    private String rs_contact;
    private String rs_datetime;
    private String rs_info;

    public LocalDateTime getRs_datetime(){
        int[] values = Arrays.stream(rs_datetime.split(",")).mapToInt(Integer::parseInt).toArray();
        return LocalDateTime.of(values[0],values[1],values[2],values[3],0);
    }
}
