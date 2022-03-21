package com.gpsiu.gamepc.service;


import com.gpsiu.gamepc.domain.Pc;
import com.gpsiu.gamepc.repository.PcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class PcServiceImpl implements PcService{
    private final PcRepository pcRepository;

    @Override
    public Pc savePc(Pc pc) {
        return pcRepository.save(pc);
    }

    @Override
    public List<Pc> findAll() {
        return pcRepository.findAll();
    }

    @Override
    public Optional<Pc> findPcById(Long id) {
        return pcRepository.findById(id);
    }

    @Override
    public Optional<Pc> findPcByName(String name) {
        return pcRepository.findByName(name);
    }
}
