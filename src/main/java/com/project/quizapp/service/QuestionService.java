package com.project.quizapp.service;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.repository.QuestionRepository;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper = QuestionMapper.getInstance();

    // TODO :
    //  This class needs to be changed.
    //  There is no need for service to return ResponseEntity at all
    //  This only should apply to Controller classes as here single responsibility rule is broken


    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionMapper.toListDto(questionRepository.findAll()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Long> addQuestion(QuestionDto questionDto) {
        try {
            return new ResponseEntity<>(questionRepository.save(questionMapper.toEntity(questionDto)).getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsByLevel(Level questionLevel) {
        try {
            return new ResponseEntity<>(questionMapper.toListDto(questionRepository.getQuestionsByLevel(questionLevel)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(Category category) {
        try {
            return new ResponseEntity<>(questionMapper.toListDto(questionRepository.getQuestionsByCategory(category)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<QuestionDto> getRandomQuestion() {
        try {
            return new ResponseEntity<>(questionMapper.toDto(questionRepository.getRandomQuestion()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<QuestionDto> getQuestionById(Long id) {
        try {
            return new ResponseEntity<>(questionMapper.toDto(questionRepository.getReferenceById(id)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
