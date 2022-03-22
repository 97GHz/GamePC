package com.gpsiu.gamepc.repository;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.domain.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    List<Reservation> findByDate(Date date);
}