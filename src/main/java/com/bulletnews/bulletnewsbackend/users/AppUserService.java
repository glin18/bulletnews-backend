package com.bulletnews.bulletnewsbackend.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }
}
