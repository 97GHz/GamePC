package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.domain.Reservation;
import com.gpsiu.gamepc.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findByDate(Date date) {
        return reservationRepository.findByDate(date);
    }

    @Override
    public Optional<Reservation> deleteById(Long id) {
        return reservationRepository.deleteById(id);
    }
}
