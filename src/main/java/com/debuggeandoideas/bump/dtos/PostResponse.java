package com.debuggeandoideas.bump.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostResponse {

    private LocalDateTime dateCreation;
    private String content;
    private String img;
}
