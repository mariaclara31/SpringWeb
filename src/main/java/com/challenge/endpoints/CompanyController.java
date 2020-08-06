package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanyByAccelerationIdOrUserId(
            @RequestParam(name = "accelerationId", required = false) Optional<Long> accelerationId,
            @RequestParam(name = "userId", required = false) Optional<Long> userId) {
        return accelerationId.map(aLong -> ResponseEntity.ok(this.companyService.findByAccelerationId(aLong))).orElseGet(() -> userId.map(aLong -> ResponseEntity.ok(this.companyService.findByUserId(aLong))).orElseGet(() -> ResponseEntity.ok(Collections.EMPTY_LIST)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable("id") Long id) {
        Optional<Company> companyOptional = this.companyService.findById(id);
        return companyOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}