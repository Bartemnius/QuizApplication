package com.project.quizapp.controller;

import com.project.quizapp.entity.Question;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/allQuestions")
    public String getAllQuestion() {
        List<Question> allQuestions = questionService.getAllQuestions();
        return allQuestions.toString();
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return "Question Added!";
    }

    @GetMapping("/level/{questionLevel}")
    public String getQuestionsByLevel(@PathVariable Level questionLevel) {
        List<Question> questionsByLevel = questionService.getQuestionsByLevel(questionLevel);
        return questionsByLevel.toString();
    }

    @GetMapping("/category/{category}")
    public String getQuestionsByLevel(@PathVariable Category category) {
        List<Question> questionsByCategory = questionService.getQuestionsByCategory(category);
        return questionsByCategory.toString();
    }
}
