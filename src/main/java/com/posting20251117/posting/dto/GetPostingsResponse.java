package com.posting20251117.posting.dto;

import lombok.Getter;

@Getter
public class GetPostingResponse {

    private final Long id;
    private final String title;

    public GetPostingResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
