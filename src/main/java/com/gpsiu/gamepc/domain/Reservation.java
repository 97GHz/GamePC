package com.gpsiu.gamepc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    @Column(nullable = false)
    private Member member;

    @Column(nullable = false)
    private Pc pc;

    @Column(nullable = false)
    private Timetable timetable;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;// = LocalDateTime.now();
}