package com.project.quizapp.service;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.entity.Question;
import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.repository.QuestionRepository;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;
    private final QuestionMapper questionMapper = QuestionMapper.getInstance();


    @Test
    @DisplayName("Verify get all questions")
    public void getAllQuestions() {
        questionService.getAllQuestions();
        verify(questionRepository).findAll();
    }


    @Test
    @DisplayName("when AddQuestion is called id is returned")
    void addQuestion() {
        QuestionDto questionDto = new QuestionDto(
                1l,
                "question",
                "a",
                "b",
                "c",
                "d",
                "a",
                Level.EASY,
                Category.JAVA);
        Question question = questionMapper.toEntity(questionDto);
        when(questionRepository.save(question)).thenReturn(question);
        Long questionId = questionService.addQuestion(questionDto);

        ArgumentCaptor<Question> questionArgumentCaptor = ArgumentCaptor.forClass(Question.class);
        verify(questionRepository).save(questionArgumentCaptor.capture());
        assertThat(questionArgumentCaptor.getValue()).isEqualTo(question);
        assertThat(questionArgumentCaptor.getValue().getId()).isEqualTo(questionId);
    }

    //
    @Test
    @DisplayName("Verify get questions by level")
    public void getQuestionsByLevel() {
        Level questionLevel = Level.EASY;
        questionService.getQuestionsByLevel(questionLevel);
        verify(questionRepository).getQuestionsByLevel(questionLevel);
    }

    @Test
    @DisplayName("Verify get questions by category")
    public void getQuestionsByCategory() {
        Category questionCategory = Category.JAVA;
        questionService.getQuestionsByCategory(questionCategory);
        verify(questionRepository).getQuestionsByCategory(questionCategory);
    }

    @Test
    @DisplayName("Verify get random question")
    public void getRandomQuestion() {
        questionService.getRandomQuestion();
        verify(questionRepository).getRandomQuestion();
    }

    @Test
    @DisplayName("Verify get question by id")
    public void getQuestionById() {
        Long id = 1l;
        questionService.getQuestionById(id);
        verify(questionRepository).getReferenceById(id);
    }

}
