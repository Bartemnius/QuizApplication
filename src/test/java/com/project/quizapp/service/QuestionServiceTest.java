package com.project.quizapp.service;

import com.project.quizapp.entity.Question;
import com.project.quizapp.repository.QuestionRepository;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;


    @Test
    void whenAddQuestionIsCalledQuestionAddedIsReturned() {
        Question question = new Question("question", "a", "b", "c", "d", "a", Level.EASY, Category.JAVA);
        when(questionRepository.save(any())).then(returnsFirstArg());

        ResponseEntity<Question> questionResponseEntity = questionService.addQuestion(question);
        assertThat(questionResponseEntity.getBody()).isEqualTo(question);
        assertThat(questionResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void whenGetAllQuestionsIsCalledReturnAllQuestions() {
        Question question1 = new Question("question1", "a", "b", "c", "d", "a", Level.EASY, Category.JAVA);
        Question question2 = new Question("question2", "a", "b", "c", "d", "b", Level.EASY, Category.JAVA);
        Question question3 = new Question("question3", "a", "b", "c", "d", "c", Level.EASY, Category.JAVA);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        when(questionRepository.findAll()).thenReturn(questionList);

        ResponseEntity<List<Question>> allQuestions = questionService.getAllQuestions();
        assertThat(allQuestions.getBody()).isEqualTo(questionList);
        assertThat(allQuestions.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void whenGetQuestionByLevelOnlyEasyAreReturned() {
        Question question1 = new Question("question1", "a", "b", "c", "d", "a", Level.EASY, Category.JAVA);
        Question question3 = new Question("question3", "a", "b", "c", "d", "c", Level.EASY, Category.JAVA);
        List<Question> easyQuestionList = new ArrayList<>();
        easyQuestionList.add(question1);
        easyQuestionList.add(question3);
        when(questionRepository.getQuestionsByLevel(Level.EASY)).thenReturn(easyQuestionList);

        ResponseEntity<List<Question>> questionsByLevel = questionService.getQuestionsByLevel(Level.EASY);
        assertThat(questionsByLevel.getBody()).isEqualTo(easyQuestionList);
        assertThat(questionsByLevel.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void whenGetQuestionByJavaCategoryIsCalledReturnOnlyJavaQuestions() {
        Question question1 = new Question("question1", "a", "b", "c", "d", "a", Level.EASY, Category.JAVA);
        Question question3 = new Question("question3", "a", "b", "c", "d", "c", Level.EASY, Category.JAVA);
        List<Question> javaQuestionList = new ArrayList<>();
        javaQuestionList.add(question1);
        javaQuestionList.add(question3);
        when(questionRepository.getQuestionsByCategory(Category.JAVA)).thenReturn(javaQuestionList);

        ResponseEntity<List<Question>> questionsByCategory = questionService.getQuestionsByCategory(Category.JAVA);
        assertThat(questionsByCategory.getBody()).isEqualTo(javaQuestionList);
        assertThat(questionsByCategory.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
