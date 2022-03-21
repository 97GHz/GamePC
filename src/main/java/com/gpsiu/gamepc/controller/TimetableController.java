package com.gpsiu.gamepc.controller;

import com.gpsiu.gamepc.domain.Timetable;
import com.gpsiu.gamepc.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/timetable")
@RequiredArgsConstructor
@RestController
public class TimetableController {
    private final TimetableService timetableService;

    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<List<Timetable>> findAll(){
        return ResponseEntity.ok().body(timetableService.findAll());
    }

    //TODO: Timetable의 date가 deserialize가 안되는 경우가 있음. 추후 DTO로 변경하여 해결할 것
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<Timetable> savePc(@RequestBody Timetable timetable){
        return ResponseEntity.ok().body(timetableService.save(timetable));
    }
}
