package com.bulletnews.bulletnewsbackend.users;

import com.bulletnews.bulletnewsbackend.exceptions.custom.ResourceNotFoundException;
import com.bulletnews.bulletnewsbackend.users.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser createUser(CreateUserRequest createUserRequest) {
        AppUser user = AppUser.builder()
                .uuid(createUserRequest.getUuid())
                .email(createUserRequest.getEmail())
                .lastLoginTime(Instant.now())
                .build();
        return appUserRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!appUserRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        appUserRepository.deleteById(id);
    }
}
