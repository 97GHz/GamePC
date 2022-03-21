package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.domain.DateType;
import com.gpsiu.gamepc.domain.Timetable;
import com.gpsiu.gamepc.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class TimetableServiceImpl implements TimetableService{
    private final TimetableRepository timetableRepository;

    @Override
    public Timetable save(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    @Override
    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    @Override
    public List<Timetable> findAllByDateType(DateType dateType) {
        return timetableRepository.findAllByDateType(dateType);
    }

    @Override
    public Optional<Timetable> findTimetableById(Long id) {
        return timetableRepository.findById(id);
    }
}
