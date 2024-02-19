package com.project.quizapp.service;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.repository.QuestionRepository;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import lombok.RequiredArgsConstructor;
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

    public Long addQuestion(QuestionDto questionDto) {
        return questionRepository.save(questionMapper.toEntity(questionDto)).getId();
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
}
