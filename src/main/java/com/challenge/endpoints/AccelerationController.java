package com.challenge.endpoints;

import com.challenge.dto.AccelerationDTO;
import com.challenge.entity.Acceleration;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.mappers.AccelerationMapper;
import com.challenge.service.impl.AccelerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/acceleration")
public class AccelerationController {
    @Autowired
    private AccelerationService accelerationService;
    @Autowired
    private AccelerationMapper accelerationMapper;

    @GetMapping
    public ResponseEntity<List<AccelerationDTO>> findAllAccelerationByCompanyId(@RequestParam(name = "companyId", required = false, defaultValue = "0") Long id) {
        List<Acceleration> accelerations = accelerationService.findByCompanyId(id);
        if (accelerations.isEmpty()) throw new ResourceNotFoundException();
        return ResponseEntity.ok().body(accelerationMapper.map(accelerations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccelerationDTO> findAccelerationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accelerationMapper.map(accelerationService.findById(id).orElseThrow(
                ResourceNotFoundException::new
        )));
    }
}
