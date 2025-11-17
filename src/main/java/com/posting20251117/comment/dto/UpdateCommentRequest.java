package com.posting20251117.comment.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    private final String content;

    public UpdateCommentRequest(String content) {
        this.content = content;
    }
}
