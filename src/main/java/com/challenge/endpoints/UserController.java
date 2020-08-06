package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long userId) {
        Optional<User> userOptional = this.userService.findById(userId);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> findByAccelerationNameOrCompanyId(@RequestParam(name = "accelerationName", required = false) Optional<String> accelerationName,
                                                                        @RequestParam(name = "companyId", required = false) Optional<Long> companyId) {
        if (accelerationName.isPresent()) {
            return ResponseEntity.ok(this.userService.findByAccelerationName(accelerationName.get()));
        } else if (companyId.isPresent()) {
            return ResponseEntity.ok(this.userService.findByCompanyId(companyId.get()));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}