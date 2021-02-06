package com.musinsa.watcher.web;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.watcher.domain.post.Post;
import com.musinsa.watcher.domain.post.master.PostRepository;
import com.musinsa.watcher.web.dto.post.PostRequestDto;
import com.musinsa.watcher.web.dto.post.PostResponseDto;
import com.musinsa.watcher.web.dto.post.PostUpdateDto;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PostControllerTest {

  @LocalServerPort
  private int port;

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private PostRepository postRepository;

  @Before
  public void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .build();
  }

  @After
  public void tearDown() {
    postRepository.deleteAll();
  }

  @Test
  @DisplayName("게시글을 저장한다.")
  public void 게시글을저장한다() throws Exception {
    //given
    String uri = "http://localhost:" + port + "/api/v1/post";
    String title = "문의 사항입니다.";
    String content = "문의 내용입니다.";
    String author = "작성자";
    PostRequestDto postRequestDto = PostRequestDto.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
    //when
    mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(postRequestDto)))
        .andExpect(status().isOk());
    //then
    Post post = postRepository.findAll().get(0);
    assertEquals(post.getTitle(), title);
    assertEquals(post.getContent(), content);
    assertEquals(post.getAuthor(), author);
  }

  @Test
  @DisplayName("게시글을 조회한다.")
  public void 게시글을조회한다() throws Exception {
    //given
    String uri = "http://localhost:" + port + "/api/v1/post";
    String title = "문의 사항입니다.";
    String content = "문의 내용입니다.";
    String author = "작성자";
    Post post = Post.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
    long id = postRepository.save(post).getId();
    //when
    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
        .param("id", id + ""))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    //then
    String returnContent = result.getResponse().getContentAsString();
    log.info(returnContent);
    ObjectMapper mapper = new ObjectMapper();
    PostResponseDto returnPost = mapper.readValue(returnContent, PostResponseDto.class);
    assertEquals(returnPost.getAuthor(), author);
    assertEquals(returnPost.getContent(), content);
    assertEquals(returnPost.getTitle(), title);
  }


  @Test
  @DisplayName("게시글을 수정한다.")
  public void 게시글을수정한다() throws Exception {
    //given
    String uri = "http://localhost:" + port + "/api/v1/post";
    String title = "문의 사항입니다.";
    String content = "문의 내용입니다.";
    String author = "작성자";
    Post post = Post.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
    String updateContent = "변경된 내용입니다.";
    PostUpdateDto postUpdateDto = PostUpdateDto.builder()
        .title(title)
        .content(updateContent)
        .build();
    long id = postRepository.save(post).getId();
    //when
    MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(postUpdateDto))
        .param("id", id + ""))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    //then
    Post updatePost = postRepository.findById(id).get();
    assertEquals(updatePost.getAuthor(), author);
    assertEquals(updatePost.getContent(), updateContent);
    assertEquals(updatePost.getTitle(), title);
  }

  @Test
  @DisplayName("게시글을 삭제한다.")
  public void 게시글을삭제한다() throws Exception {
    //given
    String uri = "http://localhost:" + port + "/api/v1/post";
    String title = "문의 사항입니다.";
    String content = "문의 내용입니다.";
    String author = "작성자";
    Post post = Post.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
    long id = postRepository.save(post).getId();
    //when
    MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri)
        .param("id", id + ""))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
    //then
    assertEquals(postRepository.findById(id), Optional.empty());
  }
}