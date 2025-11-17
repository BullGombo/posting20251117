package com.posting20251117.comment.repository;

import com.posting20251117.comment.entity.Comment;
import com.posting20251117.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPosting(Posting posting);
}
