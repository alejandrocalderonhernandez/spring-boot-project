package com.debuggeandoideas.bump.services;

import com.debuggeandoideas.bump.dtos.PageRequest;
import com.debuggeandoideas.bump.dtos.PageResponse;
import com.debuggeandoideas.bump.dtos.PostRequest;
import com.debuggeandoideas.bump.dtos.PostResponse;
import com.debuggeandoideas.bump.entities.PageEntity;
import com.debuggeandoideas.bump.entities.PostEntity;
import com.debuggeandoideas.bump.exceptions.TitleNotValidException;
import com.debuggeandoideas.bump.repositories.PageRepository;
import com.debuggeandoideas.bump.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final UserRepository userRepository;

    @Override
    public PageResponse create(PageRequest page) {

        this.validTitle(page.getTitle());
        final var entity = new PageEntity(); //create object(entity) to persist in Database
        BeanUtils.copyProperties(page, entity);//Copy properties from argument(page) in entity


        final var user = this.userRepository.findById(page.getUserId())  //Search user corresponding to page
                .orElseThrow();

        entity.setDateCreation(LocalDateTime.now()); //set date now
        entity.setUser(user); //create relationship between users and page
        entity.setPosts(new ArrayList<>()); //set empty list

        var pageCreated = this.pageRepository.save(entity); //upsert id exist id update else insert

        final var response = new PageResponse();//create dto for response

        BeanUtils.copyProperties(pageCreated, response);//copy properties from entity(pageCreated) in reponse
        return response;
    }

    @Override
    public PageResponse readByTitle(String title) {
        final var entityResponse = this.pageRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Title not found")); //Find by title and handle errors

        final var response = new PageResponse(); // create response object

        BeanUtils.copyProperties(entityResponse, response); //copy properties from entity

        //Get post responses from post entity
        final List<PostResponse> postResponses = entityResponse.getPosts()
                .stream()// convert to stream
                .map(postE ->    //transform postEntity to postResponse
                     PostResponse
                            .builder()
                            .img(postE.getImg())
                            .content(postE.getContent())
                            .dateCreation(postE.getDateCreation())
                            .build()
                )
                .toList();  // convert to list

        response.setPosts(postResponses); // set list of post
        return response;
    }

    @Override
    public PageResponse update(PageRequest page, String title) {
        this.validTitle(page.getTitle());
        final var entityFromDB = this.pageRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Title not found")); //Find by title and handle errors

        entityFromDB.setTitle(page.getTitle()); //update fields from param page

        var pageCreated = this.pageRepository.save(entityFromDB); //upsert id exist id update else insert

        final var response = new PageResponse();//create dto for response

        BeanUtils.copyProperties(pageCreated, response);//copy properties from entity(pageCreated) in reponse
        return response;
    }

    @Override
    public void delete(String title) {
        //final var entityFromDB = this.pageRepository.findByTitle(title)
        //        .orElseThrow(() -> new IllegalArgumentException("Title not found"));
        //this.pageRepository.delete(entityFromDB);
        //
        //this.pageRepository.deleteById(1L);

        if (this.pageRepository.existsByTitle(title)) {
            log.info("Delinting page");
            this.pageRepository.deleteByTitle(title);
        } else {
            log.error("Error to delete");
            throw new IllegalArgumentException("Cant delete because id not exist");
        }
    }

    @Override
    public PageResponse createPost(PostRequest post, String title) {
        final var pageToUpdate = this.pageRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Title not found")); //Find by title and handle errors

        final var postEntity = new PostEntity(); // create entity to insert

        BeanUtils.copyProperties(post, postEntity); // copy fields from dto to entity
        postEntity.setDateCreation(LocalDateTime.now());

        pageToUpdate.addPost(postEntity);

        final var responseEntity = this.pageRepository.save(pageToUpdate); // update

        final var response = new PageResponse(); // create response

        BeanUtils.copyProperties(responseEntity, response); // copy fields from objet updated

        final List<PostResponse> postResponses = responseEntity.getPosts() //map posts from db -> dto(response)
                .stream()// convert to stream
                .map(postE ->    //transform postEntity to postResponse
                        PostResponse
                                .builder()
                                .img(postE.getImg())
                                .content(postE.getContent())
                                .dateCreation(postE.getDateCreation())
                                .build()
                )
                .toList();  // convert to list

        response.setPosts(postResponses);
        return response;
    }

    @Override
    public void deletePost(Long idPost, String title) {
        final var pageToUpdate = this.pageRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Title not found")); //Find by title and handle errors

        final var postToDelete = pageToUpdate.getPosts()
                .stream()
                .filter(post -> post.getId().equals(idPost))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("post id not found"));

        pageToUpdate.removePost(postToDelete);

        this.pageRepository.save(pageToUpdate); // update
    }

    private void validTitle(String title) {
        if (title.contains("5678") || title.contains("12345")) {
            throw new TitleNotValidException("Title cant contain bad words");
        }
    }
}
