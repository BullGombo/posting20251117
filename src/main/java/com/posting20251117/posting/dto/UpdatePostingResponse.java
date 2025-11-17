package com.posting20251117.posting.dto;

import lombok.Getter;

@Getter
public class UpdatePostingResponse {
    private final Long id;

    public UpdatePostingResponse(Long id) {
        this.id = id;
    }
}
