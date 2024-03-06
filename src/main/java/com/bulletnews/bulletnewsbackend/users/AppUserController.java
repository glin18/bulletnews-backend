package com.bulletnews.bulletnewsbackend.users;

import com.bulletnews.bulletnewsbackend.users.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("")
    public List<AppUser> findAll() {
        return appUserService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<AppUser> createOrUpdateUser(@RequestBody CreateUserRequest createUserRequest) {
        AppUser createdUser = appUserService.createOrUpdateUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
