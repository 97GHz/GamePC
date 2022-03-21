package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.domain.Pc;

import java.util.List;
import java.util.Optional;

public interface PcService {
    Pc savePc(Pc pc);
    List<Pc> findAll();
    Optional<Pc> findPcById(Long id);
    Optional<Pc> findPcByName(String name);
}