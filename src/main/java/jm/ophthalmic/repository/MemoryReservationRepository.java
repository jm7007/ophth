package jm.ophthalmic.repository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jm.ophthalmic.domain.Reservation;

public class MemoryReservationRepository implements ReservationRepository {

    private Map<Long, Reservation> store = new HashMap<>();
    private static long sequence = 0l;

    @Override
    public Reservation save(Reservation reservation) {
        reservation.setRs_id(++sequence);
        store.put(reservation.getRs_id(), reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findbyId(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Reservation> findbyContact(String contact) {
        return store.values().stream()
                .filter(rs -> contact.equals(rs.getRs_contact()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findbyUserId(Long userId) {
        return store.values().stream()
                .filter(rs -> userId == rs.getRs_user_id())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reservation> findbyTime(LocalDateTime datetime) {
        return store.values().stream()
                .filter(rs -> datetime == rs.getRs_datetime())
                .findAny();
    }

    @Override
    public List<Reservation> findbyMonth(LocalDateTime datetime) {

        return store.values().stream()
                .filter(rs -> ((datetime.getYear() == rs.getRs_datetime().getYear())
                        && (datetime.getMonth() == rs.getRs_datetime().getMonth())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Long delete(Long rs_id) {
        store.remove(rs_id);
        return rs_id;
    }

    @Override
    public Optional<Reservation> modify(Reservation originalRs, Reservation newRs) {
        for (Field field : originalRs.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(newRs) == null) {
                    field.set(newRs, field.get(originalRs));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        store.put(newRs.getRs_id(), newRs);
        return Optional.ofNullable(newRs);
    }
    //더미파일 생성자
    public MemoryReservationRepository() {
        Reservation r1 = new Reservation();
        r1.setRs_name("김민수");
        r1.setRs_contact("01012341234");
        r1.setRs_datetime(LocalDateTime.of(2023, 4, 1, 9, 0));
        r1.setRs_info("시력검사");
        r1.setRs_user_id(3L);
        r1.setRs_ifuser((byte)1);

        Reservation r2 = new Reservation();
        r2.setRs_name("박영희");
        r2.setRs_contact("01012341235");
        r2.setRs_datetime(LocalDateTime.of(2023, 4, 1, 10, 0));
        r2.setRs_info("안과 검사");
        r2.setRs_ifuser((byte) 0);

        Reservation r3 = new Reservation();
        r3.setRs_name("이철수");
        r3.setRs_contact("01053487652");
        r3.setRs_datetime(LocalDateTime.of(2023, 4, 1, 11, 0));
        r3.setRs_info("안과 검사");
        r3.setRs_user_id(5L);
        r3.setRs_ifuser((byte) 1);

        Reservation r4 = new Reservation();
        r4.setRs_name("홍길동");
        r4.setRs_contact("01012341237");
        r4.setRs_datetime(LocalDateTime.of(2023, 4, 1, 12, 0));
        r4.setRs_info("시력검사");
        r4.setRs_user_id(6L);
        r4.setRs_ifuser((byte) 1);

        Reservation r5 = new Reservation();
        r5.setRs_name("김태희");
        r5.setRs_contact("01012341238");
        r5.setRs_datetime(LocalDateTime.of(2023, 4, 1, 14, 0));
        r5.setRs_info("안과 검사");
        r5.setRs_ifuser((byte) 0);

        Reservation r6 = new Reservation();
        r6.setRs_name("이나영");
        r6.setRs_contact("01012341239");
        r6.setRs_datetime(LocalDateTime.of(2023, 4, 1, 15, 0));
        r6.setRs_info("시력검사");
        r6.setRs_user_id(2L);
        r6.setRs_ifuser((byte) 0);

        Reservation r7 = new Reservation();
        r7.setRs_name("이영호");
        r7.setRs_contact("01012341240");
        r7.setRs_datetime(LocalDateTime.of(2023, 4, 2, 9, 0));
        r7.setRs_info("안과 검사");
        r7.setRs_user_id(3L);
        r7.setRs_ifuser((byte) 1);

        Reservation r8 = new Reservation();
        r8.setRs_name("김민수");
        r8.setRs_contact("01012341234");
        r8.setRs_datetime(LocalDateTime.of(2023, 4, 2, 10, 0));
        r8.setRs_info("시력검사");
        r8.setRs_user_id(3L);
        r8.setRs_ifuser((byte) 1);

        Reservation r9 = new Reservation();
        r9.setRs_name("이영희");
        r9.setRs_contact("010-1234-5678");
        r9.setRs_datetime(LocalDateTime.of(2023, 4, 8, 9, 0, 0));
        r9.setRs_info("발열, 기침");
        r9.setRs_user_id(3L);
        r9.setRs_ifuser((byte) 0);

        Reservation r10 = new Reservation();
        r10.setRs_name("손흥민");
        r10.setRs_contact("010-5555-5555");
        r10.setRs_datetime(LocalDateTime.of(2023, 4, 11, 10, 0, 0));
        r10.setRs_info("인후통");
        r10.setRs_user_id(4L);
        r10.setRs_ifuser((byte) 1);

        Reservation r11 = new Reservation();
        r11.setRs_name("김민수");
        r11.setRs_contact("010-2222-2222");
        r11.setRs_datetime(LocalDateTime.of(2023, 4, 14, 11, 0, 0));
        r11.setRs_info("복통");
        r11.setRs_user_id(2L);
        r11.setRs_ifuser((byte) 0);

        Reservation r12 = new Reservation();
        r12.setRs_name("박영호");
        r12.setRs_contact("010-4444-4444");
        r12.setRs_datetime(LocalDateTime.of(2023, 4, 15, 12, 0, 0));
        r12.setRs_info("두통");
        r12.setRs_user_id(1L);
        r12.setRs_ifuser((byte) 1);

        Reservation r13 = new Reservation();
        r13.setRs_name("최영철");
        r13.setRs_contact("010-3333-3333");
        r13.setRs_datetime(LocalDateTime.of(2023, 4, 18, 14, 0, 0));
        r13.setRs_info("가래");
        r13.setRs_user_id(3L);
        r13.setRs_ifuser((byte) 0);

        Reservation r14 = new Reservation();
        r14.setRs_name("홍길순");
        r14.setRs_contact("010-9999-9999");
        r14.setRs_datetime(LocalDateTime.of(2023, 4, 20, 15, 0, 0));
        r14.setRs_info("눈물나요");
        r14.setRs_user_id(2L);
        r14.setRs_ifuser((byte) 1);

        Reservation r15 = new Reservation();
        r15.setRs_name("김진아");
        r15.setRs_contact("010-7777-7777");
        r15.setRs_datetime(LocalDateTime.of(2023, 4, 21, 9, 0, 0));
        r15.setRs_info("몸살");
        r15.setRs_user_id(8L);
        r15.setRs_ifuser((byte) 1);

        save(r1);
        save(r2);
        save(r3);
        save(r4);
        save(r5);
        save(r6);
        save(r7);
        save(r8);
        save(r9);
        save(r10);
        save(r11);
        save(r12);
        save(r13);
        save(r14);
        save(r15);
    }
}
