package com.gpsiu.gamepc.repository;

import com.gpsiu.gamepc.domain.DateType;
import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.domain.Timetable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class TimetableRepositoryImpl implements TimetableRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Timetable save(Timetable timetable) {
        em.persist(timetable);
        return timetable;
    }

    @Override
    public List<Timetable> findAll() {
        return em.createQuery("select t from Timetable t", Timetable.class)
                .getResultList();
    }

    @Override
    public List<Timetable> findAllByDateType(DateType dateType) {
        return em.createQuery("select t from Timetable t where dateType = :dateType", Timetable.class)
                .setParameter("dateType", dateType)
                .getResultList();
    }

    @Override
    public Optional<Timetable> findById(Long id) {
        try {
            return Optional.ofNullable(em.find(Timetable.class, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
