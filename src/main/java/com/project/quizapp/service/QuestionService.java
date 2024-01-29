package com.project.quizapp.service;

import com.project.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List getAllQuestions() {
        return questionRepository.findAll();
    }
}
