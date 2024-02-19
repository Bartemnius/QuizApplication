package com.project.quizapp.repository;

import com.project.quizapp.entity.Question;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    private Question question1;
    private Question question2;
    private Question question3;
    private Question question4;

    @BeforeEach
    private void setUp() {
        question1 = new Question(
                "This is question 1",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.GIT);
        question2 = new Question(
                "This is question 2",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.JAVA);
        question3 = new Question(
                "This is question 1",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.MEDIUM,
                Category.SPRING);
        question4 = new Question(
                "This is question 1",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.HARD,
                Category.JAVA);


        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);
    }


    @Test
    @DisplayName("When getQuestionByLevel(Easy) called return only easy questions")
    public void getEasyQuestions() {
        List<Question> questionsByLevel = questionRepository.getQuestionsByLevel(Level.EASY);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        assertThat(questionsByLevel).isEqualTo(questions);
    }

    @Test
    @DisplayName("When getQuestionByCategory(Spring) called return only Spring questions")
    public void getSpringQuestions() {
        List<Question> questionsByCategory = questionRepository.getQuestionsByCategory(Category.SPRING);
        List<Question> questions = new ArrayList<>();
        questions.add(question3);
        assertThat(questionsByCategory).isEqualTo(questions);
    }

    @Test
    @DisplayName("When getQuestionByCategory(Linux) called return empty list")
    public void getLinuxQuestions() {
        List<Question> questionsByCategory = questionRepository.getQuestionsByCategory(Category.LINUX);
        assertThat(questionsByCategory.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("When getRandomQuestion() called return not null")
    public void getRandomQuestion() {
       assertNotNull(questionRepository.getRandomQuestion());
    }
}