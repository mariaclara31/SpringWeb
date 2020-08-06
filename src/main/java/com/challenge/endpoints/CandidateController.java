package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findAllCandidateByCompanyId(@RequestParam(name = "companyId") Long id) {
        return ResponseEntity.ok(candidateMapper.map(candidateService.findByCompanyId(id)));
    }

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findCandidateByUserIdAndCompanyIdAndAccelerationId(
            @PathVariable("userId") Long userId,
            @PathVariable("companyId") Long companyId,
            @PathVariable("accelerationId") Long accelerationId) {
        Optional<Candidate> candidateOptional = candidateService.findById(userId, companyId, accelerationId);
        return ResponseEntity.ok(candidateMapper.map(candidateOptional.orElse(new Candidate())));
    }
}