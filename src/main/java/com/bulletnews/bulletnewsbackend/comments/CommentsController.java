package com.bulletnews.bulletnewsbackend.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentsController {

    private final CommentsService commentsService;

    @GetMapping("")
    public List<Comments> findAll(){
        return commentsService.findAll();
    }
}
