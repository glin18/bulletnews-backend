package com.bulletnews.bulletnewsbackend.users;

import com.bulletnews.bulletnewsbackend.exceptions.custom.ResourceNotFoundException;
import com.bulletnews.bulletnewsbackend.news.News;
import com.bulletnews.bulletnewsbackend.users.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findByUuid(String uuid) {
        return appUserRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("App User with uuid of " + uuid + " not found"));
    }

    public AppUser createOrUpdateUser(CreateUserRequest createUserRequest) {
        String uuid = createUserRequest.getUuid();
        Optional<AppUser> existingUser = appUserRepository.findByUuid(uuid);
        return existingUser.map(this::updateUserLoginTime).orElseGet(() -> createUser(createUserRequest));
    }

    public void deleteUser(Long id) {
        if (!appUserRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        appUserRepository.deleteById(id);
    }

    public AppUser likeNews(News news, String uuid){
        AppUser user = findByUuid(uuid);
        if (!user.getLikedNews().contains(news)) {
            user.getLikedNews().add(news);
        } else {
            user.getLikedNews().remove(news);
        }
        return appUserRepository.save(user);
    }

    private AppUser updateUserLoginTime(AppUser user) {
        user.setLastLoginTime(Instant.now());
        return appUserRepository.save(user);
    }

    private AppUser createUser(CreateUserRequest createUserRequest) {
        AppUser user = AppUser.builder()
                .uuid(createUserRequest.getUuid())
                .email(createUserRequest.getEmail())
                .lastLoginTime(Instant.now())
                .build();
        return appUserRepository.save(user);
    }

}
