package com.gpsiu.gamepc.repository;

import com.gpsiu.gamepc.domain.Pc;

import java.util.List;
import java.util.Optional;

public interface PcRepository {
    Pc save(Pc pc);
    List<Pc> findAll();
    Optional<Pc> findById(Long id);
    Optional<Pc> findByName(String name);
}