package com.gpsiu.gamepc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIMETABLE_ID")
    private Long id;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    Date startTime;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    Date endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    DateType dateType;

    @OneToMany(mappedBy = "timetable")
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        if (reservation.getTimetable() != this) {
            reservation.setTimetable(this);
        }
    }
}