package com.posting20251117.posting.service;

import com.posting20251117.posting.dto.*;
import com.posting20251117.posting.entity.Posting;
import com.posting20251117.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final PostingRepository postingRepository;

    @Transactional
    public CreatePostingResponse save(CreatePostingRequest request) {
        Posting posting = new Posting(request.getTitle(), request.getContent());
        Posting savedPosting = postingRepository.save(posting);
        return new CreatePostingResponse(
                savedPosting.getId(),
                savedPosting.getTitle(),
                savedPosting.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<GetPostingsResponse> getAll() {
        List<Posting> postings = postingRepository.findAll();
        List<GetPostingsResponse> dtos = new ArrayList<>();
        for (Posting posting : postings) {
            GetPostingsResponse dto = new GetPostingsResponse(
                    posting.getId(),
                    posting.getTitle()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetPostingResponse getOne(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(  // 글로벌익셉션핸들러로 업그레이드할 부분
                () -> new IllegalStateException("Posting with id " + postingId + " not found")
        );
        return new GetPostingResponse(
                posting.getId(),
                posting.getTitle(),
                posting.getContent()
        );
    }

    @Transactional
    public UpdatePostingResponse update(Long postingId, UpdatePostingRequest request) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(  // 글로벌익셉션핸들러로 업그레이드할 부분
                () -> new IllegalStateException("Posting with id " + postingId + " not found")
        );
        posting.update(request.getTitle(), request.getContent());
        return new UpdatePostingResponse(posting.getId());
    }

    @Transactional
    public void delete(Long postingId) {
        boolean existence = postingRepository.existsById(postingId);
        if (!existence) {
            throw new IllegalStateException("Posting with id " + postingId + " not found");
        }
        postingRepository.deleteById(postingId);
    }
}
