package com.debuggeandoideas.bump.services;

import com.debuggeandoideas.bump.dtos.PageResponse;
import com.debuggeandoideas.bump.entities.PageEntity;
import com.debuggeandoideas.bump.entities.PostEntity;
import com.debuggeandoideas.bump.repositories.PageRepository;
import com.debuggeandoideas.bump.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PageServiceTest {

    @MockBean //similar to @Autowired for test
    private PageRepository pageRepository;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PageServiceImpl target;


    @Test //Happy path
    void readByTitle_ShouldReturnPageResponse_WhenTitleExist() {
        String title = "Debuggeando ideas";

        PostEntity postEntity = new PostEntity();

        postEntity.setImg("http://img");
        postEntity.setContent("Some content");
        postEntity.setDateCreation(LocalDateTime.MIN);

        PageEntity pageEntity = new PageEntity();
        pageEntity.setTitle(title);
        pageEntity.setDateCreation(LocalDateTime.MIN);
        pageEntity.setPosts(List.of(postEntity));

        given(pageRepository.findByTitle(title))
                .willReturn(Optional.of(pageEntity));

        PageResponse result = target.readByTitle(title);

       assertThat(result).isNotNull();
       assertThat(result.getTitle()).isEqualTo("Debuggeando ideas");
       assertThat(result.getDateCreation()).isEqualTo(LocalDateTime.MIN);
       assertThat(result.getPosts()).hasSize(1);

    }

    @Test //Unhappy path
    void readByTitle_ShouldReturnException_WhenTitleNotExist() {
        String title = "Invalid title";

        given(pageRepository.findByTitle(title))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> target.readByTitle(title))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title not found");

        verify(pageRepository).findByTitle(title);
    }
}
