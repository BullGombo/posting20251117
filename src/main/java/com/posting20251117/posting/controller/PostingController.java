package com.posting20251117.posting.controller;

import com.posting20251117.posting.dto.*;
import com.posting20251117.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @PostMapping("/postings")
    public ResponseEntity<CreatePostingResponse> createPosting(@RequestBody CreatePostingRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(postingService.save(request));
    }

    @GetMapping("/postings")
    public ResponseEntity<List<GetPostingsResponse>> getPostings(){
        return ResponseEntity.status(HttpStatus.OK).body(postingService.getAll());
    }

    @GetMapping("/postings/{postingId}")
    public ResponseEntity<GetPostingResponse> getPosting(@PathVariable Long postingId){
        return ResponseEntity.status(HttpStatus.OK).body(postingService.getOne(postingId));
    }

    @PutMapping("/postings/{postingId}")
    public ResponseEntity<UpdatePostingResponse> updatePosting(@PathVariable Long postingId, @RequestBody UpdatePostingRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(postingService.update(postingId, request));
    }

    @DeleteMapping("/postings/{postingId}")
    public ResponseEntity<Void> deletePosting(@PathVariable Long postingId){
        postingService.delete(postingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
