package com.gpsiu.gamepc.controller;

import com.gpsiu.gamepc.domain.Member;
import com.gpsiu.gamepc.domain.Pc;
import com.gpsiu.gamepc.service.PcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/pc")
@RequiredArgsConstructor
@RestController
public class PcController {
    private final PcService pcService;

    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<List<Pc>> findAll(){
        return ResponseEntity.ok().body(pcService.findAll());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<Pc> findPcById(@PathVariable Long id){
        return pcService.findPcById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<Pc> savePc(@RequestBody Pc pc){
        return ResponseEntity.ok().body(pcService.savePc(pc));
    }
}
