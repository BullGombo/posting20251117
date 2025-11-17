package com.posting20251117.comment.dto;

import lombok.Getter;

@Getter
public class SearchCommentResponse {

    private final Long id;
    private final String content;

    public SearchCommentResponse(Long id, String content) {
        this.id = id;
        this.content = content;
    }
    //private final String postingTitle;

}
