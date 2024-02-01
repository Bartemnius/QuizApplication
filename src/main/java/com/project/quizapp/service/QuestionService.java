package com.project.quizapp.service;

import com.project.quizapp.entity.Question;
import com.project.quizapp.repository.QuestionRepository;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
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

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public List getQuestionsByLevel(Level questionLevel) {
        return questionRepository.getQuestionsByLevel(questionLevel);
    }

    public List getQuestionsByCategory(Category category) {
        return questionRepository.getQuestionsByCategory(category);
    }
}
