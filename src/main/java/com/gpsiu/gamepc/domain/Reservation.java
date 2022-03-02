package com.gpsiu.gamepc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PC_ID")
    private Pc pc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIMETABLE_ID")
    private Timetable timetable;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;// = LocalDateTime.now();

    public void setMember(Member member) {
        this.member = member;

        if(!member.getReservations().contains(this)) {
            member.getReservations().add(this);
        }
    }

    public void setPc(Pc pc) {
        this.pc = pc;

        if(!pc.getReservations().contains(this)) {
            pc.getReservations().add(this);
        }
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;

        if(!timetable.getReservations().contains(this)) {
            timetable.getReservations().add(this);
        }
    }
}