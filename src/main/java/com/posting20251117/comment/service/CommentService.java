package com.posting20251117.comment.service;

import com.posting20251117.comment.dto.*;
import com.posting20251117.comment.entity.Comment;
import com.posting20251117.comment.repository.CommentRepository;
import com.posting20251117.posting.entity.Posting;
import com.posting20251117.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    // 3-Layer-Architecture에 의거하여, 하위 레이어를 포함시킴
    private final CommentRepository commentRepository;

    //
    private final PostingRepository postingRepository;

    @Transactional
    public CreateCommentResponse save(Long postingId, CreateCommentRequest request) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalStateException("postingId: " + postingId + " not found")
        );

        Comment comment = new Comment(request.getContent(), posting);
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> getAll(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalStateException("postingId: " + postingId + " not found")
        );

        List<Comment> comments = commentRepository.findByPosting(posting);
        return comments.stream()
                .map(comment -> new GetCommentResponse(
                    comment.getId(),
                    comment.getContent()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public GetCommentResponse getOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("commentId: " + commentId + " not found")
        );
        return new GetCommentResponse(comment.getId(), comment.getContent());
    }

    @Transactional
    public UpdateCommentResponse update(Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("commentId: " + commentId + " not found")
        );
        comment.update(request.getContent());
        return new UpdateCommentResponse(comment.getId());
    }

    @Transactional
    public void delete(Long commentId) {
        boolean existence = commentRepository.existsById(commentId);
        if (!existence) {
            throw new IllegalStateException("commentId: " + commentId + " not found");
        }
        commentRepository.deleteById(commentId);

    }
}
