package com.posting20251117.posting.dto;

import lombok.Getter;

@Getter
public class GetPostingResponse {
    private final Long id;
    private final String title;
    private final String content;

    public GetPostingResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
