package com.debuggeandoideas.bump.services;

import com.debuggeandoideas.bump.dtos.PageRequest;
import com.debuggeandoideas.bump.dtos.PageResponse;
import com.debuggeandoideas.bump.dtos.PostRequest;


public interface PageService {

    PageResponse create(PageRequest page);
    PageResponse readByTitle(String title);
    PageResponse update(PageRequest page, String title);
    void delete(String title);

    PageResponse createPost(PostRequest post, String title);
    void deletePost(Long idPost, String title);
}
