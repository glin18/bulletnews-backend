package com.bulletnews.bulletnewsbackend.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    public List<CommentResponse> findAll(){
        return commentService.findAll();
    }

    @PostMapping("/news/{id}")
    public CommentResponse createComment(@RequestBody CreateCommentRequest request, @PathVariable Long id,
                                 @AuthenticationPrincipal Jwt jwt){
        return commentService.createComment(request, id, jwt.getSubject());
    }

}
