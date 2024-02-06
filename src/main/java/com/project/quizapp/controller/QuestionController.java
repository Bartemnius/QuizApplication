package com.project.quizapp.controller;

import com.project.quizapp.entity.Question;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return "Question Added!";
    }

    @GetMapping("/level/{questionLevel}")
    public ResponseEntity<List<Question>> getQuestionsByLevel(@PathVariable Level questionLevel) {
        return questionService.getQuestionsByLevel(questionLevel);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByLevel(@PathVariable Category category) {
        return questionService.getQuestionsByCategory(category);
    }
}
