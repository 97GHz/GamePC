package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.domain.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation save(Reservation reservation);
    List<Reservation> findAll();
    Optional<Reservation> findById(Long id);
    List<Reservation> findByDate(Date date);
    Optional<Reservation> deleteById(Long id);
}