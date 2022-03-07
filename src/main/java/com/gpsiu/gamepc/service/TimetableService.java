package com.gpsiu.gamepc.service;

import com.gpsiu.gamepc.domain.DateType;
import com.gpsiu.gamepc.domain.Timetable;

import java.util.List;
import java.util.Optional;

public interface TimetableService {
    Timetable save(Timetable timetable);
    List<Timetable> findAll();
    List<Timetable> findAllByDateType(DateType dateType);
    Optional<Timetable> findTimetableById(Long id);
}
