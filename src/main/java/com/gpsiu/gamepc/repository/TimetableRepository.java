package com.gpsiu.gamepc.repository;

import com.gpsiu.gamepc.domain.DateType;
import com.gpsiu.gamepc.domain.Timetable;

import java.util.List;
import java.util.Optional;

public interface TimetableRepository {
    Timetable save(Timetable timetable);
    List<Timetable> findAll();
    List<Timetable> findAllByDateType(DateType dateType);
    Optional<Timetable> findById(Long id);
}