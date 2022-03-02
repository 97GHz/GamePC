package com.gpsiu.gamepc.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Pc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PC_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean enable;

    @OneToMany(mappedBy = "pc")
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        if (reservation.getPc() != this) {
            reservation.setPc(this);
        }
    }
}
