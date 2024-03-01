package com.project.quizapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.quizapp.dto.PostQuestionDto;
import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(QuestionRestController.class)
class QuestionRestControllerTest {
    @MockBean
    private QuestionService questionService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String explanation = "test explanation";

    @Test
    @DisplayName("When getAllQuestions is called return all Questions added and status 200")
    public void getAllQuestions() throws Exception {
        // given
        List<QuestionDto> questionDtoList = new ArrayList<>();
        questionDtoList.add(new QuestionDto(
                1L,
                "Question 1",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.EASY,
                Category.JAVA,
                explanation));
        questionDtoList.add(new QuestionDto(
                2L,
                "Question 2",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.MEDIUM,
                Category.GIT,
                explanation));

        // when
        when(questionService.getAllQuestions()).thenReturn(questionDtoList);

        // then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questions" +
                                "/allQuestions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        // Map JSON to list of QuestionDto
        List<QuestionDto> actualQuestions = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });

        // verify
        assertEquals(questionDtoList, actualQuestions);
    }


    @Test
    @DisplayName("When addQuestion return 201 and question id")
    public void addQuestion() throws Exception {
        //given
        PostQuestionDto postQuestionDto = new PostQuestionDto(
                "Question 1",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.EASY,
                Category.JAVA,
                explanation);

        //when
        when(questionService.addQuestion(postQuestionDto)).thenReturn(1l);

        // then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/questions/addQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postQuestionDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @DisplayName("When getByLevel return 200")
    public void getByLevel() throws Exception {
        //given
        QuestionDto questionDto1 = new QuestionDto(
                1L,
                "Question 1",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.EASY,
                Category.JAVA,
                explanation);

        QuestionDto questionDto2 = new QuestionDto(
                1L,
                "Question 1",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.EASY,
                Category.JAVA,
                explanation);

        //when
        List<QuestionDto> questionDtoList = List.of(questionDto1, questionDto2);
        when(questionService.getQuestionsByLevel(Level.EASY)).thenReturn(questionDtoList);

        // then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questions" +
                                "/level/EASY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        // Map JSON to list of QuestionDto
        List<QuestionDto> actualQuestions = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });

        // verify
        assertEquals(questionDtoList, actualQuestions);

    }

    @Test
    @DisplayName("When getByCategory return 200")
    public void getByCategory() throws Exception {
        //given
        QuestionDto questionDto1 = new QuestionDto(
                1L,
                "Question 1",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.EASY,
                Category.JAVA,
                explanation);

        QuestionDto questionDto2 = new QuestionDto(
                1L,
                "Question 1",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.EASY,
                Category.JAVA,
                explanation);

        //when
        List<QuestionDto> questionDtoList = List.of(questionDto1, questionDto2);
        when(questionService.getQuestionsByCategory(Category.JAVA)).thenReturn(questionDtoList);

        // then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questions" +
                                "/category/JAVA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        // Map JSON to list of QuestionDto
        List<QuestionDto> actualQuestions = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });

        // verify
        assertEquals(questionDtoList, actualQuestions);
    }

    @Test
    @DisplayName("When randomQuestion return 200")
    public void getRandomQuestion() throws Exception {
        //given
        QuestionDto questionDto = new QuestionDto(
                1L,
                "Question 1",
                "A",
                "B",
                "C",
                "D",
                "A",
                Level.EASY,
                Category.JAVA,
                explanation);

        //when
        when(questionService.getRandomQuestion()).thenReturn(questionDto);

        // then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/questions" +
                                "/randomQuestion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        // Map JSON to QuestionDto
        QuestionDto actualValue = objectMapper.readValue(jsonResponse, QuestionDto.class);

        // verify
        assertEquals(questionDto, actualValue);
    }

}