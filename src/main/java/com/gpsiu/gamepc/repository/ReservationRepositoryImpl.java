package com.gpsiu.gamepc.repository;

import com.gpsiu.gamepc.domain.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Reservation save(Reservation reservation) {
        em.persist(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        try{
            return Optional.ofNullable(em.find(Reservation.class, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    // TODO: Like문 등의 구문으로 다시 작성해야할 것으로 판단됨
    @Override
    public List<Reservation> findByDate(Date date) {
        return em.createQuery("select r from Reservation r where r.createTime = :date", Reservation.class)
                .setParameter("date", date)
                .getResultList();
    }
}