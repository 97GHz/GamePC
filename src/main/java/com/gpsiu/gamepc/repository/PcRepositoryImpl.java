package com.gpsiu.gamepc.repository;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.domain.Pc;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class PcRepositoryImpl implements PcRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Pc save(Pc pc) {
        em.persist(pc);
        return pc;
    }

    @Override
    public List<Pc> findAll() {
        return em.createQuery("select p from Pc p", Pc.class)
                .getResultList();
    }

    @Override
    public Optional<Pc> findById(Long id) {
        try {
            return Optional.ofNullable(em.find(Pc.class, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Pc> findByName(String name) {
        TypedQuery<Pc> query = em.createQuery("select p from Pc p where name = :name", Pc.class);
        try {
            return Optional.ofNullable(
                    query.setParameter("name", name)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
