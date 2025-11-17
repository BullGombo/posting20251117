package com.posting20251117.comment.repository;

import com.posting20251117.comment.entity.Comment;
import com.posting20251117.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//    // 전체 조회
//    @Query("SELECT c FROM Comment c")
//    List<Comment> findAllComments();
//
//    // 조건 조회 - 입력한 숫자의 id보다 낮은 id들 조회
//    @Query("SELECT c FROM Comment c WHERE c.id < :num")
//    List<Comment> findAllCommentsByNum(@Param("num") Long num);
//
//    // 조건 조회 - 입력한 게시글 제목의 댓글 조회
//    @Query("SELECT c FROM Comment c WHERE :postingTitle IS NULL OR c.posting.title = ':PostingTitle'")
//    List<Comment> findByPostingTitle(@Param("PostingTitle") String postingTitle);
//
//    // 조건 조회 - 사실은 PostingRepository에 위치해야함 !!!!!!!!!!!!!!
//    @Query("SELECT p FROM Posting p WHERE p.id = :postingId")
//    Optional<Comment> findByPostingId(@Param("postingId") Long postingId);

    List<Comment> findByPosting(Posting posting);
}
