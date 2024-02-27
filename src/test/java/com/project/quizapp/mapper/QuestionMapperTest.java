package com.project.quizapp.mapper;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.entity.Question;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QuestionMapperTest {

    private QuestionMapper questionMapper = QuestionMapper.getInstance();

    @Test
    public void questionToQuestionDtoConversion() {
        Question question = new Question(
                "This is question",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.JAVA);
        QuestionDto questionDto = questionMapper.toDto(question);

        assertThat(questionDto.id()).isEqualTo(question.getId());
        assertThat(questionDto.question()).isEqualTo(question.getQuestion());
        assertThat(questionDto.ansA()).isEqualTo(question.getAnsA());
        assertThat(questionDto.ansB()).isEqualTo(question.getAnsB());
        assertThat(questionDto.ansC()).isEqualTo(question.getAnsC());
        assertThat(questionDto.ansD()).isEqualTo(question.getAnsD());
        assertThat(questionDto.goodAnswer()).isEqualTo(question.getGoodAnswer());
        assertThat(questionDto.level()).isEqualTo(question.getLevel());
        assertThat(questionDto.category()).isEqualTo(question.getCategory());
    }

    @Test
    public void questionDtoToQuestionConversion() {
        QuestionDto questionDto = new QuestionDto(
                0l,
                "This is question",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.JAVA);
        Question question = questionMapper.toEntity(questionDto);

        assertThat(questionDto.id()).isEqualTo(question.getId());
        assertThat(questionDto.question()).isEqualTo(question.getQuestion());
        assertThat(questionDto.ansA()).isEqualTo(question.getAnsA());
        assertThat(questionDto.ansB()).isEqualTo(question.getAnsB());
        assertThat(questionDto.ansC()).isEqualTo(question.getAnsC());
        assertThat(questionDto.ansD()).isEqualTo(question.getAnsD());
        assertThat(questionDto.goodAnswer()).isEqualTo(question.getGoodAnswer());
        assertThat(questionDto.level()).isEqualTo(question.getLevel());
        assertThat(questionDto.category()).isEqualTo(question.getCategory());
    }

    @Test
    public void questionListToQuestionDtoListConversion() {
        Question question1 = new Question(
                "This is question 1",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.JAVA);
        Question question2 = new Question(
                "This is question 2",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.JAVA);
        QuestionDto questionDto1 = questionMapper.toDto(question1);
        QuestionDto questionDto2 = questionMapper.toDto(question2);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        List<QuestionDto> questionDtoList = new ArrayList<>();
        questionDtoList.add(questionDto1);
        questionDtoList.add(questionDto2);

        assertThat(questionDtoList).isEqualTo(questionMapper.toListDto(questions));
    }

    @Test
    public void questionDtoListToQuestionListConversion() {
        QuestionDto questionDto1 = new QuestionDto(
                0l,
                "This is question 1",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.JAVA);
        QuestionDto questionDto2 = new QuestionDto(
                1l,
                "This is question 2",
                "ansA",
                "ansB",
                "ansC",
                "ansD",
                "ansA",
                Level.EASY,
                Category.JAVA);
        Question question1 = questionMapper.toEntity(questionDto1);
        Question question2 = questionMapper.toEntity(questionDto2);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        List<QuestionDto> questionDtoList = new ArrayList<>();
        questionDtoList.add(questionDto1);
        questionDtoList.add(questionDto2);

        assertThat(questions).isEqualTo(questionMapper.toListEntity(questionDtoList));
    }
}