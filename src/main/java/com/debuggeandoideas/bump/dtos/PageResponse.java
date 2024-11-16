package com.debuggeandoideas.bump.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PageResponse {

    private String title;
    private LocalDateTime dateCreation;
    private List<PostResponse> posts;
}
