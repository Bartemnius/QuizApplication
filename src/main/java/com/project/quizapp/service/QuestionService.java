package com.project.quizapp.service;

import com.project.quizapp.dto.PostQuestionDto;
import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.entity.Question;
import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.repository.QuestionRepository;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper = QuestionMapper.getInstance();


    public List<QuestionDto> getAllQuestions() {
        return questionMapper.toListDto(questionRepository.findAll());
    }

    public Long addQuestion(PostQuestionDto postQuestionDto) {
        return questionRepository.save(questionMapper.toEntityFromPostDto(postQuestionDto)).getId();
    }

    public List<QuestionDto> getQuestionsByLevel(Level questionLevel) {
        return questionMapper.toListDto(questionRepository.getQuestionsByLevel(questionLevel));
    }

    public List<QuestionDto> getQuestionsByCategory(Category category) {
        return questionMapper.toListDto(questionRepository.getQuestionsByCategory(category));
    }

    public QuestionDto getRandomQuestion() {
        return questionMapper.toDto(questionRepository.getRandomQuestion());
    }

    public QuestionDto getQuestionById(Long id) {
        return questionMapper.toDto(questionRepository.getReferenceById(id));
    }

    public Page<QuestionDto> getQuestions(PageRequest of) {
            Page<Question> questionPage = questionRepository.findAll(of);
            return questionPage.map(questionMapper::toDto);
    }
}
