package com.bulletnews.bulletnewsbackend.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

}
